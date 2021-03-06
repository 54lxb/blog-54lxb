<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="baseURL" value="${pageContext.request.contextPath}"/>

<div class="data_list">
    <div class="data_list_title">
        <img src="${baseURL}/static/common/images/list_icon.png"/>
        最新博客
    </div>
    <div class="datas">
        <ul>
            <c:forEach var="blog" items="${blogList }">
                <li style="margin-bottom: 0.3rem">
                    <span class="date">
                        <a href="${baseURL}/article/${blog.id}/detail">
                            <fmt:formatDate value="${blog.releaseDate }" type="date" pattern="yyyy年MM月dd日"/>
                        </a>
                    </span>
                    <span class="title article-title">
                        <a href="${baseURL}/article/${blog.id}/detail">${blog.title }</a>
                    </span>
                    <span class="summary"><strong>摘要: </strong>${blog.summary }......</span>
                    <span class="img">
				  		<c:forEach var="image" items="${blog.imagesList }">
                            <a href="${baseURL}/article/${blog.id}/detail">${image }</a>&nbsp;&nbsp;
                        </c:forEach>
				  	</span><br>
                    <span class="info">
                        发表于
                        <fmt:formatDate value="${blog.releaseDate }" type="date" pattern="yyyy-MM-dd HH:mm"/>
                        阅读(${blog.clickHit}) 评论(${blog.replyHit})
                    </span>
                </li>
                <hr style="height:5px;border:none;border-top:1px dashed gray;padding-bottom: 10px;"/>
            </c:forEach>
        </ul>
    </div>
</div>

<div class="text-center">
    <nav>
        <ul class="pagination pagination-sm">
            ${requestScope.pageCode }
        </ul>
    </nav>
</div>