package cn.lxb.blog.service.Impl;

import cn.lxb.blog.dao.LinkDao;
import cn.lxb.blog.entity.Link;
import cn.lxb.blog.service.LinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 友情链接Service实现类
 * Created by Andy on 2017/3/13.
 */
@Service
public class LinkServiceImpl implements LinkService {

    @Resource
    private LinkDao linkDao;

    @Override
    public Integer add(Link link) throws Exception {
        return linkDao.add(link);
    }

    @Override
    public Integer updateById(Integer id, Link link) throws Exception {
        link.setId(id);
        return linkDao.update(link);
    }

    @Override
    public List<Link> list(Map<String, Object> map) throws Exception {
        return linkDao.list(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) throws Exception {
        return linkDao.getTotal(map);
    }

    @Override
    public Integer delete(Integer id) throws Exception {
        return linkDao.delete(id);
    }
}
