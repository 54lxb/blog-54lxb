package cn.lxb.blog.service;

import cn.lxb.blog.entity.Blog;

import java.util.List;
import java.util.Map;

/**
 * 博客Service接口
 * Created by Andy on 2017/3/13.
 */
public interface BlogService {

    /**
     * 更新博文点击量
     * @param id 博文id
     * @return
     * @throws Exception
     */
    public Integer addClickHit(Integer id) throws Exception;

    /**
     * 根据日期月份分组查询
     * @return
     */
    public List<Blog> countList() throws Exception;

    /**
     * 分页查询博客
     * @return
     */
    public List<Blog> list(Map<String,Object> map) throws Exception;

    /**
     * 获取总记录数
     * @param map
     * @return
     */
    public Long getTotal(Map<String,Object> map) throws Exception;

    /**
     * 通过Id查找实体
     * @param id
     * @return
     */
    public Blog findById(Integer id) throws Exception;

    /**
     * 更新博客信息
     * @param id
     * @param blog
     * @return
     */
    public Integer updateById(Integer id, Blog blog) throws Exception;

    /**
     * 获取上一个博客
     * @param id
     * @return
     */
    public Blog getLastBlog(Integer id) throws Exception;

    /**
     * 获取下一个博客
     * @param id
     * @return
     */
    public Blog getNextBlog(Integer id) throws Exception;

    /**
     * 添加博客信息
     * @param blog
     * @return
     */
    public Integer add(Blog blog) throws Exception;

    /**
     * 删除博客信息
     * @param id
     * @return
     */
    public Integer delete(Integer id) throws Exception;

    /**
     * 查询指定的博客类别下的博客数量
     * @param typeId
     * @return
     */
    public Integer getBlogByTypeId(Integer typeId) throws Exception;

    /**
     * 查询所有博客文章
     * @return 博客文章ID数组
     * @throws Exception
     */
    List<String> getBlogIds() throws Exception;
}
