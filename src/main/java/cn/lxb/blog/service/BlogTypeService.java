package cn.lxb.blog.service;

import cn.lxb.blog.entity.BlogType;

import java.util.List;
import java.util.Map;

/**
 * 博客类型Service接口
 * Created by Andy on 2017/3/13.
 */
public interface BlogTypeService {

    /**
     * 查询所有博客类型 以及对应的博客数量
     * @return
     */
    public List<BlogType> countList() throws Exception;

    /**
     * 分页查询博客类别信息
     * @param map
     * @return
     */
    public List<BlogType> list(Map<String,Object> map) throws Exception;

    /**
     * 获取总记录数
     * @param map
     * @return
     */
    public Long getTotal(Map<String,Object> map) throws Exception;

    /**
     * 添加博客类别信息
     * @param blogType
     * @return
     */
    public Integer add(BlogType blogType) throws Exception;

    /**
     * 修改博客类别信息
     * @param blogType
     * @return
     */
    public Integer updateById(Integer id, BlogType blogType) throws Exception;

    /**
     * 删除博客类别信息
     * @param id
     * @return
     */
    public Integer delete(Integer id) throws Exception;
}
