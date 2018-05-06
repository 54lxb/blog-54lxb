package cn.lxb.blog.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * <p>
 * Description：文件上传服务
 * </P>
 *
 * @author Andy
 * @apiNote 知识改变命运，技术改变世界！
 * @since 2017-09-13 09:00.
 */
public interface BlogUploadService {


    /**
     * <p>
     * Description：文件格式校验
     * </p>
     *
     * @param file 文件信息
     * @return true 通过 false 不通过
     * @author Andy
     * @apiNote 知识改变命运，技术改变世界！
     * @since 2017-09-13 09:00.
     */
    boolean checkImageFileType(MultipartFile file);

    /**
     * <p>
     * Description：文件大小校验
     * </p>
     *
     * @param file 文件信息
     * @return true 通过 false 不通过
     * @author Andy
     * @apiNote 知识改变命运，技术改变世界！
     * @since 2017-09-13 09:00.
     */
    boolean checkImageFileSize(MultipartFile file);


    /**
     * <p>
     * Description：保存上传文件
     * </P>
     *
     * @param file         文件信息
     * @param savePathType 文件保存路劲类型
     * @return 文件保存后的相对路径
     * @author Andy
     * @apiNote 知识改变命运，技术改变世界！
     * @since 2017-09-13 09:00.
     */
    String saveUploadImage(MultipartFile file, HttpSession session, int savePathType) throws IOException;

}
