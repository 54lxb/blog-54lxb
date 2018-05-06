<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="baseURL" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta name="baidu-site-verification" content="KLDgkk5tFN" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${pageTitle }</title>
    <link rel="icon" href="${baseURL }/static/common/images/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="${baseURL}/static/common/images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="${baseURL}/static/frame/bootstrap-3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${baseURL}/static/frame/bootstrap-3.3.7/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="${baseURL}/static/frame/layer-2.4/skin/layer.css">
    <link rel="stylesheet" href="${baseURL}/static/common/css/blog.css">
    <link rel="stylesheet" href="${baseURL}/static/common/css/load.css">
    <link rel="stylesheet" href="${baseURL}/static/common/css/loading.css">
    <style type="text/css">
        body {
            padding-top: 0.1em;
            padding-bottom: 0.4em;
        }

        .article-title a {
            font-size: 1.5em;
        }

        .article-title a:hover, .article-title a:focus {
            text-decoration: underline;
        }

        .article-title a:active, .article-title a:hover {
            outline: 0;
        }

    </style>
</head>
<body>
<div class="container">
    <jsp:include page="head.jsp"/>

    <jsp:include page="menu.jsp"/>

    <div id="loader-wrapper">
        <div id="loader"></div>
        <div class="loader-section section-left"></div>
        <div class="loader-section section-right"></div>
        <div class="load_title">
            正在加载54lxb的站点....<br>
            <span>V1.0</span>
        </div>
    </div>

    <div class="row">
        <div class="col-md-9">
            <jsp:include page="${mainPage }"/>
        </div>

        <div class="col-md-3">
            <c:if test="${applicationScope.blogger != null}">
                <div class="data_list">
                    <div class="data_list_title">
                        <img src="${baseURL}/static/common/images/user_icon.png"/>
                        博主信息
                    </div>
                    <div class="user_image">
                        <img src="${baseURL}/static/front/blogger/image/${blogger.imageName}"/>
                    </div>
                    <div class="nickName">${blogger.nickName }</div>
                    <div class="userSign">(${blogger.sign })</div>
                </div>
            </c:if>


            <div class="data_list">
                <div class="data_list_title">
                    <img src="${baseURL}/static/common/images/byType_icon.png"/>
                    按日志类别
                </div>
                <div class="datas">
                    <ul>
                        <c:forEach var="blogTypeCount" items="${applicationScope.blogTypeCountList }">
                            <li>
                                <span>
                                    <a href="${baseURL}/index?typeId=${blogTypeCount.id }">
                                        ${blogTypeCount.typeName }(${blogTypeCount.blogCount })
                                    </a>
                                </span>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>

            <div class="data_list">
                <div class="data_list_title">
                    <img src="${baseURL}/static/common/images/byDate_icon.png"/>
                    按日志日期
                </div>
                <div class="datas">
                    <ul>
                        <c:forEach var="blogCount" items="${applicationScope.blogCountList }">
                            <li>
                                <span>
                                    <a href="${baseURL}/index?releaseDateStr=${blogCount.releaseDateStr }">
                                        ${blogCount.releaseDateStr }(${blogCount.blogCount })
                                    </a>
                                </span>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>

            <div class="data_list">
                <div class="data_list_title">
                    <img src="${baseURL}/static/common/images/link_icon.png"/>
                    友情链接
                </div>
                <div class="datas">
                    <ul>
                        <c:forEach var="link" items="${applicationScope.linkList }">
                            <li><span><a href="${link.linkUrl }" target="_blank">${link.linkName }</a></span></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="foot.jsp"%>
</div>
<script src="${baseURL}/static/frame/bootstrap-3.3.7/js/jquery-1.11.2.min.js"></script>
<script src="${baseURL}/static/frame/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="${baseURL}/static/frame/layer-2.4/layer.js"></script>
<script type="text/javascript">
    $(function() {
        $('body').addClass('loaded');
    });
</script>
</body>
</html>