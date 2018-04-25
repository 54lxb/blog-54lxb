package cn.lxb.blog.entity;

import java.io.Serializable;

/**
 * 分页Model类
 * Created by Andy on 2017/3/12.
 */
public class PageBean implements Serializable {

    /**
     * 第几页
     */
    private int page;
    /**
     * 每页记录数
     */
    private int pageSize;
    /**
     * 起始页
     */
    private int start;

    public PageBean(int page, int pageSize) {
        super();
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStart() {
        return (page - 1) * pageSize;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", start=" + start +
                '}';
    }

}
