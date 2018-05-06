package cn.lxb.blog.web;

import cn.lxb.blog.constant.BlogConstant;
import cn.lxb.blog.service.BloggerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * <p>
 * Description：留言列表管理
 * </P>
 *
 * @author Andy
 * @apiNote 知识改变命运，技术改变世界！
 * @since 2017-09-13 09:00.
 */
@Controller
@RequestMapping("/word")
public class WordController {

    @Resource
    private BloggerService bloggerService;

    /**
     * <p>
     *     description：查看留言列表
     * </p>
     *
     * @param model Model
     * @return 页面路劲
     * @throws Exception
     * @author Andy
     * @apiNote 知识改变命运，技术改变世界！
     * @since 2017-09-13 09:00.
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) throws Exception {
        model.addAttribute("blogger", bloggerService.find());
        model.addAttribute("pageTitle", "大爱我小宝哥");
        model.addAttribute("mainPage", BlogConstant.WORD_LIST);
        return BlogConstant.COMMON_MAIN;
    }

}
