package cn.lxb.blog.web;

import cn.lxb.blog.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * <p>
 *     Description：验证码工具类
 * </p>
 * @author Andy
 * @apiNote 知识改变命运，技术改变世界！
 * @since 2017-09-13 09:00.
 * Created by Andy on 2017/3/13.
 */
@Controller
public class PasscodeController {

    /**
     * <p>
     *     Description：随机颜色
     * </p>
     * @param fc 起始值
     * @param bc 结束值
     * @author Andy
     * @apiNote 知识改变命运，技术改变世界！
     * @since 2017-09-13 09:00.
     */
    private static Color getRandColor(Integer fc, Integer bc) {
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     *
     * <p>
     *     Description：获取验证码
     * </p>
     * @param request 请求
     * @param response 响应
     * @throws Exception
     * @author Andy
     * @apiNote 知识改变命运，技术改变世界！
     * @since 2017-09-13 09:00.
     */
    @RequestMapping(value = "/passcode", method = RequestMethod.GET)
    public void generatePasscode(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 设置不缓存头部
        ResponseUtil.setContent(response);

        int width = 60, height = 20;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        StringBuilder passcode = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            passcode.append(rand);
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }
        // 将验证码存入SESSION
        request.getSession().setAttribute("passcode", passcode.toString());
        g.dispose();
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

}
