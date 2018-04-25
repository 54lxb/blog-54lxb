package cn.lxb.blog.web;

import cn.lxb.blog.entity.Blog;
import cn.lxb.blog.entity.Comment;
import cn.lxb.blog.constant.BlogConstant;
import cn.lxb.blog.service.BlogIndexService;
import cn.lxb.blog.service.BlogService;
import cn.lxb.blog.service.BloggerService;
import cn.lxb.blog.service.CommentService;
import cn.lxb.blog.utils.PageUtil;
import cn.lxb.blog.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 博客Controller层
 * Created by Andy on 2017/3/13.
 */
@Controller
@RequestMapping("/article")
public class BlogController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private BlogService blogService;

    @Resource
    private BloggerService bloggerService;

    @Resource
    private CommentService commentService;

    @Resource
    private BlogIndexService blogIndexService;

    /**
     * TODO 请求博客详细信息
     */
    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id, Model model, HttpServletRequest request) throws Exception {

        Blog blog = blogService.findById(id);
        String keyWord = blog.getKeyWord();
        if (StringUtil.isNotEmpty(keyWord)) {
            String arr[] = keyWord.split(" ");
            List<String> keyWords = StringUtil.filterWhite(Arrays.asList(arr));
            model.addAttribute("keyWords", keyWords);
        } else {
            model.addAttribute("keyWords", null);
        }
        model.addAttribute("blog", blog);
        // 博客点击次数加1
        Integer i = blogService.addClickHit(id);
        if (i > 0) {
            logger.info("博客操作记录：{}", "增加博客点击量成功！");
        } else {
            logger.info("博客操作记录：{}", "增加博客点击量失败！");
        }
        //查询上，下博客
        Blog last = blogService.getLastBlog(id);
        Blog next = blogService.getNextBlog(id);
        String context = request.getServletContext().getContextPath();
        String pageCode = PageUtil.genUpAndDownPageCode(last, next, context);

        // 查询审核通过的评论
        Map<String, Object> map = new HashMap<>();
        map.put("blogId", blog.getId());
        map.put("state", 1);
        List<Comment> commentList = commentService.list(map);

        //将其他数据装入Map，存入request域
        model.addAttribute("commentList", commentList);
        model.addAttribute("pageCode", pageCode);
        model.addAttribute("blogger", bloggerService.find());
        model.addAttribute("mainPage", BlogConstant.BLOG_DETAIL);
        model.addAttribute("pageTitle", blog.getTitle());

        return BlogConstant.COMMON_MAIN;
    }

    /**
     * TODO 根据关键字查询相关博客信息
     *
     * @param keyword 查询关键字
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam(value = "keyword", required = false) String keyword,
                         @RequestParam(value = "page", required = false) String page, HttpSession session, Model model) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        page = StringUtil.isEmpty(page) ? "1" : page;
        result.put("mainPage", BlogConstant.BLOG_RESULT);

        List<Blog> blogList = blogIndexService.searchBlog(keyword.trim());

        Integer toIndex = blogList.size() >= Integer.parseInt(page) * BlogConstant.DEFAULT_RECORDS ? Integer.parseInt(page) * BlogConstant.DEFAULT_RECORDS : blogList.size();

        String projectContext = session.getServletContext().getContextPath();
        String pageCode = PageUtil.getUpAndDownPageCode(Integer.parseInt(page), blogList.size(), keyword.trim(), BlogConstant.DEFAULT_RECORDS, projectContext);
        result.put("blogList", blogList.subList((Integer.parseInt(page) - 1) * BlogConstant.DEFAULT_RECORDS, toIndex));
        result.put("pageCode", pageCode);
        result.put("keyword", keyword);
        result.put("resultTotal", blogList.size());
        result.put("pageTitle", "搜索关键字'" + keyword + "'结果页面");
        model.addAllAttributes(result);

        return BlogConstant.COMMON_MAIN;
    }
}
