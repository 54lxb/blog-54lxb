package cn.lxb.blog.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * <p>
 * description：response工具类
 * </p>
 *
 * @author 54LXB.
 * @apiNote 知识改变命运，技术改变世界。
 * @since 2017-11-25.
 */
public class ResponseUtil {

    /**
     * <p>
     * description：写出响应数据
     * </p>
     *
     * @param response HttpServletResponse
     * @param o        Object
     * @throws Exception
     * @author 54LXB.
     * @apiNote 知识改变命运，技术改变世界。
     * @since 2017-11-25.
     */
    public static void write(HttpServletResponse response, Object o) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(o.toString());
        out.flush();
        out.close();
    }

    /**
     * <p>
     * description：设置响应头
     * </p>
     *
     * @param response HttpServletResponse
     * @throws Exception
     * @author 54LXB.
     * @apiNote 知识改变命运，技术改变世界。
     * @since 2017-11-25.
     */
    public static void setContent(HttpServletResponse response) throws Exception {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    }
}
