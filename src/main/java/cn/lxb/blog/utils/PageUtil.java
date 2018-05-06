package cn.lxb.blog.utils;

import cn.lxb.blog.entity.Blog;

/**
 * <p>
 * description：分页工具类
 * </p>
 *
 * @author 54LXB.
 * @apiNote 知识改变命运，技术改变世界。
 * @since 2017-11-25.
 */
public class PageUtil {

    /**
     * <p>
     * Description：生成分页代码
     * </p>
     *
     * @param targetUrl   目标地址
     * @param totalNum    总记录数
     * @param currentPage 当前页
     * @param pageSize    每页大小
     * @author 54LXB.
     * @apiNote 知识改变命运，技术改变世界。
     * @since 2017-11-25.
     */
    public static String genPagination(String targetUrl, long totalNum, int currentPage, int pageSize, String param) {

        long totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;

        if (totalPage == 0) {
            return "未查询到数据";
        }

        StringBuilder pageCode = new StringBuilder("");
        if (StringUtil.isEmpty(param)) {

            if (currentPage == 1) {
                pageCode.append("<li class='disabled'><a href='#'>首页</a></li>");
            } else {
                pageCode.append("<li><a href='").append(targetUrl).append("?page=1'>首页</a></li>");
            }

            if (currentPage > 1) {
                pageCode.append("<li><a href='").append(targetUrl).append("?page=").append(currentPage - 1).append("'>上一页</a></li>");
            } else {
                pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
            }

            for (int i = currentPage - 2; i <= currentPage + 2; i++) {
                if (i < 1 || i > totalPage) {
                    continue;
                }
                if (i == currentPage) {
                    pageCode.append("<li class='active'><a href='").append(targetUrl).append("?page=").append(i).append("'>").append(i).append("</a></li>");
                } else {
                    pageCode.append("<li><a href='").append(targetUrl).append("?page=").append(i).append("'>").append(i).append("</a></li>");
                }
            }

            if (currentPage < totalPage) {
                pageCode.append("<li><a href='").append(targetUrl).append("?page=").append(currentPage + 1).append("'>下一页</a></li>");
            } else {
                pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
            }
            if (currentPage == totalPage) {
                pageCode.append("<li class='disabled'><a href='#'>尾页</a></li>");
            } else {
                pageCode.append("<li><a href='").append(targetUrl).append("?page=").append(totalPage).append("'>尾页</a></li>");
            }

        } else {

            if (currentPage == 1) {
                pageCode.append("<li class='disabled'><a href='#'>首页</a></li>");
            } else {
                pageCode.append("<li><a href='").append(targetUrl).append("?page=1&").append(param).append("'>首页</a></li>");
            }

            if (currentPage > 1) {
                pageCode.append("<li><a href='").append(targetUrl).append("?page=").append(currentPage - 1).append("&").append(param).append("'>上一页</a></li>");
            } else {
                pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
            }

            for (int i = currentPage - 2; i <= currentPage + 2; i++) {
                if (i < 1 || i > totalPage) {
                    continue;
                }
                if (i == currentPage) {
                    pageCode.append("<li class='active'><a href='").append(targetUrl).append("?page=").append(i).append("&").append(param).append("'>").append(i).append("</a></li>");
                } else {
                    pageCode.append("<li><a href='").append(targetUrl).append("?page=").append(i).append("&").append(param).append("'>").append(i).append("</a></li>");
                }
            }

            if (currentPage < totalPage) {
                pageCode.append("<li><a href='").append(targetUrl).append("?page=").append(currentPage + 1).append("&").append(param).append("'>下一页</a></li>");
            } else {
                pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
            }
            if (currentPage == totalPage) {
                pageCode.append("<li class='disabled'><a href='#'>尾页</a></li>");
            } else {
                pageCode.append("<li><a href='").append(targetUrl).append("?page=").append(totalPage).append("&").append(param).append("'>尾页</a></li>");
            }

        }

        return pageCode.toString();

    }

    /**
     * <p>
     * Description：获取上一篇博客和下一篇博客代码
     * </p>
     *
     * @param lastBlog 上一篇
     * @param nextBlog 下一篇
     * @return 上一页下一页页面代码
     * @author 54LXB.
     * @apiNote 知识改变命运，技术改变世界。
     * @since 2017-11-25.
     */
    public static String genUpAndDownPageCode(Blog lastBlog, Blog nextBlog, String projectContext) {
        StringBuilder pageCode = new StringBuilder("");
        if (lastBlog == null || lastBlog.getId() == null) {
            pageCode.append("<p>上一篇：没有了</p>");
        } else {
            pageCode.append("<p>上一篇：<a href='").append(projectContext).append("/article/").append(lastBlog.getId()).append("/detail'>").append(lastBlog.getTitle()).append("</a></p>");
        }

        if (nextBlog == null || nextBlog.getId() == null) {
            pageCode.append("<p>下一篇：没有了</p>");
        } else {
            pageCode.append("<p>下一篇：<a href='").append(projectContext).append("/article/").append(nextBlog.getId()).append("/detail'>").append(nextBlog.getTitle()).append("</a></p>");
        }
        return pageCode.toString();
    }

    /**
     * <p>
     * Description：获取上一页，下一页代码 查询博客用到
     * </p>
     *
     * @param page           当前页
     * @param totalNum       总记录数
     * @param keyword        查询关键字
     * @param pageSize       每页大小
     * @param projectContext 项目路径
     * @return 上一页下一页页面代码
     * @author 54LXB.
     * @apiNote 知识改变命运，技术改变世界。
     * @since 2017-11-25.
     */
    public static String getUpAndDownPageCode(Integer page, Integer totalNum, String keyword, Integer pageSize, String projectContext) {
        long totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
        if (totalPage == 0) {
            return "";
        }

        StringBuilder pageCode = new StringBuilder("");
        pageCode.append("<nav><ul class='pager'>");
        if (page > 1) {
            pageCode.append("<li><a href='").append(projectContext).append("/article/search?page=").append(page - 1);
            if (StringUtil.isNotEmpty(keyword)) pageCode.append("&keyword=").append(keyword);
            pageCode.append("'>上一页</a></li>");
        } else {
            pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
        }

        if (page == 1) {
            pageCode.append("<li class='disabled'><a href='#'>1</a></li>");
        }

        if (page < totalPage) {
            pageCode.append("<li><a href='").append(projectContext).append("/article/search?page=").append(page + 1);
            if (StringUtil.isNotEmpty(keyword)) pageCode.append("&keyword=").append(keyword);
            pageCode.append("'>上一页</a></li>");
        } else {
            pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
        }
        pageCode.append("</ul></nav>");

        return pageCode.toString();
    }
}
