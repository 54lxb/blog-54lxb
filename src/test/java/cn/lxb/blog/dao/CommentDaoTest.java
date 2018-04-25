package cn.lxb.blog.dao;

import cn.lxb.blog.entity.Comment;
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
public class CommentDaoTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CommentDao commentDao;

    @Test
    public void add() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void list() throws Exception {
        Map<String, Object> map = new LinkedMap();
        map.put("blogId", "1");
        List<Comment> commentList = commentDao.list(map);
        if (commentList != null && !commentList.isEmpty()) {
            logger.info("根据ID查询博客回复内容：{}", commentList);
        } else {
            logger.info("根据ID查询博客回复内容：{}", "博主人员太差，至今无人问津！");
        }
    }

    @Test
    public void getTotal() throws Exception {
        Map<String, Object> map = new LinkedMap();
        map.put("state", "0");
        Long total = commentDao.getTotal(map);
        if (total > 0) {
            logger.info("根据回帖审核状态统计回帖数：{}", total);
        } else {
            logger.info("根据回帖审核状态统计回帖数：{}", "未找到任何符合此类型的回帖！");
        }
    }

    @Test
    public void delete() throws Exception {

    }

}