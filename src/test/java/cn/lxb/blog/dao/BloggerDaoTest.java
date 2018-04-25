package cn.lxb.blog.dao;

import cn.lxb.blog.entity.Blogger;
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
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class BloggerDaoTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BloggerDao bloggerDao;

    @Test
    public void find() throws Exception {
        Blogger blogger = bloggerDao.find();
        if (blogger != null) {
            logger.info("查找博主操作记录：{}", blogger);
        } else {
            logger.info("查找博主操作记录：{}", "找不到任何已经注册了的博主！");
        }
    }

    @Test
    public void getByUserName() throws Exception {
        Blogger blogger = bloggerDao.getByUserName("54lxb");
        if (blogger != null) {
            logger.info("查找博主操作记录：{}", blogger);
        } else {
            logger.info("查找博主操作记录：{}", "找不到任何已经注册了的博主！");
        }
    }

    @Test
    public void update() throws Exception {

    }

}