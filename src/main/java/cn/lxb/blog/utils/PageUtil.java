package cn.lxb.blog.utils;

import cn.lxb.blog.entity.Blog;

/**
 * TODO 分页工具类
 * Created by Andy on 2017/3/13.
 */
public class PageUtil {

    /**
     * TODO 生成分页代码
     *
     * @param targetUrl   目标地址
     * @param totalNum    总记录数
     * @param currentPage 当前页
     * @param pageSize    每页大小
     */
    public static String genPagination(String targetUrl, long totalNum, int currentPage, int pageSize, String param) {

        long totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;

        if (totalPage == 0) {
            return "未查询到数据";
        } else {

            String pageCode = "";

            if (param == null || "".equals(param)) {

                if (currentPage == 1) {
                    pageCode += "<li class='disabled'><a href='#'>首页</a></li>";
                } else {
                    pageCode += "<li><a href='" + targetUrl + "?page=1'>首页</a></li>";
                }

                if (currentPage > 1) {
                    pageCode += "<li><a href='" + targetUrl + "?page=" + (currentPage - 1) + "'>上一页</a></li>";
                } else {
                    pageCode += "<li class='disabled'><a href='#'>上一页</a></li>";
                }

                for (int i = currentPage - 2; i <= currentPage + 2; i++) {
                    if (i < 1 || i > totalPage) {
                        continue;
                    }
                    if (i == currentPage) {
                        pageCode += "<li class='active'><a href='" + targetUrl + "?page=" + i + "'>" + i + "</a></li>";
                    } else {
                        pageCode += "<li><a href='" + targetUrl + "?page=" + i + "'>" + i + "</a></li>";
                    }
                }

                if (currentPage < totalPage) {
                    pageCode += "<li><a href='" + targetUrl + "?page=" + (currentPage + 1) + "'>下一页</a></li>";
                } else {
                    pageCode += "<li class='disabled'><a href='#'>下一页</a></li>";
                }
                if (currentPage == totalPage) {
                    pageCode += "<li class='disabled'><a href='#'>尾页</a></li>";
                } else {
                    pageCode += "<li><a href='" + targetUrl + "?page=" + totalPage + "'>尾页</a></li>";
                }

            } else if (!"".equals(param)) {

                if (currentPage == 1) {
                    pageCode += "<li class='disabled'><a href='#'>首页</a></li>";
                } else {
                    pageCode += "<li><a href='" + targetUrl + "?page=1&" + param + "'>首页</a></li>";
                }

                if (currentPage > 1) {
                    pageCode += "<li><a href='" + targetUrl + "?page=" + (currentPage - 1) + "&" + param + "'>上一页</a></li>";
                } else {
                    pageCode += "<li class='disabled'><a href='#'>上一页</a></li>";
                }

                for (int i = currentPage - 2; i <= currentPage + 2; i++) {
                    if (i < 1 || i > totalPage) {
                        continue;
                    }
                    if (i == currentPage) {
                        pageCode += "<li class='active'><a href='" + targetUrl + "?page=" + i + "&" + param + "'>" + i + "</a></li>";
                    } else {
                        pageCode += "<li><a href='" + targetUrl + "?page=" + i + "&" + param + "'>" + i + "</a></li>";
                    }
                }

                if (currentPage < totalPage) {
                    pageCode += "<li><a href='" + targetUrl + "?page=" + (currentPage + 1) + "&" + param + "'>下一页</a></li>";
                } else {
                    pageCode += "<li class='disabled'><a href='#'>下一页</a></li>";
                }
                if (currentPage == totalPage) {
                    pageCode += "<li class='disabled'><a href='#'>尾页</a></li>";
                } else {
                    pageCode += "<li><a href='" + targetUrl + "?page=" + totalPage + "&" + param + "'>尾页</a></li>";
                }

            }

            return pageCode;
        }
    }

    /**
     * TODO 获取上一篇博客和下一篇博客代码
     *
     * @param lastBlog 上一篇
     * @param nextBlog 下一篇
     * @return 上一页下一页页面代码
     */
    public static String genUpAndDownPageCode(Blog lastBlog, Blog nextBlog, String projectContext) {
        String pageCode = "";
        if (lastBlog == null || lastBlog.getId() == null) {
            pageCode += "<p>上一篇：没有了</p>";
        } else {
            pageCode += "<p>上一篇：<a href='" + projectContext + "/article/" + lastBlog.getId() + "/detail'>" + lastBlog.getTitle() + "</a></p>";
        }

        if (nextBlog == null || nextBlog.getId() == null) {
            pageCode += "<p>下一篇：没有了</p>";
        } else {
            pageCode += "<p>下一篇：<a href='" + projectContext + "/article/" + nextBlog.getId() + "/detail'>" + nextBlog.getTitle() + "</a></p>";
        }
        return pageCode;
    }

    /**
     * TODO 获取上一页，下一页代码 查询博客用到
     *
     * @param page           当前页
     * @param totalNum       总记录数
     * @param keyword        查询关键字
     * @param pageSize       每页大小
     * @param projectContext 项目路径
     * @return 上一页下一页页面代码
     */
    public static String getUpAndDownPageCode(Integer page, Integer totalNum, String keyword, Integer pageSize, String projectContext) {
        long totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
        String pageCode = "";
        if (totalPage == 0) {
            return "";
        } else {
            pageCode = "<nav><ul class='pager'>";
            if (page > 1) {
                if (keyword == null || "".equals(keyword)) {
                    pageCode += "<li><a href='" + projectContext + "/article/search?page=" + (page - 1) + "'>上一页</a></li>";
                } else {
                    pageCode += "<li><a href='" + projectContext + "/article/search?page=" + (page - 1) + "&keyword=" + keyword + "'>上一页</a></li>";
                }
            } else {
                pageCode += "<li class='disabled'><a href='#'>上一页</a></li>";
            }

            if (page == 1) {
                pageCode += "<li class='disabled'><a href='#'>1</a></li>";
            }

            if (page < totalPage) {
                if (keyword == null || "".equals(keyword)) {
                    pageCode += "<li><a href='" + projectContext + "/article/search?page=" + (page + 1) + "'>上一页</a></li>";
                } else {
                    pageCode += "<li><a href='" + projectContext + "/article/search?page=" + (page + 1) + "&keyword=" + keyword + "'>下一页</a></li>";
                }
            } else {
                pageCode += "<li class='disabled'><a href='#'>下一页</a></li>";
            }
            pageCode += "</ul></nav>";
        }
        return pageCode;
    }
}
