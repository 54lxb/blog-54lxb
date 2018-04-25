package cn.lxb.blog.entity;

import java.io.Serializable;

/**
 * 博客类型对应实体类
 * Created by Andy on 2017/3/12.
 */
public class BlogType implements Serializable {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 博客类型名称
     */
    private String typeName;
    /**
     * 数量
     */
    private Integer blogCount;
    /**
     * 排序: 从小到大排序显示
     */
    private Integer orderNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "BlogType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", blogCount=" + blogCount +
                ", orderNo=" + orderNo +
                '}';
    }

}
