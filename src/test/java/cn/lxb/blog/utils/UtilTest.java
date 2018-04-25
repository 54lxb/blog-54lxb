package cn.lxb.blog.utils;

import org.apache.commons.lang.StringEscapeUtils;
import org.junit.Test;

/**
 * util工具类测试
 * Created by Andy on 2017/3/13.
 */
public class UtilTest {

    @Test
    public void testEncryption() throws Exception {
        String str = MD5Util.md5("54lxb970913.", "I!a,m@a<C$l%e^V&e*r+L~x-B.");
        System.out.println("Md5加密："+str+"；Salt："+"I!a,m@a<C$l%e^V&e*r+L~x-B.");
    }

    @Test
    public void textStringUtil() throws Exception {
        String str="<p>fda</p>";
        System.out.println(StringEscapeUtils.escapeHtml(str));
    }

}
