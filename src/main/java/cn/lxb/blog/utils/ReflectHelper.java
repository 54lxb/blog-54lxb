package cn.lxb.blog.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * <p>
 * description：反射工具
 * </p>
 *
 * @author 54LXB.
 * @apiNote 知识改变命运，技术改变世界。
 * @since 2017-11-25.
 */
public class ReflectHelper {

    private static Logger logger = LoggerFactory.getLogger(ReflectHelper.class);

    /**
     * 获取obj对象fieldName的Field
     *
     * @param obj       Object
     * @param fieldName 属性
     * @return Object
     * @author 54LXB.
     * @apiNote 知识改变命运，技术改变世界。
     * @since 2017-11-25.
     */
    public static Field getFieldByFieldName(Object obj, String fieldName) {
        if (obj == null || fieldName == null) {
            return null;
        }
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }
        return null;
    }

    /**
     * 获取obj对象fieldName的属性值
     *
     * @param obj       Object
     * @param fieldName 属性
     * @return Object
     * @author 54LXB.
     * @apiNote 知识改变命运，技术改变世界。
     * @since 2017-11-25.
     */
    public static Object getValueByFieldName(Object obj, String fieldName) {
        Object value = null;
        try {
            Field field = getFieldByFieldName(obj, fieldName);
            if (field != null) {
                if (field.isAccessible()) {
                    value = field.get(obj);
                } else {
                    field.setAccessible(true);
                    value = field.get(obj);
                    field.setAccessible(false);
                }
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return value;
    }

    /**
     * 获取obj对象fieldName的属性值
     *
     * @param obj       Object
     * @param fieldType Class<T>
     * @return T
     * @author 54LXB.
     * @apiNote 知识改变命运，技术改变世界。
     * @since 2017-11-25.
     */
    public static <T> T getValueByFieldType(Object obj, Class<T> fieldType) {
        Object value = null;
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                Field[] fields = superClass.getDeclaredFields();
                for (Field f : fields) {
                    if (f.getType() == fieldType) {
                        if (f.isAccessible()) {
                            value = f.get(obj);
                            break;
                        } else {
                            f.setAccessible(true);
                            value = f.get(obj);
                            f.setAccessible(false);
                            break;
                        }
                    }
                }
                if (value != null) {
                    break;
                }
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }
        return (T) value;
    }

    /**
     * 设置obj对象fieldName的属性值
     *
     * @param obj       Object
     * @param fieldName String
     * @param value     Object
     * @throws Exception
     * @author 54LXB.
     * @apiNote 知识改变命运，技术改变世界。
     * @since 2017-11-25.
     */
    public static boolean setValueByFieldName(Object obj, String fieldName, Object value) {
        try {
            // java.lang.Class.getDeclaredField()方法用法实例教程 -
            // 方法返回一个Field对象，它反映此Class对象所表示的类或接口的指定已声明字段。
            // 此方法返回这个类中的指定字段的Field对象
            Field field = obj.getClass().getDeclaredField(fieldName);
            /**
             * public void setAccessible(boolean flag) throws
             * SecurityException将此对象的 accessible 标志设置为指示的布尔值。值为 true
             * 则指示反射的对象在使用时应该取消 Java 语言访问检查。值为 false 则指示反射的对象应该实施 Java 语言访问检查。
             * 首先，如果存在安全管理器，则在 ReflectPermission("suppressAccessChecks") 权限下调用
             * checkPermission 方法。 如果 flag 为 true，并且不能更改此对象的可访问性（例如，如果此元素对象是
             * Class 类的 Constructor 对象），则会引发 SecurityException。 如果此对象是
             * java.lang.Class 类的 Constructor 对象，并且 flag 为 true，则会引发
             * SecurityException。 参数： flag - accessible 标志的新值 抛出：
             * SecurityException - 如果请求被拒绝。
             */
            if (field.isAccessible()) {// 获取此对象的 accessible 标志的值。
                field.set(obj, value);// 将指定对象变量上此 Field 对象表示的字段设置为指定的新值
            } else {
                field.setAccessible(true);
                field.set(obj, value);
                field.setAccessible(false);
            }
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return false;
    }
}
