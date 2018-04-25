package cn.lxb.blog.dao;

import cn.lxb.blog.entity.Blog;
import org.apache.commons.collections.map.LinkedMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by Andy on 2017/3/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class BlogDaoTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BlogDao blogDao;

    @Test
    public void countList() throws Exception {
        List<Blog> blogList =  blogDao.countList();
        if (blogList != null && !blogList.isEmpty()) {
            logger.info("博客查询操作记录：{}", blogList);
        } else {
            logger.info("博客查询操作记录：{}", "未找到任何博客帖子！");
        }
    }

    @Test
    public void list() throws Exception {
        Map<String, Object> map = new LinkedMap();
        map.put("typeId", "1");
        List<Blog> blogList = blogDao.list(map);
        if (blogList != null && !blogList.isEmpty()) {
            logger.info("博客查询操作记录：{}", blogList);
        } else {
            logger.info("博客查询操作记录：{}", "未找到任何博客帖子！");
        }
    }

    @Test
    public void getTotal() throws Exception {
        Map<String, Object> map = new LinkedMap();
        Long total = blogDao.getTotal(map);
        if (total > 0) {
            logger.info("博客查询操作记录：{}", total);
        } else {
            logger.info("博客查询操作记录：{}", "总操作记录为0！");
        }
    }

    @Test
    public void findById() throws Exception {
        Blog blog = blogDao.findById(1);
        if (blog != null) {
            logger.info("博客操作查询记录：{}", blog);
        } else {
            logger.info("博客查询操作记录：{}", "您所查找的帖子不存在！");
        }
    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void getLastBlog() throws Exception {

    }

    @Test
    public void getNextBlog() throws Exception {

    }

    @Test
    public void add() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void getBlogByTypeId() throws Exception {

    }

}