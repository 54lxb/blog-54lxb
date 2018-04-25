package cn.lxb.blog.service.Impl;

import cn.lxb.blog.entity.Blogger;
import cn.lxb.blog.service.BloggerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Andy on 2017/3/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class BloggerServiceImplTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BloggerService bloggerService;

    @Test
    public void find() throws Exception {
        Blogger blogger = bloggerService.find();
        if (blogger != null) {
            logger.info("查找博主操作记录：{}", blogger);
        } else {
            logger.info("查找博主操作记录：{}", "找不到任何已经注册了的博主！");
        }
    }

    @Test
    public void getByUserName() throws Exception {
        Blogger blogger = bloggerService.getByUserName("54lxb");
        if (blogger != null) {
            logger.info("查找博主操作记录：{}", blogger);
        } else {
            logger.info("查找博主操作记录：{}", "找不到任何已经注册了的博主！");
        }
    }

    @Test
    public void updateById() throws Exception {

    }

}