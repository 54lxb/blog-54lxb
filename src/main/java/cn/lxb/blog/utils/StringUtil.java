package cn.lxb.blog.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TODO 字符串工具类
 * Created by Andy on 2017/3/13.
 */
public class StringUtil {

    /**
     * TODO 字符串判断是否是空
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * TODO 字符串判断是否不是空
     */
    public static boolean isNotEmpty(String str) {
        return (str != null) && !"".equals(str.trim());
    }

    /**
     * TODO list集合判断是否是空
     */
    public static boolean isEmptyList(List list) {
        return list == null || list.isEmpty();
    }

    /**
     * TODO list集合判断是否不是空
     */
    public static boolean isNotEmptyList(List list) {
        return list != null && !list.isEmpty();
    }


    /**
     * TODO map集合判断是否是空
     */
    public static boolean isEmptyMap(Map map) {
        return map == null || map.isEmpty();
    }

    /**
     * TODO map集合判断是否不是空
     */
    public static boolean isNotEmptyMap(Map map) {
        return map != null && !map.isEmpty();
    }

    /**
     * TODO 字符串格式化模糊查询
     */
    public static String formatLike(String str) {
        if (isNotEmpty(str)) {
            return "%" + str + "%";
        } else {
            return null;
        }
    }

    /**
     * TODO 过滤掉集合里的空格
     */
    public static List<String> filterWhite(List<String> list) {
        List<String> resultList = new ArrayList<String>();
        for (String str : list) {
            if (isNotEmpty(str)) {
                resultList.add(str);
            }
        }
        return resultList;
    }

    /**
     * 删除所有的HTML标签
     *
     * @param source 需要进行除HTML的文本
     * @return 没有HTML标签的文本
     */
    public static String deleteAllHTMLTag(String source) {
        // 非空校验
        if (source == null) return "";
        String target = source;
        // 删除普通标签
        target = target.replaceAll("<(S*?)[^>]*>.*?|<.*? />", "");
        target = target.replaceAll("&.{2,6}?;", "");
        return target;
    }
}
