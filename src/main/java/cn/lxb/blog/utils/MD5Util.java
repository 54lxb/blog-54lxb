package cn.lxb.blog.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * <p>
 * description：自定义加密工具
 * </p>
 *
 * @author 54LXB.
 * @apiNote 知识改变命运，技术改变世界。
 * @since 2017-11-25.
 */
public class MD5Util {

    private static final String SALT = "I!a,m@a<C$l%e^V&e*r+L~x-B.";

    private MD5Util() {
    }

    /**
     * <p>
     * description：Md5加密
     * </p>
     *
     * @param str 密码字符串
     * @return 加密后的密码
     * @author 54LXB.
     * @apiNote 知识改变命运，技术改变世界。
     * @since 2017-11-25.
     */
    public static String md5(String str) {
        return new Md5Hash(str, SALT).toString();
    }

    /**
     * <p>
     * description：Md5加密
     * </p>
     *
     * @param str  密码字符串
     * @param salt 加密盐值
     * @return 加密后的密码
     * @author 54LXB.
     * @apiNote 知识改变命运，技术改变世界。
     * @since 2017-11-25.
     */
    public static String md5(String str, String salt) {
        return new Md5Hash(str, salt).toString();
    }

}
