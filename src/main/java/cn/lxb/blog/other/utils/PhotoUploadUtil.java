package cn.lxb.blog.other.utils;

import cn.lxb.blog.entity.PhotoResult;
import cn.lxb.blog.other.config.BlogConfig;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;

/**
 * 使用七牛存储图片
 *
 * @author 54LXB.
 * @apiNote 知识改变命运，技术改变世界。
 * @since 2017-11-25.
 */
@Component
public class PhotoUploadUtil {

    @Resource
    private BlogConfig blogConfig;

    //上传到七牛后保存的文件名
    private String getFilePath(String fileName) {
        StringBuilder resultPath = new StringBuilder();
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH) + 1;
        int day = instance.get(Calendar.DATE);
        return resultPath.append(year).append("/").append(month).append("/").append(day).append("/").append(fileName).toString();
    }

    private Auth getAuth() {
        return Auth.create(blogConfig.getAccessKey(), blogConfig.getSecretKey());
    }

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    private String getUpToken() {
        Auth auth = Auth.create(blogConfig.getAccessKey(), blogConfig.getSecretKey());
        return auth.uploadToken(blogConfig.getBucketName());
    }

    /**
     * 长传图片
     *
     * @param realName 图片路径名
     * @param filename 图片名
     * @return 图片上传的结果
     */
    public PhotoResult uploadPhoto(String realName, String filename) {
        PhotoResult result = new PhotoResult();
        try {
            Configuration cfg = new Configuration(Zone.zone0());
            Response response = new UploadManager(cfg).put(realName, getFilePath(filename), getUpToken());
            if (response.isOK()) {
                result.setSuccess(1);
                result.setUrl(blogConfig.getBasePath() + getFilePath(filename));
                return result;
            }
        } catch (QiniuException e) {
            result.setSuccess(0);
            result.setMessage(e.getMessage());
            return result;
        }
        return result;
    }

    /**
     * 删除图片
     *
     * @param fileNames 文件名数组
     * @return 删除的记录数
     */
    public int deletePhoto(String[] fileNames) {
        try {
            int result = 0;
            Configuration cfg = new Configuration(Zone.zone0());
            BucketManager bucketManager = new BucketManager(getAuth(), cfg);
            for (String filename : fileNames) {
                bucketManager.delete(blogConfig.getBucketName(), filename);
                result++;
            }
            return result;
        } catch (QiniuException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
