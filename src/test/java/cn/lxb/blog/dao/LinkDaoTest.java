package cn.lxb.blog.dao;

import cn.lxb.blog.entity.Link;
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
public class LinkDaoTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LinkDao linkDao;

    @Test
    public void add() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void list() throws Exception {
        Map<String, Object> map = new LinkedMap();
        List<Link> linkList = linkDao.list(map);
        if (linkList != null && !linkList.isEmpty()) {
            logger.info("友情链接列表查询：{}", linkList);
        } else {
            logger.info("友情链接列表查询：{}", "博主还没有添加任何友情链接！");
        }
    }

    @Test
    public void getTotal() throws Exception {
        Map<String, Object> map = new LinkedMap();
        Long total = linkDao.getTotal(map);
        if (total > 0) {
            logger.info("友情链接列表总记录查询：{}", total);
        } else {
            logger.info("友情链接列表总记录查询：{}", "还没有添加任何记录！");
        }
    }

    @Test
    public void delete() throws Exception {

    }

}