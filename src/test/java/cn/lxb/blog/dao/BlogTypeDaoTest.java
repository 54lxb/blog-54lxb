package cn.lxb.blog.dao;

import cn.lxb.blog.entity.BlogType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Andy on 2017/3/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class BlogTypeDaoTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BlogTypeDao blogTypeDao;

    @Test
    public void countList() throws Exception {
        List<BlogType> blogTypeList = blogTypeDao.countList();
        if (blogTypeList != null && !blogTypeList.isEmpty()) {
            logger.info("博客类型查找操作记录：{}", blogTypeList);
        } else {
            logger.info("博客类型查找操作记录：{}", "博主还没有添加博客类型！");
        }
    }

    @Test
    public void findById() throws Exception {

    }

    @Test
    public void list() throws Exception {

    }

    @Test
    public void getTotal() throws Exception {

    }

    @Test
    public void add() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

}