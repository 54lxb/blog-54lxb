package cn.lxb.blog.web;

import cn.lxb.blog.entity.Blog;
import cn.lxb.blog.entity.PageBean;
import cn.lxb.blog.constant.BlogConstant;
import cn.lxb.blog.service.BlogService;
import cn.lxb.blog.utils.PageUtil;
import cn.lxb.blog.utils.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 博客信息公共控制类
 * Created by Andy on 2017/3/13.
 */
@Controller
public class BlogInitController {

    @Resource
    private BlogService blogService;

    /**
     * TODO 初始化管理员主页
     */
    @RequestMapping(value = "/admin/main", method = RequestMethod.GET)
    public String initMain() throws Exception {
        return "back/common/main";
    }

    /**
     * TODO 初始化其他页面
     * @param name 要初始化的页面名称
     */
    @RequestMapping(value = "/admin/{name}/{value}", method = RequestMethod.GET)
    public String initBlog(@PathVariable("name") String name, @PathVariable("value") String value, Model model) throws Exception {
        boolean isNum = name.matches("[0-9]+");
        if (isNum) {
            model.addAttribute("id", Integer.valueOf(name));
            return "back/blog/"+value;
        }
        return "back/"+name+"/"+value;
    }

    /**
     * TODO 实例化博客默认主页
     *
     * @param page           页数
     * @param typeId         文章类型id
     * @param releaseDateStr 发表时间
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "typeId", required = false) String typeId,
                        @RequestParam(value = "releaseDateStr", required = false) String releaseDateStr, Model model, HttpSession session) throws Exception {
        page = StringUtil.isEmpty(page) ? "1":page;
        PageBean pageBean = new PageBean(Integer.parseInt(page), BlogConstant.DEFAULT_RECORDS);

        Map<String, Object> map = new HashMap<>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("typeId", typeId);
        map.put("releaseDateStr", releaseDateStr);

        List<Blog> blogList = blogService.list(map);
        for (Blog blog : blogList) {
            List<String> imagesList = blog.getImagesList();
            String blogInfo = blog.getContent();
            Document doc = Jsoup.parse(blogInfo);
            Elements jpgs = doc.select("img[src$=.jpg]"); //　查找扩展名是jpg的图片
            for (int i = 0; i < jpgs.size(); i++) {
                Element jpg = jpgs.get(i);
                imagesList.add(jpg.toString());
                if (i == 2) {
                    break;
                }
            }
        }

        //存储主页的初始化参数
        Map<String, Object> initParam = new HashMap<String, Object>();
        initParam.put("blogList", blogList);

        String param = ""; // 查询参数
        if (StringUtil.isNotEmpty(typeId)) {
            param += "typeId=" + typeId + "&";
        }
        if (StringUtil.isNotEmpty(releaseDateStr)) {
            param += "releaseDateStr=" + releaseDateStr + "&";
        }
        Long total =  blogService.getTotal(map);
        String url = session.getServletContext().getContextPath() + "/index";
        String pageCode =  PageUtil.genPagination(url, total, Integer.parseInt(page), BlogConstant.DEFAULT_RECORDS, param);

        initParam.put("pageCode", pageCode);
        initParam.put("mainPage", BlogConstant.BLOG_LIST);
        initParam.put("pageTitle", "大爱我小宝哥");

        model.addAllAttributes(initParam);

        return BlogConstant.COMMON_MAIN;
    }

}
