package cn.lxb.blog.service.Impl;

import cn.lxb.blog.dao.BloggerDao;
import cn.lxb.blog.entity.Blogger;
import cn.lxb.blog.service.BloggerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 博主信息实现类
 * Created by Andy on 2017/3/12.
 */
@Service
public class BloggerServiceImpl implements BloggerService {

    @Resource
    private BloggerDao bloggerDao;

    @Override
    public Blogger find() throws Exception {
        return bloggerDao.find();
    }

    @Override
    public Blogger getByUserName(String userName) throws Exception {
        return bloggerDao.getByUserName(userName);
    }

    @Override
    public Integer updateById(Integer id, Blogger blogger) throws Exception {
        blogger.setId(id);
        return bloggerDao.update(blogger);
    }
}
