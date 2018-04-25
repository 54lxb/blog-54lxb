<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="baseURL" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>博客个人信息修改页面</title>
    <link rel="stylesheet" type="text/css" href="${baseURL}/static/frame/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${baseURL}/static/frame/jquery-easyui-1.3.3/themes/icon.css">
</head>
<body style="margin: 10px">
<div id="p" class="easyui-panel" title="预览个人信息" style="padding: 10px">
    <table cellspacing="20px">
        <tr>
            <td>个人头像：</td>
            <td><img src="${baseURL}/static/front/blogger/image/${requestScope.blogger.imageName}"></td>
        </tr>
        <tr>
            <td colspan="2">
                <hr style="border: solid 1px #99cdff;">
            </td>
        </tr>
        <tr>
            <td width="80px">用户名：</td>
            <td>${requestScope.blogger.userName}</td>
        </tr>
        <tr>
            <td colspan="2">
                <hr style="border: solid 1px #99cdff;">
            </td>
        </tr>
        <tr>
            <td>昵称：</td>
            <td>${requestScope.blogger.nickName}</td>
        </tr>
        <tr>
            <td colspan="2">
                <hr style="border: solid 1px #99cdff;">
            </td>
        </tr>
        <tr>
            <td>个性签名：</td>
            <td>${requestScope.blogger.sign}</td>
        </tr>
        <tr>
            <td colspan="2">
                <hr style="border: solid 1px #99cdff;">
            </td>
        </tr>
        <tr>
            <td valign="top">个人简介：</td>
            <td>${requestScope.blogger.proFile}</td>
        </tr>
        <tr>
            <td colspan="2">
                <hr style="border: solid 1px #99cdff;">
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript" src="${baseURL}/static/frame/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${baseURL}/static/frame/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${baseURL}/static/frame/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
</body>
</html>