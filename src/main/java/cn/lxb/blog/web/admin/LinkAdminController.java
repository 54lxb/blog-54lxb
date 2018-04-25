package cn.lxb.blog.web.admin;

import cn.lxb.blog.entity.Link;
import cn.lxb.blog.entity.PageBean;
import cn.lxb.blog.service.LinkService;
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
 * 友情链接Controller层
 * Created by Andy on 2017/3/13.
 */
@Controller
@RequestMapping("/admin/link")
public class LinkAdminController {

    @Resource
    private LinkService linkService;

    /**
     * TODO 分页查询友情链接信息
     *
     * @param page 起始页
     * @param rows 查询记录数
     */
    @RequestMapping("/list")
    public String list(@RequestParam(value = "page", required = false) String page,
                       @RequestParam(value = "rows", required = false) String rows, HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<Link> linkList = linkService.list(map);
        Long total = linkService.getTotal(map);
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(linkList);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * TODO 添加或者修改友情链接信息
     *
     * @param link 友情链接信息bean
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(Link link, HttpServletResponse response) throws Exception {
        int resultTotal = 0; // 操作的记录条数
        if (link.getId() == null) {
            resultTotal = linkService.add(link);
        } else {
            Integer id = link.getId();
            resultTotal = linkService.updateById(id, link);
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
     * TODO 删除友情链接信息
     *
     * @param ids id集合
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete(@RequestParam(value = "ids") String ids, HttpServletResponse response) throws Exception {
        String[] idsStr = ids.split(",");
        for (String id : idsStr) {
            linkService.delete(Integer.parseInt(id));
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
    }
}
