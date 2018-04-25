package cn.lxb.blog.web;

import cn.lxb.blog.constant.BlogConstant;
import cn.lxb.blog.service.BloggerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * 博主Controller层
 * Created by Andy on 2017/3/13.
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {

    @Resource
    private BloggerService bloggerService;

    /**
     * 查找博主信息
     */
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Model model) throws Exception {
        model.addAttribute("blogger", bloggerService.find());
        model.addAttribute("pageTitle", "大爱我小宝哥");
        model.addAttribute("mainPage", BlogConstant.BLOGGER_INFO);
        return BlogConstant.COMMON_MAIN;
    }
}
