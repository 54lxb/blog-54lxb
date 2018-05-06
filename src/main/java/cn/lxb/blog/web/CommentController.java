package cn.lxb.blog.web;

import cn.lxb.blog.entity.Blog;
import cn.lxb.blog.entity.Comment;
import cn.lxb.blog.service.BlogService;
import cn.lxb.blog.service.CommentService;
import cn.lxb.blog.utils.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 评论Controller层
 * Created by Andy on 2017/3/13.
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @Resource
    private BlogService blogService;

    /**
     * TODO 修改评论
     *
     * @param comment 评论信息bean
     */
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public void update(@PathVariable("id") Integer id, Comment comment, @RequestParam("passcode") String passcode,
                       HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String code = (String) session.getAttribute("passcode"); // 获取系统生成的验证码
        JSONObject result = new JSONObject();
        int resultTotal = 0; // 操作的记录条数
        //校验验证码
        if (!passcode.equals(code)) {
            //验证码错误
            result.put("success", false);
            result.put("errorInfo", "验证码填写错误！");
        } else {
            //验证码正确
            String userIp = request.getRemoteAddr(); // 获取用户IP
            comment.setUserIp(userIp); //设置IP

            //修改评论
            resultTotal = commentService.updateById(id, comment);
        }
        result.put("success", resultTotal > 0);

        ResponseUtil.write(response, result);
    }

    /**
     * TODO 添加评论
     *
     * @param comment 评论信息bean
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(Comment comment, @RequestParam("passcode") String passcode,
                     HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String code = (String) session.getAttribute("passcode"); // 获取系统生成的验证码
        JSONObject result = new JSONObject();
        int resultTotal = 0; // 操作的记录条数
        //校验验证码
        if (!passcode.equals(code)) {
            //验证码错误
            result.put("success", false);
            result.put("errorInfo", "验证码填写错误！");
        } else {
            //验证码正确
            String userIp = request.getRemoteAddr(); // 获取用户IP
            comment.setUserIp(userIp); //设置IP
            if (comment.getId() == null) {
                //添加评论
                resultTotal = commentService.add(comment);
                // 该博客的回复次数加 1
                Blog blog = blogService.findById(comment.getBlog().getId());
                Integer id = blog.getId();
                blog.setReplyHit(blog.getReplyHit() + 1);
                blogService.updateById(id, blog);
            } else {
                //修改评论
                Integer id = comment.getId();
                resultTotal = commentService.updateById(id, comment);
            }
            result.put("success", resultTotal > 0);
        }
        ResponseUtil.write(response, result);
    }
}
