package cn.lxb.blog.web.admin;

import cn.lxb.blog.constant.ImageTypeEnums;
import cn.lxb.blog.constant.MsgCode;
import cn.lxb.blog.constant.MsgInfo;
import cn.lxb.blog.service.BlogUploadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Description：图片,文件上传
 * </P>
 *
 * @author Andy
 * @apiNote 知识改变命运，技术改变世界！
 * @since 2017-09-13 09:00.
 */
@Controller
@RequestMapping("/admin/upload")
public class BlogUploadController {

    @Resource
    private BlogUploadService blogUploadService;


    /**
     * <p>
     * Description：图片上传
     * </p>
     *
     * @param file    图片文件
     * @param session HttpSession
     * @return MsgBean
     * @author Andy
     * @apiNote 知识改变命运，技术改变世界！
     * @since 2017-09-13 09:00.
     */
    @RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> imageUpload(@RequestParam("editormd-image-file") MultipartFile file, HttpSession session) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put("url", "");
            if (!blogUploadService.checkImageFileType(file)) {
                resultMap.put("success", MsgCode.FAILED.getValue());
                resultMap.put("message", MsgInfo.IMAGE_TYPE_ERROR.getValue());
                return resultMap;
            }
            if (!blogUploadService.checkImageFileSize(file)) {
                resultMap.put("success", MsgCode.FAILED.getValue());
                resultMap.put("message", MsgInfo.IMAGE_SIZE_ERROR.getValue());
                return resultMap;
            }
            // 判断用户是否上传文件
            if (null ==  file.getOriginalFilename() ||  file.getOriginalFilename().length() <= 0) {
                resultMap.put("success", MsgCode.FAILED.getValue());
                resultMap.put("message", MsgInfo.NO_FILE_CHOOSE.getValue());
                return resultMap;
            }

            resultMap.put("success", MsgCode.SUCCESS.getValue());
            resultMap.put("message", MsgInfo.SUCCESS.getValue());
            resultMap.put("url", blogUploadService.saveUploadImage(file, session, ImageTypeEnums.BLOG_ARTICLE.getIndex()));
            return resultMap;
        } catch (IOException e) {
            e.printStackTrace();
            resultMap.put("success", MsgCode.FAILED.getValue());
            resultMap.put("message", MsgInfo.SERVER_ERROR.getValue());
            return resultMap;
        }
    }

}
