package cn.lxb.blog.service.Impl;

import cn.lxb.blog.dao.BlogTypeDao;
import cn.lxb.blog.entity.BlogType;
import cn.lxb.blog.service.BlogTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 博客类型Service实现类
 * Created by Andy on 2017/3/13.
 */
@Service
public class BlogTypeServiceImpl implements BlogTypeService {

    @Resource
    private BlogTypeDao blogTypeDao;

    @Override
    public List<BlogType> countList() throws Exception {
        return blogTypeDao.countList();
    }

    @Override
    public List<BlogType> list(Map<String, Object> map) throws Exception {
        return blogTypeDao.list(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) throws Exception {
        return blogTypeDao.getTotal(map);
    }

    @Override
    public Integer add(BlogType blogType) throws Exception {
        return blogTypeDao.add(blogType);
    }

    @Override
    public Integer updateById(Integer id, BlogType blogType) throws Exception {
        blogType.setId(id);
        return blogTypeDao.update(blogType);
    }

    @Override
    public Integer delete(Integer id) throws Exception {
        return blogTypeDao.delete(id);
    }
}
