package cn.lxb.blog.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式验证工具
 *
 * @author Andy
 * @date 2017年8月22日
 * @Description:
 */
public class VerifyUtil {

    /**
     * 邮箱验证
     *
     * @param email
     * @return
     */
    public static boolean emailVerify(String email) {
        if (StringUtil.isEmpty(email))
            return false;
        Pattern p = Pattern.compile("^[A-Za-z0-9](([_\\.\\-]?[a-zA-Z0-9]+)*)@([A-Za-z0-9]+)(([\\.\\-]?[a-zA-Z0-9]+)*)\\.([A-Za-z]{2,})$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 手机格式验证(简单验证）
     *
     * @param phone
     * @return
     */
    public static boolean phoneVerify(String phone) {
        if (StringUtil.isEmpty(phone))
            return false;
        Pattern p = Pattern.compile("^((0\\d{2,3}-\\d{7,8})|(1\\d{10}))$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    /**
     * 用户名合法验证（汉字、数字、字母、下划线组成，不能以数字或下划线开头，不能用下划线结尾，2位以上）
     *
     * @param username
     * @return
     */
    public static boolean usernameVerify(String username) {
        if (StringUtil.isEmpty(username))
            return false;
        Pattern p = Pattern.compile("^(?![0-9_])(?!.*_$)[a-zA-Z0-9_\u4e00-\u9fa5]{2,19}$");
        Matcher m = p.matcher(username);
        return m.matches();
    }

    /**
     * 密码合法验证
     *
     * @param pwd
     * @return true : 通过 false : 未通过
     */
    public static boolean passwordVerify(String pwd) {
        if (StringUtil.isEmpty(pwd)) return false;
        // =================^[\\~!@#$%^&*()-_=+|{}\[\],.?\/:;\'\"\d\w]{'.$minLen.','.$maxLen.'}$
        return pwd.matches("^[\\\\~!@#$%^&*()-_=+|{}\\[\\],.?\\/:;\\'\\\"\\d\\w]{6,16}$") && !pwd.matches("[0-9]*") && !pwd.matches("[a-zA-Z]*");
    }

    /**
     * 金额验证，最多两位小数
     *
     * @param str
     * @return
     */
    public static boolean isMoney(String str) {
        return StringUtil.isNotEmpty(str) && Pattern.compile("^[0-9]{1,9}([.][0-9]{1,2})?$").matcher(str).matches();
    }

    /**
     * 验证是否是整数
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        return StringUtil.isNotEmpty(str) && Pattern.compile("^[0-9]{1,20}$").matcher(str).matches();
    }

    /**
     * 联系方式验证（固定电话和手机：支持区号）
     *
     * @param contact
     * @return
     */
    public static boolean contactPhone(String contact) {
        Pattern p = Pattern
                .compile("^(\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$");
        Matcher matcher = p.matcher(contact);
        return matcher.matches();
    }

    /**
     * 身份证格式验证
     *
     * @param idCard
     * @return
     */
    public static boolean idCard(String idCard) {
        if (StringUtil.isEmpty(idCard)) return false;
        Pattern p = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$");
        Matcher m = p.matcher(idCard);
        return m.matches();
    }

    /**
     * 中文字符串验证,不能有空格
     *
     * @param str
     * @return
     */
    public static boolean isChinese(String str) {
        if (StringUtil.isEmpty(str)) return false;
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]*");
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
