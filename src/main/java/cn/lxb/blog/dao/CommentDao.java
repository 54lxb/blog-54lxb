package cn.lxb.blog.dao;

import cn.lxb.blog.entity.Comment;

import java.util.List;
import java.util.Map;

/**
 * 评论Dao接口
 * Created by Andy on 2017/3/12.
 */
public interface CommentDao {

    /**
     * 添加评论
     *
     * @param comment 要添加的评论信息bean
     * @return 影响的行数
     */
    public Integer add(Comment comment) throws Exception;

    /**
     * 修改评论
     *
     * @param comment 要修改的评论信息bean
     * @return 影响的行数
     */
    public Integer update(Comment comment) throws Exception;

    /**
     * 查找用户评论信息
     *
     * @param map
     * @return 找的的用户评论信息集合或者empty
     */
    public List<Comment> list(Map<String, Object> map) throws Exception;

    /**
     * 获取总记录数
     *
     * @param map
     * @return 获取到的总记录数
     */
    public Long getTotal(Map<String, Object> map) throws Exception;

    /**
     * 删除评论信息
     *
     * @param id 博客id
     * @return 影响的行数
     */
    public Integer delete(Integer id) throws Exception;

}
