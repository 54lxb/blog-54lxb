package cn.lxb.blog.service;

import cn.lxb.blog.entity.Comment;

import java.util.List;
import java.util.Map;

/**
 * 评论Service接口
 * Created by Andy on 2017/3/13.
 */
public interface CommentService {
    /**
     * 添加评论
     * @param comment
     * @return
     */
    public Integer add(Comment comment) throws Exception;

    /**
     * 修改评论
     * @param comment
     * @return
     */
    public Integer updateById(Integer id, Comment comment) throws Exception;

    /**
     * 查找用户评论信息
     * @param map
     * @return
     */
    public List<Comment> list(Map<String,Object> map) throws Exception;


    /**
     * 获取总记录数
     * @param map
     * @return
     */
    public Long getTotal(Map<String,Object> map) throws Exception;

    /**
     * 删除评论信息
     * @param id
     * @return
     */
    public Integer delete(Integer id) throws Exception;

}
