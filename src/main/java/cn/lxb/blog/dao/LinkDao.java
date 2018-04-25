package cn.lxb.blog.dao;

import cn.lxb.blog.entity.Link;

import java.util.List;
import java.util.Map;

/**
 * 友情链接Dao接口
 * Created by Andy on 2017/3/12.
 */
public interface LinkDao {

    /**
     * 添加友情链接
     *
     * @param link 要添加的友情链接信息bean
     * @return 影响的行数
     */
    public Integer add(Link link) throws Exception;

    /**
     * 修改友情链接
     *
     * @param link 要修改的友情连接信息bean
     * @return 影响的行数
     */
    public Integer update(Link link) throws Exception;

    /**
     * 查找友情链接信息
     *
     * @param map
     * @return 找到的友情连接信息bean或者empty
     */
    public List<Link> list(Map<String, Object> map) throws Exception;

    /**
     * 获取总记录数
     *
     * @param map
     * @return 查询到的总记录数
     */
    public Long getTotal(Map<String, Object> map) throws Exception;

    /**
     * 删除友情链接
     *
     * @param id 博客id
     * @return 影响的行数
     */
    public Integer delete(Integer id) throws Exception;
}
