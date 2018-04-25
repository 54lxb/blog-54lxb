package cn.lxb.blog.web.admin;

import cn.lxb.blog.entity.Blog;
import cn.lxb.blog.entity.BlogType;
import cn.lxb.blog.entity.Blogger;
import cn.lxb.blog.entity.Link;
import cn.lxb.blog.service.*;
import cn.lxb.blog.utils.ResponseUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 管理员系统Controller层
 * Created by Andy on 2017/3/13.
 */
@Controller
@RequestMapping("/admin/system")
public class SystemAdminController {

    @Resource
    private BloggerService bloggerService;

    @Resource
    private BlogTypeService blogTypeService;

    @Resource
    private BlogService blogService;

    @Resource
    private LinkService linkService;

    @Resource
    private BlogIndexService blogIndexService;

    @Resource
    private ThreadPoolTaskExecutor taskExecutor;

    private Logger logger = LoggerFactory.getLogger(SystemAdminController.class);

    /**
     * 刷新系统缓存
     */
    @RequestMapping("/refreshSystem")
    public void refreshSystem(HttpServletResponse response, HttpServletRequest request) throws Exception {

        ServletContext application = RequestContextUtils.getWebApplicationContext(request).getServletContext();
        Blogger blogger = bloggerService.find(); // 查询博主信息
        blogger.setPassword(null);
        application.setAttribute("blogger", blogger);

        List<BlogType> blogTypeCountList = blogTypeService.countList(); // 查询博客类别以及博客的数量
        application.setAttribute("blogTypeCountList", blogTypeCountList);

        List<Blog> blogCountList = blogService.countList(); // 根据日期分组查询博客
        application.setAttribute("blogCountList", blogCountList);

        List<Blog> blogList = blogService.list(null); // 更新所有博客的索引
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                for (Blog blog : blogList) {
                    try {
                        blogIndexService.updateIndex(blog);
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.info(e.getMessage());
                    }
                }
            }
        });


        List<Link> linkList = linkService.list(null); // 获取所有友情链接
        application.setAttribute("linkList", linkList);

        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
    }
}
