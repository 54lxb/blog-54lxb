package cn.lxb.blog.web.admin;

import cn.lxb.blog.constant.BlogConstant;
import cn.lxb.blog.entity.Blogger;
import cn.lxb.blog.service.BloggerService;
import cn.lxb.blog.utils.DateUtil;
import cn.lxb.blog.utils.MD5Util;
import cn.lxb.blog.utils.ResponseUtil;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * <p>
 * description：管理员博主Controller层
 * </p>
 *
 * @author 54LXB.
 * @apiNote 知识改变命运，技术改变世界。
 * @since 2017-11-25.
 */
@Controller
@RequestMapping("/admin/blogger")
public class BloggerAdminController {

    @Resource
    private BloggerService bloggerService;

    /**
     * TODO　初始化登录界面
     */
    @RequestMapping(value = "/logon", method = RequestMethod.GET)
    public String initLogin() throws Exception {
        return "common/login";
    }

    /**
     * TODO　博主登录
     *
     * @param blogger 博主信息bean
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Blogger blogger, HttpServletRequest request) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(blogger.getUserName(), MD5Util.md5(blogger.getPassword()));
        try {
            // 登录验证
            subject.login(token);
            return "redirect:/admin/main";
        } catch (IncorrectCredentialsException | UnknownAccountException e) {
            request.setAttribute("blogger", blogger);
            request.setAttribute("errorInfo", "账号或密码错误！");
            return BlogConstant.BLOG_LOGIN;
        } catch (LockedAccountException e) {
            request.setAttribute("blogger", blogger);
            request.setAttribute("errorInfo", "非法操作，账号已锁！");
            return BlogConstant.BLOG_LOGIN;
        } catch (ExcessiveAttemptsException e) {
            request.setAttribute("blogger", blogger);
            request.setAttribute("errorInfo", "登录失败多次，账户锁定1小时！");
            return BlogConstant.BLOG_LOGIN;
        }
    }

    /**
     * TODO 博主注销
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() throws Exception {
        SecurityUtils.getSubject().logout();
        return "redirect:/admin/blogger/logon";
    }

    /**
     * TODO 修改博主信息
     *
     * @param blogger 博主信息bean
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@RequestParam("imageFile") MultipartFile imageFile, Blogger blogger,
                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!imageFile.isEmpty()) {
            String filePath = request.getServletContext().getRealPath("/");
            String imageName = DateUtil.getCurrentDateStr() + "." + imageFile.getOriginalFilename().split("\\.")[1];
            imageFile.transferTo(new File(filePath + "static/front/blogger/image/" + imageName));
            blogger.setImageName(imageName);
        }
        Integer id = blogger.getId();
        int resultTotal = bloggerService.updateById(id, blogger);
        StringBuffer result = new StringBuffer();
        if (resultTotal > 0) {
            result.append("<script language='javascript'>alert('修改成功！');</script>");
        } else {
            result.append("<script language='javascript'>alert('修改失败！');</script>");
        }
        ResponseUtil.write(response, result);
    }

    /**
     * TODO 查询博主信息
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public void find(HttpServletResponse response) throws Exception {
        Blogger blogger = bloggerService.find();
        blogger.setPassword(null);
        JSONObject jsonObject = JSONObject.fromObject(blogger);
        ResponseUtil.write(response, jsonObject);
    }

    /**
     * TODO 查询博主信息
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String detailInfo(HttpServletRequest request) throws Exception {
        Blogger blogger = bloggerService.find();
        request.setAttribute("blogger", blogger);
        return "back/blogger/view";
    }


    /**
     * TODO 修改博主密码
     *
     * @param newPassword 新密码
     */
    @RequestMapping(value = "/modifyPassword")
    public void modifyPassword(String newPassword, HttpServletResponse response) throws Exception {
        Blogger blogger = new Blogger();
        String tempPassword = MD5Util.md5(newPassword);
        SimpleHash hash = new SimpleHash("md5", tempPassword, null, 3);
        blogger.setPassword(hash.toHex());
        Integer id = blogger.getId();
        int resultTotal = bloggerService.updateById(id, blogger);
        JSONObject result = new JSONObject();
        result.put("success", resultTotal > 0);
        ResponseUtil.write(response, result);
    }

}
