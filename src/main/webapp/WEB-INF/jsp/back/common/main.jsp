<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>博客系统后台管理页面</title>
    <link rel="icon" href="${pageContext.request.contextPath }/static/common/images/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="${pageContext.request.contextPath }/static/common/images/favicon.ico"
          type="image/x-icon"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/frame/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/frame/jquery-easyui-1.3.3/themes/icon.css">
</head>
<body class="easyui-layout">
<div region="north" style="height: 56px;background-color: #E0ECFF">
    <table style="padding: 1px" width="100%">
        <tr>
            <td width="35%">
                <img alt="logo" src="${pageContext.request.contextPath}/static/common/images/logo.png" height="40"/>
            </td>
            <td width="30%" align="center">
                <font size="3">
                    <span id="today"></span>
                </font>
            </td>
            <td width="35%" align="right" valign="center">
                <font size="3" style="margin-right: 1%">
                    &nbsp;&nbsp;<strong>欢迎回来，</strong>${sessionScope.currentUser.nickName }
                </font>
            </td>
        </tr>
    </table>
</div>
<div region="center">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="首页" data-options="iconCls:'icon-home'">
            <div align="center" style="padding-top: 20%">
                <font color="red" size="10">欢迎使用，亲爱的${sessionScope.currentUser.nickName }！</font>
            </div>
        </div>
    </div>
</div>
<div region="west" style="width: 200px" title="导航菜单" split="true">
    <div class="easyui-accordion" data-options="fit:true,border:false">

        <div title="博客文章管理" data-options="iconCls:'icon-bkgl'" style="padding:10px;">
            <a href="javascript:main.ToolBox.openTab('撰写个人博客', 'blog', 'writeBlog', 'icon-writeblog')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-writeblog'" style="width: 150px;">撰写个人博客</a>
            <a href="javascript:main.ToolBox.openTab('博客文章管理', 'blog', 'blogManage', 'icon-bkgl')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-bkgl'" style="width: 150px;">博客文章管理</a>
        </div>

        <div title="博客类别管理" data-options="iconCls:'icon-bklb'" style="padding:10px">
            <a href="javascript:main.ToolBox.openTab('博客类别管理', 'blogType', 'blogTypeManage', 'icon-bklb')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-bklb'" style="width: 150px;">博客类别管理</a>
        </div>

        <div title="博客评论管理" data-options="iconCls:'icon-plgl'" style="padding:10px">
            <a href="javascript:main.ToolBox.openTab('评论审核', 'comment', 'commentReview', 'icon-review')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-review'" style="width: 150px">评论审核</a>
            <a href="javascript:main.ToolBox.openTab('评论信息管理', 'comment', 'commentManage', 'icon-plgl')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-plgl'" style="width: 150px;">评论信息管理</a>
        </div>

        <div title="个人信息管理" data-options="iconCls:'icon-grxx'" style="padding:10px">
            <a href="javascript:main.ToolBox.openTab('查看个人信息', 'blogger', 'view', 'icon-grxx')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-grxx'" style="width: 150px;">查看个人信息</a>
            <a href="javascript:main.ToolBox.openTab('修改个人信息', 'blogger', 'edit', 'icon-grxxxg')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-grxxxg'" style="width: 150px;">修改个人信息</a>
        </div>

        <div title="博客系统管理" data-options="iconCls:'icon-system'" style="padding:10px">
            <a href="javascript:main.ToolBox.openTab('友情链接管理', 'link', 'linkManage', 'icon-link')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-link'" style="width: 150px">友情链接管理</a>
            <a href="javascript:main.ToolBox.openPasswordModifyDialog(${sessionScope.currentUser.id})" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-modifyPassword'" style="width: 150px;">修改博主密码</a>
            <a href="javascript:main.ToolBox.refreshSystem()" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-refresh'" style="width: 150px;">刷新系统缓存</a>
            <a href="javascript:main.ToolBox.logout()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'"
               style="width: 150px;">安全退出系统</a>
        </div>

    </div>
</div>

<div region="south" style="height: 25px;padding: 5px" align="center">
    Copyright © 2018-2020 © Powered By <strong>54lxb</strong>
</div>

<div id="dlg" class="easyui-dialog" style="width:400px;height:200px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">

    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <td>用户名：</td>
                <td><input type="text" id="userName" name="userName" readonly="readonly"
                           value="${sessionScope.currentUser.userName }" style="width: 200px"/></td>
            </tr>
            <tr>
                <td>新密码：</td>
                <td><input type="password" id="newPassword" name="newPassword" class="easyui-validatebox"
                           required="true" style="width: 200px"/></td>
            </tr>
            <tr>
                <td>确认新密码：</td>
                <td><input type="password" id="newPassword2" name="newPassword2" class="easyui-validatebox"
                           required="true" style="width: 200px"/></td>
            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:main.ToolBox.modifyPassword()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:main.ToolBox.closePasswordModifyDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/frame/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/frame/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/frame/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/back/js/main.js"></script>
<script type="text/javascript">
    function setDateTime() {
        var date = new Date();
        var day = date.getDay();
        var week;
        switch (day) {
            case 0:
                week = "星期日";
                break;
            case 1:
                week = "星期一";
                break;
            case 2:
                week = "星期二";
                break;
            case 3:
                week = "星期三";
                break;
            case 4:
                week = "星期四";
                break;
            case 5:
                week = "星期五";
                break;
            case 6:
                week = "星期六";
                break;
        }
        var today = date.getFullYear() + "年" + (date.getMonth() + 1) + "月"
            + date.getDate() + "日  " + week + " " + date.getHours() + ":"
            + date.getMinutes() + ":" + (date.getSeconds() > 10 ? date.getSeconds() : '0'+date.getSeconds());
        document.getElementById("today").innerHTML = today;
    }
    window.setInterval("setDateTime()", 1000);

</script>
</body>
</html>