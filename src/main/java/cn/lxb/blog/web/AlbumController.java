package cn.lxb.blog.web;

import cn.lxb.blog.constant.BlogConstant;
import cn.lxb.blog.service.BloggerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by Andy on 2017/3/18.
 */
@Controller
@RequestMapping("/album")
public class AlbumController {
    @Resource
    private BloggerService bloggerService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) throws Exception {
        model.addAttribute("blogger", bloggerService.find());
        model.addAttribute("pageTitle", "大爱我小宝哥");
        model.addAttribute("mainPage", BlogConstant.ALBUM_LIST);
        return BlogConstant.COMMON_MAIN;
    }
}
