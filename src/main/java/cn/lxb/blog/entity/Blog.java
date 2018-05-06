package cn.lxb.blog.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * Description：Blog表对应实体类
 * </P>
 *
 * @author Andy
 * @apiNote 知识改变命运，技术改变世界！
 * @since 2017-09-13 09:00.
 */
public class Blog implements Serializable {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 博客标题
     */
    private String title;
    /**
     * 摘要
     */
    private String summary;
    /**
     * 发布日期
     */
    private Date releaseDate;
    /**
     * 查看次数
     */
    private Integer clickHit;
    /**
     * 回复次数
     */
    private Integer replyHit;
    /**
     * 博客内容
     */
    private String content;
    /**
     * 博客内容 无网页标签 Lucene分词用
     */
    private String contentNoTag;
    /**
     * 博客类型
     */
    private BlogType blogType;
    /**
     * 博客数量 非博客实际属性，主要是 根据发布日期归档查询博客数量用
     */
    private Integer blogCount;
    /**
     * 发布日期字符串 只取年和月
     */
    private String releaseDateStr;
    /**
     * 关键字 空格隔开
     */
    private String keyWord;
    /**
     * 博客里存在的图片 主要用于列表展示显示缩略图
     */
    private List<String> imagesList = new LinkedList<String>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getClickHit() {
        return clickHit;
    }

    public void setClickHit(Integer clickHit) {
        this.clickHit = clickHit;
    }

    public Integer getReplyHit() {
        return replyHit;
    }

    public void setReplyHit(Integer replyHit) {
        this.replyHit = replyHit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentNoTag() {
        return contentNoTag;
    }

    public void setContentNoTag(String contentNoTag) {
        this.contentNoTag = contentNoTag;
    }

    public BlogType getBlogType() {
        return blogType;
    }

    public void setBlogType(BlogType blogType) {
        this.blogType = blogType;
    }

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

    public String getReleaseDateStr() {
        return releaseDateStr;
    }

    public void setReleaseDateStr(String releaseDateStr) {
        this.releaseDateStr = releaseDateStr;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public List<String> getImagesList() {
        return imagesList;
    }

    public void setImagesList(List<String> imagesList) {
        this.imagesList = imagesList;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", releaseDate=" + releaseDate +
                ", clickHit=" + clickHit +
                ", replyHit=" + replyHit +
                ", content='" + content + '\'' +
                ", contentNoTag='" + contentNoTag + '\'' +
                ", blogType=" + blogType +
                ", blogCount=" + blogCount +
                ", releaseDateStr='" + releaseDateStr + '\'' +
                ", keyWord='" + keyWord + '\'' +
                ", imagesList=" + imagesList +
                '}';
    }
}
