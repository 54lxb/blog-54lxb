package cn.lxb.blog.utils;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

/**
 * json字符串转换工具类
 * Create By Andy On 2017-08-22
 */
public class JsonUtil {

	private JsonUtil() {}

	/**
	 * 
	 * @deprecated
	 */
	public static String getJsonStr(Object obj) {
		return objToJson(obj);
	}

	/**
	 * 将对象转换为json字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String objToJson(Object obj) {
		return JSON.toJSONString(obj);
	}

	/**
	 * json字符串转换为map
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static Map jsonToMap(String jsonStr) {
		return JSON.parseObject(jsonStr, Map.class);
	}

	/**
	 * json字符串转对象
	 * 
	 * @param jsonStr
	 * @param objClass
	 * @param <T>
	 * @return
	 */
	public static <T> T jsonToObj(String jsonStr, Class<T> objClass) {
		return JSON.parseObject(jsonStr, objClass);
	}

	/**
	 * json数组转集合
	 * @param jsonStr
	 * @param classes
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> jsonArrayToList(String jsonStr, Class<T> classes) {
		return JSON.parseArray(jsonStr, classes);
	}
}