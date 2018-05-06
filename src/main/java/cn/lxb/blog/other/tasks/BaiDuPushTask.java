package cn.lxb.blog.other.tasks;

import cn.lxb.blog.other.config.BlogConfig;
import cn.lxb.blog.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * 百度推送的工具类
 *
 * @author 54LXB.
 * @apiNote 知识改变命运，技术改变世界。
 * @since 2017-11-25.
 */
@Component
public class BaiDuPushTask {

    @Resource
    private BlogConfig blogConfig;

    @Resource
    private BlogService blogService;

    private Logger logger = LoggerFactory.getLogger(BaiDuPushTask.class);

    /**
     * 自动推送任务
     *
     * @throws IOException
     * @author 54LXB.
     * @apiNote 知识改变命运，技术改变世界。
     * @since 2017-11-25.
     */
    @Scheduled(fixedDelay = 2000000)
    public void postBlogUrl() throws Exception {
        List<String> blogIds = blogService.getBlogIds();
        writerUrl(initConnect(), blogIds);
    }

    /**
     * <p>
     * description：初始化HttpURLConnection
     * </p>
     *
     * @return HttpURLConnection
     * @throws IOException
     * @author 54LXB.
     * @apiNote 知识改变命运，技术改变世界。
     * @since 2017-11-25.
     */
    private HttpURLConnection initConnect() throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(blogConfig.getPostUrl()).openConnection();
        conn.setRequestProperty("Host", "data.zz.baidu.com");
        conn.setRequestProperty("User-Agent", "curl/7.12.1");
        conn.setRequestProperty("Content-Length", "83");
        conn.setRequestProperty("Content-Type", "text/plain");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        return conn;
    }


    /**
     * <p>
     * description：重构推送文章的write方法
     * </p>
     *
     * @param connection HttpURLConnection
     * @param ids        文章ID数组
     * @throws IOException
     * @author 54LXB.
     * @apiNote 知识改变命运，技术改变世界。
     * @since 2017-11-25.
     */
    private void writerUrl(HttpURLConnection connection, List<String> ids) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (String id : ids) {
            sb.append(blogConfig.getBaseUrl()).append("/article/").append(id).append("/detail").append("\n");
        }
        logger.info("》》》》》》》》》》》》》》》》》》》》》推送的url为:\n{}", sb.toString());
        connection.connect();
        PrintWriter out = new PrintWriter(connection.getOutputStream());
        out.print(sb.toString().trim());
        out.flush();
        int code = connection.getResponseCode();
        logger.info("》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》博客文章URL推送{}！", code == 200 ? "成功" : "失败");
    }

    /**
     * <p>
     * description：新增添加文章推送功能
     * </p>
     *
     * @param articleId 文章id
     * @author 54LXB.
     * @apiNote 知识改变命运，技术改变世界。
     * @since 2017-11-25.
     */
    public void pushOneBlog(String articleId) throws Exception {
        writerUrl(initConnect(), Arrays.asList(articleId));
    }
}
