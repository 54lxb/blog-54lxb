package cn.lxb.blog.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 * Created by Andy on 2017/3/12.
 */
public class BlogExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {

        //1.解析出异常类型
        //2.如果该异常类型是自定义异常，直接取出异常信息，在错误页面显示
        //3.如果该异常类型不是自定义异常，构造一个自定义异常类型，信息为：未知错误！

        BlogException blogException;
        if (exception instanceof BlogException) {
            blogException = (BlogException)exception;
        } else {
            blogException = new BlogException(exception.getMessage());
        }
        //获取错误信息
        String message = blogException.getMessage();
        if (message == null || "".equals(message)) {
            message = "系统异常，未知错误！";
        }
        ModelAndView modelAndView = new ModelAndView();
        //将错误信息存入request域
        modelAndView.addObject("message", message);
        //转发到错误页面并显示错误信息
        modelAndView.setViewName("common/error");
        return modelAndView;
    }

}
