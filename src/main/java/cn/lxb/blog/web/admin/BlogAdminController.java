package cn.lxb.blog.web.admin;

import cn.lxb.blog.constant.MsgCode;
import cn.lxb.blog.constant.MsgInfo;
import cn.lxb.blog.constant.MsgLevel;
import cn.lxb.blog.dto.MsgBean;
import cn.lxb.blog.entity.Blog;
import cn.lxb.blog.entity.PageBean;
import cn.lxb.blog.service.BlogIndexService;
import cn.lxb.blog.service.BlogService;
import cn.lxb.blog.utils.DateUtil;
import cn.lxb.blog.utils.ResponseUtil;
import cn.lxb.blog.utils.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员博客Controller层
 * Created by Andy on 2017/3/13.
 */
@Controller
@RequestMapping("/admin/article")
public class BlogAdminController {

    @Resource
    private BlogService blogService;

    @Resource
    private BlogIndexService blogIndexService;

    /**
     * TODO 添加博客信息
     *
     * @param blog 博客信息bean
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(Blog blog, HttpServletResponse response) throws Exception {

        //添加博客
        int resultTotal = blogService.add(blog);
        blogIndexService.addIndex(blog); // 添加博客索引

        JSONObject result = new JSONObject();
        result.put("success", resultTotal > 0);
        ResponseUtil.write(response, result);
    }

    /**
     * TODO 修改博客信息
     *
     * @param blog 博客信息bean
     */
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public void update(@PathVariable("id") Integer id, Blog blog, HttpServletResponse response) throws Exception {
        // 更新博客
        int resultTotal = blogService.updateById(id, blog);
        // 更新博客索引
        blogIndexService.updateIndex(blog);
        JSONObject result = new JSONObject();
        result.put("success", resultTotal > 0);
        ResponseUtil.write(response, result);
    }

    /**
     * TODO 通过ID查找实体
     *
     * @param id 博客id
     */
    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    @ResponseBody
    public MsgBean detail(@PathVariable("id") Integer id) throws Exception {
        Blog blog = blogService.findById(id);
        return new MsgBean(MsgCode.SUCCESS, MsgInfo.SUCCESS, MsgLevel.NORMAL).setData(blog);
    }

    /**
     * TODO 分页查询博客信息
     *
     * @param page   起始页
     * @param rows   查询记录数
     * @param s_blog 查询关键字
     */
    @RequestMapping(value = "/list")
    public void list(@RequestParam(value = "page", required = false) Integer page,
                     @RequestParam(value = "rows", required = false) Integer rows,
                     Blog s_blog, HttpServletResponse response) throws Exception {
        page = page == null ? 1 : page;
        rows = rows == null ? 10 : rows;
        PageBean pageBean = new PageBean(page, rows);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", StringUtil.formatLike(s_blog.getTitle()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<Blog> blogList = blogService.list(map);
        Long total = blogService.getTotal(map);
        JSONObject result = new JSONObject();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateUtil("yyyy-MM-dd"));
        JSONArray jsonArray = JSONArray.fromObject(blogList, jsonConfig);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
    }


    /**
     * TODO 删除博客信息
     *
     * @param ids id集合数组
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(@RequestParam(value = "ids") String ids, HttpServletResponse response) throws Exception {
        String[] idsStr = ids.split(",");
        for (String id : idsStr) {
            blogService.delete(Integer.parseInt(id));
            // 删除对应博客的索引
            blogIndexService.deleteIndex(id);
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
    }

}
