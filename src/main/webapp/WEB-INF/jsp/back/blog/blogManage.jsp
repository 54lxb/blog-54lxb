<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="baseURL" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>博客后台管理页面</title>
    <link rel="stylesheet" type="text/css" href="${baseURL}/static/frame/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${baseURL}/static/frame/jquery-easyui-1.3.3/themes/icon.css">
</head>
<body style="margin: 1px">
<table id="dg" title="博客管理" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
       url="${baseURL}/admin/article/list" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="20" align="center">编号</th>
        <th field="title" width="200" align="center" formatter="blog.ToolBox.formatTitle">标题</th>
        <th field="releaseDate" width="50" align="center">发布日期</th>
        <th field="blogType" width="50" align="center" formatter="blog.ToolBox.formatBlogType">博客类别</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <div>
        <a href="javascript:blog.ToolBox.openBlogModifyTab()" class="easyui-linkbutton" iconCls="icon-edit"
           plain="true">修改</a>
        <a href="javascript:blog.ToolBox.deleteBlog()" class="easyui-linkbutton" iconCls="icon-remove"
           plain="true">删除</a>
    </div>
    <div>
        &nbsp;标题：&nbsp;<input type="text" id="s_title" size="20"
                              onkeydown="if(event.keyCode===13) blog.ToolBox.searchBlog()"/>
        <a href="javascript:blog.ToolBox.searchBlog()" class="easyui-linkbutton" iconCls="icon-search"
           plain="true">搜索</a>
    </div>
</div>
</body>
<script type="text/javascript" src="${baseURL}/static/frame/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${baseURL}/static/frame/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${baseURL}/static/frame/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${baseURL}/static/back/js/blogManage.js"></script>
</html>