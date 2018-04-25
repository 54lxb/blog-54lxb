package cn.lxb.blog.dao;

import cn.lxb.blog.entity.Blog;

import java.util.List;
import java.util.Map;

/**
 * <P>
 *  Description：博客Dao接口
 * </P>
 * @author Andy
 * @since 2017-09-13 09:00.
 * @apiNote 知识改变命运，技术改变世界！
 */
public interface BlogDao {

    /**
     * 更新博文点击量
     * @param id 博文id
     * @return 影响行数
     * @throws Exception
     */
    Integer addClickHit(Integer id) throws Exception;

    /**
     * 根据日期月份分组查询
     *
     * @return 找到的博客集合或者empty
     * @throws Exception
     */
    List<Blog> countList() throws Exception;

    /**
     * 分页查询博客
     *
     * @return 找到的博客集合或者empty
     * @throws Exception
     */
    List<Blog> list(Map<String, Object> map) throws Exception;

    /**
     * 获取总记录数
     *
     * @param map 查询参数
     * @return 总记录数
     * @throws Exception
     */
    Long getTotal(Map<String, Object> map) throws Exception;

    /**
     * 通过Id查找实体
     *
     * @param id 博客文章id
     * @return 找到的博客实体或者null
     * @throws Exception
     */
    Blog findById(Integer id) throws Exception;

    /**
     * 更新博客信息
     *
     * @param blog 要更新博客信息bean
     * @return 影响的行数
     * @throws Exception
     */
    Integer update(Blog blog) throws Exception;

    /**
     * 获取上一个博客
     *
     * @param id 博客id
     * @return 找到的博客或者null
     * @throws Exception
     */
    Blog getLastBlog(Integer id) throws Exception;

    /**
     * 获取下一个博客
     *
     * @param id 博客id
     * @return 找到的博客或者null
     * @throws Exception
     */
    Blog getNextBlog(Integer id) throws Exception;

    /**
     * 添加博客信息
     *
     * @param blog 要添加的博客信息bean
     * @return 影响的行数
     * @throws Exception
     */
    Integer add(Blog blog) throws Exception;

    /**
     * 删除博客信息
     *
     * @param id 博客id
     * @return 影响的行数
     * @throws Exception
     */
    Integer delete(Integer id) throws Exception;

    /**
     * 查询指定的博客类别下的博客数量
     *
     * @param typeId 博客类型id
     * @return 影响的行数
     * @throws Exception
     */
    Integer getBlogByTypeId(Integer typeId) throws Exception;

    /**
     * 查询所有博客ID
     * @return 博客ID集合
     * @throws Exception
     */
    List<String> getBlogIds() throws Exception;
}
