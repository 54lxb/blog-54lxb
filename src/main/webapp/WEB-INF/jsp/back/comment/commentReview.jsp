<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>博客评论审核管理页面</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/frame/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/frame/jquery-easyui-1.3.3/themes/icon.css">
</head>
<body style="margin: 1px">
<table id="dg" title="评论审核管理" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
       url="${pageContext.request.contextPath}/admin/comment/list?state=0" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="20" align="center">编号</th>
        <th field="blog" width="200" align="center" formatter="comment.ToolBox.formatBlogTitle">博客标题</th>
        <th field="userIp" width="100" align="center">用户IP</th>
        <th field="content" width="200" align="center">评论内容</th>
        <th field="commentDate" width="50" align="center">评论日期</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <div>
        <a href="javascript:comment.ToolBox.commentReview(1)" class="easyui-linkbutton" iconCls="icon-ok" plain="true">审核通过</a>
        <a href="javascript:comment.ToolBox.commentReview(2)" class="easyui-linkbutton" iconCls="icon-no" plain="true">审核不通过</a>
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