package cn.lxb.blog.dao;

import cn.lxb.blog.entity.BlogType;

import java.util.List;
import java.util.Map;

/**
 * 博客类型Dao接口
 * Created by Andy on 2017/3/12.
 */
public interface BlogTypeDao {
    /**
     * 查询所有博客类型 以及对应的博客数量
     *
     * @return 找到的所有博客类型以及对应的博客数量的集合或者empty
     */
    public List<BlogType> countList() throws Exception;

    /**
     * 通过id查询博客类型
     *
     * @param id 博客id
     * @return 找到的博客或者null
     */
    public BlogType findById(Integer id) throws Exception;

    /**
     * 分页查询博客类别信息
     *
     * @param map
     * @return 找到博客类型集合或者empty
     */
    public List<BlogType> list(Map<String, Object> map) throws Exception;

    /**
     * 获取总记录数
     *
     * @param map
     * @return 找的的总记录数
     */
    public Long getTotal(Map<String, Object> map) throws Exception;

    /**
     * 添加博客类别信息
     *
     * @param blogType 要添加的博客类型信息bean
     * @return 影响的行数
     */
    public Integer add(BlogType blogType) throws Exception;

    /**
     * 修改博客类别信息
     *
     * @param blogType 要修改的博客类型信息bean
     * @return 影响的行数
     */
    public Integer update(BlogType blogType) throws Exception;

    /**
     * 删除博客类别信息
     *
     * @param id 博客id
     * @return 影响的行数
     */
    public Integer delete(Integer id) throws Exception;
}
