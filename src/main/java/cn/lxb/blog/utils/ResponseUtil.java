package cn.lxb.blog.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * TODO response工具类
 * Created by Andy on 2017/3/13.
 */
public class ResponseUtil {

    public static void write(HttpServletResponse response, Object o) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(o.toString());
        out.flush();
        out.close();
    }

    public static void setContent(HttpServletResponse response) throws Exception {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    }
}
