package cn.lxb.blog.utils;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO 日期工具类
 * Created by Andy on 2017/3/13.
 */
public class DateUtil implements JsonValueProcessor {

    /**
     * TODO 日期对象转字符串
     *
     * @param date 要转换的时间
     * @param format 目标格式
     * @return 转换后的结果
     */
    public static String formatDate(Date date, String format) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null) {
            result = sdf.format(date);
        }
        return result;
    }

    /**
     * TODO 字符串转日期对象
     *
     * @param str 要转换的字符串
     * @param format 目标格式
     * @return 转换后的时间
     */
    public static Date formatString(String str, String format) throws Exception {
        if (StringUtil.isEmpty(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(str);
    }

    /**
     * TODO 按默认格式获取当前时间，并转换为字符串
     */
    public static String getCurrentDateStr() throws Exception {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        return sdf.format(date);
    }

    private String format = "yyyy-MM-dd HH:mm:ss";

    public String getFormat() {
        return format;
    }

    public DateUtil(String format) {
        this.format = format;
    }

    @Override
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        String[] obj = {};
        if (value instanceof Date[]) {
            Date[] dates = (Date[]) value;
            obj = new String[dates.length];
            for (int i = 0; i < dates.length; i++) {
                obj[i] = DateUtil.formatDate(dates[i], format);
            }
        }
        return obj;
    }

    @Override
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        if (value instanceof Date) {
            return new SimpleDateFormat(format).format(value);
        }
        return value == null ? null : value.toString();
    }
}
