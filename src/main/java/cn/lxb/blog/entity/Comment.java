package cn.lxb.blog.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 评论对应实体表
 * Created by Andy on 2017/3/12.
 */
public class Comment implements Serializable {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 评论用户IP
     */
    private String userIp;
    /**
     * 评论用户名
     */
    private String nickname;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 被评论的博客
     */
    private Blog blog;
    /**
     * 评论日期
     */
    private Date commentDate;
    /**
     * 审核状态：0 待审核 1 审核通过 2 审核未通过
     */
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userIp='" + userIp + '\'' +
                ", nickname='" + nickname + '\'' +
                ", content='" + content + '\'' +
                ", blog=" + blog +
                ", commentDate=" + commentDate +
                ", state=" + state +
                '}';
    }

}
