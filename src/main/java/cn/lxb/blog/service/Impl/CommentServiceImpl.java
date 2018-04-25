package cn.lxb.blog.service.Impl;

import cn.lxb.blog.dao.CommentDao;
import cn.lxb.blog.entity.Comment;
import cn.lxb.blog.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 评论Service实现类
 * Created by Andy on 2017/3/13.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDao commentDao;

    @Override
    public Integer add(Comment comment) throws Exception {
        return commentDao.add(comment);
    }

    @Override
    public List<Comment> list(Map<String, Object> map) throws Exception {
        return commentDao.list(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) throws Exception {
        return commentDao.getTotal(map);
    }

    @Override
    public Integer delete(Integer id) throws Exception {
        return commentDao.delete(id);
    }

    @Override
    public Integer updateById(Integer id, Comment comment) throws Exception {
        comment.setId(id);
        return commentDao.update(comment);
    }

}
