<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>博客评论管理页面</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/frame/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/frame/jquery-easyui-1.3.3/themes/icon.css">
</head>
<body style="margin: 1px">
<table id="dg" title="评论管理" class="easyui-datagrid"
       fitColumns="true" pagination="true" rownumbers="true"
       url="${pageContext.request.contextPath}/admin/comment/list" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="20" align="center">编号</th>
        <th field="blog" width="200" align="center" formatter="comment.ToolBox.formatBlogTitle">博客标题</th>
        <th field="userIp" width="100" align="center">用户IP</th>
        <th field="content" width="200" align="center">评论内容</th>
        <th field="commentDate" width="50" align="center">评论日期</th>
        <th field="state" width="50" align="center" formatter="comment.ToolBox.formatState">评论状态</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <div>
        <a href="javascript:comment.ToolBox.deleteComment()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
</div>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/frame/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/frame/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/frame/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/back/js/commentManage.js"></script>
</body>
</html>