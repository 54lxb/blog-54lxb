package cn.lxb.blog.web.admin;

import cn.lxb.blog.entity.BlogType;
import cn.lxb.blog.entity.PageBean;
import cn.lxb.blog.service.BlogService;
import cn.lxb.blog.service.BlogTypeService;
import cn.lxb.blog.utils.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
 * 管理员博客类别Controller层
 * Created by Andy on 2017/3/13.
 */
@Controller
@RequestMapping("/admin/blogType")
public class BlogTypeAdminController {

    @Resource
    private BlogTypeService blogTypeService;

    @Resource
    private BlogService blogService;

    /**
     * TODO 分页查询博客类别信息
     *
     * @param page     页数
     * @param rows     行数
     */
    @RequestMapping(value = "/list")
    public void list(@RequestParam(value = "page", required = false) Integer page,
                     @RequestParam(value = "rows", required = false) Integer rows, HttpServletResponse response) throws Exception {
        page = page == null ? 1 : page;
        rows = rows == null ? 10 : rows;
        PageBean pageBean = new PageBean(page, rows);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<BlogType> blogTypeList = blogTypeService.list(map);
        Long total = blogTypeService.getTotal(map);
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(blogTypeList);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
    }

    /**
     * 添加或者修改博客类别信息
     *
     * @param blogType 博客信息bean
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(BlogType blogType, HttpServletResponse response) throws Exception {
        // 操作的记录条数
        int resultTotal = 0;
        // 判断博客id为是否为空
        if (blogType.getId() == null) {
            //添加博客类型
            resultTotal = blogTypeService.add(blogType);
        } else {
            //修改博客类型
            Integer id = blogType.getId();
            resultTotal = blogTypeService.updateById(id, blogType);
        }

        JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
    }

    /**
     * TODO 删除博客类别信息
     *
     * @param ids 博客id集合
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete(@RequestParam(value = "ids") String ids, HttpServletResponse response) throws Exception {
        String[] idsStr = ids.split(",");
        JSONObject result = new JSONObject();
        for (String id: idsStr) {
            if (blogService.getBlogByTypeId(Integer.parseInt(id)) > 0) {
                result.put("exist", "博客类别下有博客，无法删除！");
            } else {
                blogTypeService.delete(Integer.parseInt(id));
            }
        }
        result.put("success", true);
        ResponseUtil.write(response, result);
    }
}
