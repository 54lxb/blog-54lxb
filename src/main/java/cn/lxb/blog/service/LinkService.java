package cn.lxb.blog.service;

import cn.lxb.blog.entity.Link;

import java.util.List;
import java.util.Map;

/**
 * 友情链接Service接口
 * Created by Andy on 2017/3/13.
 */
public interface LinkService {
    /**
     * 添加友情链接
     * @param link
     * @return
     */
    public Integer add(Link link) throws Exception;

    /**
     * 修改友情链接
     * @param id
     * @param link
     * @return
     */
    public Integer updateById(Integer id, Link link) throws Exception;

    /**
     * 查找友情链接信息
     * @param map
     * @return
     */
    public List<Link> list(Map<String,Object> map) throws Exception;

    /**
     * 获取总记录数
     * @param map
     * @return
     */
    public Long getTotal(Map<String,Object> map) throws Exception;

    /**
     * 删除友情链接
     * @param id
     * @return
     */
    public Integer delete(Integer id) throws Exception;
}
