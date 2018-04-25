package cn.lxb.blog.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * TODO 自定义加密工具
 * Created by Andy on 2017/3/13.
 */
public class MD5Util {

    private static final String SALT = "I!a,m@a<C$l%e^V&e*r+L~x-B.";

    private MD5Util() {}

    /**
     * Md5加密
     *
     * @param str 密码字符串
     * @return 加密后的密码
     */
    public static String md5(String str) {
        return new Md5Hash(str, SALT).toString();
    }

    /**
     * Md5加密
     *
     * @param str  密码字符串
     * @param salt 加密盐值
     * @return 加密后的密码
     */
    public static String md5(String str, String salt) {
        return new Md5Hash(str, salt).toString();
    }

}
