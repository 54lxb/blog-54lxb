package cn.lxb.blog.other.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 获取配置文件
 * @author 54LXB.
 * @apiNote 知识改变命运，技术改变世界。
 * @since 2017-11-25.
 */
public class BlogConfig extends PropertyPlaceholderConfigurer {

    private static Map<String, Object> ctxPropertiesMap;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {

        super.processProperties(beanFactory, props);
        //load properties to ctxPropertiesMap  
        ctxPropertiesMap = new HashMap<>();
        for (Object key : props.keySet()) {
            if (null == key) continue;
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }
    }

    //static method for accessing context properties  
    public static String getContextStringProperty(String name) {
        if (null == ctxPropertiesMap.get(name)) {
            return null;
        }
        return String.valueOf(ctxPropertiesMap.get(name));
    }

    //static method for accessing context properties
    public static Boolean getContextBooleanProperty(String name) {
        if (null == ctxPropertiesMap.get(name)) {
            return null;
        }
        return Boolean.parseBoolean(String.valueOf(ctxPropertiesMap.get(name)));
    }

    //static method for accessing context properties
    public static Integer getContextIntegerProperty(String name) {
        if (null == ctxPropertiesMap.get(name)) {
            return null;
        }
        return Integer.parseInt(String.valueOf(ctxPropertiesMap.get(name)));
    }

    public String getBaseUrl() {
        return getContextStringProperty("baidu.task.baseUrl");
    }

    public String getPostUrl() {
        return getContextStringProperty("baidu.task.postUrl");
    }

    public String getAccessKey() {
        return getContextStringProperty("qiniu.accessKey");
    }

    public String getSecretKey() {
        return getContextStringProperty("qiniu.secretKey");
    }

    public String getBucketName() {
        return getContextStringProperty("qiniu.bucketName");
    }

    public String getBasePath() {
        return getContextStringProperty("qiniu.basePath");
    }
}