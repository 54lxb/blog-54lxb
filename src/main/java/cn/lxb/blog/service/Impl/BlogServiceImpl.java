package cn.lxb.blog.service.Impl;

import cn.lxb.blog.dao.BlogDao;
import cn.lxb.blog.entity.Blog;
import cn.lxb.blog.service.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <P>
 *  Description：博客Service实现类
 * </P>
 * @author Andy
 * @since 2017-09-13 09:00.
 * @apiNote 知识改变命运，技术改变世界！
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogDao blogDao;

    @Override
    public Integer addClickHit(Integer id) throws Exception {
        return blogDao.addClickHit(id);
    }

    public List<Blog> countList() throws Exception {
        return blogDao.countList();
    }

    public List<Blog> list(Map<String, Object> map) throws Exception {
        return blogDao.list(map);
    }

    public Long getTotal(Map<String, Object> map) throws Exception {
        return blogDao.getTotal(map);
    }

    public Blog findById(Integer id) throws Exception {
        return blogDao.findById(id);
    }

    public Integer updateById(Integer id, Blog blog) throws Exception {
        blog.setId(id);
        return blogDao.update(blog);
    }

    public Blog getLastBlog(Integer id) throws Exception {
        return blogDao.getLastBlog(id);
    }

    public Blog getNextBlog(Integer id) throws Exception {
        return blogDao.getNextBlog(id);
    }

    public Integer add(Blog blog) throws Exception {
        return blogDao.add(blog);
    }

    public Integer delete(Integer id) throws Exception {
        return blogDao.delete(id);
    }

    public Integer getBlogByTypeId(Integer typeId) throws Exception {
        return blogDao.getBlogByTypeId(typeId);
    }

    @Override
    public List<String> getBlogIds() throws Exception {
        return blogDao.getBlogIds();
    }
}
