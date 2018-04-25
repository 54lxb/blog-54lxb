package cn.lxb.blog.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <P>
 *  Description：请求时间计算拦截器
 * </P>
 * @author Andy
 * @since 2017-09-13 09:00.
 * @apiNote 知识改变命运，技术改变世界！
 */
public class TimeInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(TimeInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!request.getRequestURI().contains("/static/**")) {
            long startTime = System.currentTimeMillis();
            request.setAttribute("stat_startTime", startTime);
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (!request.getRequestURI().contains("/static/**")) {
            long startTime = (Long) request.getAttribute("stat_startTime");
            long endTime = System.currentTimeMillis();
            long executeTime = endTime - startTime;
            logger.info("》》》》》》》》》》》》》》》访问地址{}，处理器{}，处理时间{} ms", request.getRequestURL(), handler.toString(), executeTime);
        }
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, Exception exception) throws Exception {}
}  