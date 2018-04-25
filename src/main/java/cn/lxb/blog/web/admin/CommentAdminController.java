package cn.lxb.blog.web.admin;

import cn.lxb.blog.entity.Comment;
import cn.lxb.blog.entity.PageBean;
import cn.lxb.blog.service.CommentService;
import cn.lxb.blog.utils.DateUtil;
import cn.lxb.blog.utils.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员评论Controller层
 * Created by Andy on 2017/3/13.
 */
@Controller
@RequestMapping("/admin/comment")
public class CommentAdminController {

    @Resource
    private CommentService commentService;

    /**
     * TODO 分页查询评论信息
     *
     * @param page 起始页
     * @param rows 查询记录数
     */
    @RequestMapping(value = "/list")
    public void list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "rows", required = false) Integer rows,
                       @RequestParam(value = "state", required = false) String state, HttpServletResponse response) throws Exception {
        page = page == null ? 1 : page;
        rows = rows == null ? 10 : rows;
        PageBean pageBean = new PageBean(page,rows);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("state", state); // 评论状态
        List<Comment> commentList = commentService.list(map);
        Long total = commentService.getTotal(map);
        JSONObject result = new JSONObject();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateUtil("yyyy-MM-dd"));
        JSONArray jsonArray = JSONArray.fromObject(commentList, jsonConfig);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
    }

    /**
     * TODO 删除评论信息
     *
     * @param ids id集合
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete(@RequestParam(value = "ids") String ids, HttpServletResponse response) throws Exception {
        String[] idsStr = ids.split(",");
        for (String id: idsStr) {
            commentService.delete(Integer.parseInt(id));
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
    }

    /**
     * TODO 评论审核
     *
     * @param ids id集合
     * @param state 审核状态
     */
    @RequestMapping(value = "/review")
    public void review(@RequestParam(value = "ids") String ids,
                         @RequestParam(value = "state") Integer state, HttpServletResponse response) throws Exception {
        String[] idsStr = ids.split(",");
        for (String id : idsStr) {
            Comment comment = new Comment();
            comment.setState(state);
            commentService.updateById(Integer.parseInt(id), comment);
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
    }
}
