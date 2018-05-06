package cn.lxb.blog.service.Impl;

import cn.lxb.blog.constant.FileLimit;
import cn.lxb.blog.constant.ImageTypeEnums;
import cn.lxb.blog.service.BlogUploadService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * Description：文件上传服务
 * </P>
 *
 * @author Andy
 * @apiNote 知识改变命运，技术改变世界！
 * @since 2017-09-13 09:00.
 */
@Service
public class BlogUploadServiceImpl implements BlogUploadService {

    private Logger logger = LoggerFactory.getLogger(BlogUploadServiceImpl.class);

    @Override
    public boolean checkImageFileType(MultipartFile file) {
        // 1.文件上传格式校验
        FileLimit rarLimit = FileLimit.IMAGE;
        String suffix = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
        return rarLimit.getSuffix().contains(suffix);
    }

    @Override
    public boolean checkImageFileSize(MultipartFile file) {
        // 2.验证文件大小
        FileLimit rarLimit = FileLimit.IMAGE;
        long size = file.getSize();
        long allowSize = rarLimit.getSizeValue();
        return size <= allowSize;
    }

    @Override
    public String saveUploadImage(MultipartFile file, HttpSession session, int savePathType) throws IOException {

        // 获取原文件名
        String oldFileName = file.getOriginalFilename();
        if (null == oldFileName || oldFileName.length() <= 0) {
            // 上传图片失败，或者用户根本没选择上传文件 返回null
            return null;
        }

        Date nowDate = new Date();
        // 文件保存相对路径 = 默认路径 + 拼接路径
        String tempPath = new SimpleDateFormat("yyyyMMdd").format(nowDate);
        String save_path = String.format("%s%s/", ImageTypeEnums.stateOf(savePathType), tempPath);

        // 获取图片保存物理路径
        String real_path = session.getServletContext().getRealPath(save_path);
        // 设置新文件名
        String tempName = new SimpleDateFormat("yyyyMMddHHmmss").format(nowDate);
        String newFilename = String.format("%s%s", tempName, oldFileName.substring(oldFileName.lastIndexOf(".")));

        File targetFile = new File(real_path, newFilename);
        if (!targetFile.exists()) {
            boolean createStatus = targetFile.mkdirs();
            logger.info("》》》》》》》》》》》》》上传文件路径不存在，创建文件路径{}：{}", createStatus ? "成功" : "失败", targetFile.getAbsolutePath());
        }

        // 保存文件到硬盘
        file.transferTo(targetFile);

        // 上传图片成功，返回图片在硬盘的相对路径
        return String.format("%s%s", save_path, newFilename);
    }

}
