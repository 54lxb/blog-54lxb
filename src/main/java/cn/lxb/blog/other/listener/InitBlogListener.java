package cn.lxb.blog.other.listener;

import cn.lxb.blog.entity.Blog;
import cn.lxb.blog.entity.BlogType;
import cn.lxb.blog.entity.Blogger;
import cn.lxb.blog.entity.Link;
import cn.lxb.blog.interceptor.TimeInterceptor;
import cn.lxb.blog.service.*;
import cn.lxb.blog.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * <p>
 * 初始化组件 把博主信息 根据博客类别分类信息 根据日期归档分类信息
 * 存放到application中，用以提供页面请求性能
 * </p>
 * Created by Andy on 2017/3/13.
 */
@Component
public class InitBlogListener implements ServletContextListener, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static final Logger logger = LoggerFactory.getLogger(TimeInterceptor.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        InitBlogListener.applicationContext = applicationContext;
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        try {

            ServletContext application = servletContextEvent.getServletContext();

            // 初始化其他数据
            BloggerService bloggerService = applicationContext.getBean("bloggerServiceImpl", BloggerService.class);
            Blogger blogger = bloggerService.find(); // 查询博主信息
            blogger.setPassword(null);
            application.setAttribute("blogger", blogger);

            BlogTypeService blogTypeService = applicationContext.getBean("blogTypeServiceImpl", BlogTypeService.class);
            List<BlogType> blogTypeCountList = blogTypeService.countList(); // 查询博客类别以及博客的数量
            application.setAttribute("blogTypeCountList", blogTypeCountList);

            BlogService blogService = applicationContext.getBean("blogServiceImpl", BlogService.class);
            List<Blog> blogCountList = blogService.countList(); // 根据日期分组查询博客
            application.setAttribute("blogCountList", blogCountList);

            LinkService linkService = applicationContext.getBean("linkServiceImpl", LinkService.class);
            List<Link> linkList = linkService.list(null); // 查询所有的友情链接信息
            application.setAttribute("linkList", linkList);

            BlogIndexService blogIndexService = applicationContext.getBean("blogIndexServiceImpl", BlogIndexService.class);
            List<Blog> blogList = blogService.list(null); // 生成所有博客的索引
            for (Blog blog : blogList) {
                if (null == blog) {
                    continue;
                }
                blogIndexService.deleteIndex(String.valueOf(blog.getId()));
                blog.setContentNoTag(StringUtil.deleteAllHTMLTag(blog.getContent()));
                blogIndexService.addIndex(blog);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
