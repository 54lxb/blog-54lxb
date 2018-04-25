<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>博客系统后台登录</title>
    <link rel="icon" href="${pageContext.request.contextPath }/static/common/images/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="${pageContext.request.contextPath }/static/common/images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/common/css/login.css">
</head>
<body>
<div class="top_div"></div>
<form action="${pageContext.request.contextPath}/admin/blogger/login" method="post" onsubmit="return checkForm()">
    <div class="form-up">
        <div style="width: 165px; height: 96px; position: absolute;">
            <div class="tou"></div>
            <div class="initial_left_hand" id="left_hand"></div>
            <div class="initial_right_hand" id="right_hand"></div>
        </div>
        <p style="padding: 30px 0px 10px; position: relative;">
            <span class="u_logo"></span>
            <input id="userName" name="userName" class="ipt" type="text" placeholder="请输入用户名"
                   value="${param.userName }">
        </p>
        <P style="position: relative;">
            <span class="p_logo"></span>
            <input id="password" name="password" class="ipt" type="password" placeholder="请输入密码"
                   value="${param.password }">
        </P>
        <div class="form-down">
            <P style="margin: 0px 35px 20px 45px;">
                <span style="float: left;">大爱我小宝哥</span>
                <span><font color="red" id="error">${requestScope.errorInfo }</font></span>
                <span style="float: right;">
                    <input class="login-button" type="submit" value="登录"/>
	            </span>
            </P>
        </div>
    </div>
</form>
<div style="text-align:center;padding-top: 30px">
    Copyright Reserved © 2017-2020 © Designed By <strong>54lxb</strong>
</div>
<script src="${pageContext.request.contextPath}/static/frame/bootstrap-3.3.7/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
    /**
     * 登录界面特效及js验证逻辑
     * Created by Andy on 2017/3/15.
     */
    $(function () {

        // 获取节点
        var passwordNode = $("#password");
        var leftHandNode = $("#left_hand");
        var rightHandNode = $("#right_hand");

        //得到焦点
        passwordNode.focus(function () {
            $("#left_hand").animate({
                left: "150",
                top: " -38"
            }, {
                step: function () {
                    if (parseInt(leftHandNode.css("left")) > 140) {
                        $("#left_hand").attr("class", "left_hand");
                    }
                }
            }, 2000);
            $("#right_hand").animate({
                right: "-64",
                top: "-38px"
            }, {
                step: function () {
                    if (parseInt(rightHandNode.css("right")) > -70) {
                        $("#right_hand").attr("class", "right_hand");
                    }
                }
            }, 2000);

        });

        //失去焦点
        passwordNode.blur(function () {
            leftHandNode.attr("class", "initial_left_hand");
            leftHandNode.attr("style", "left:100px;top:-12px;");
            rightHandNode.attr("class", "initial_right_hand");
            rightHandNode.attr("style", "right:-112px;top:-12px");
        });

    });

    function checkForm() {
        var userName = $("#userName").val();
        var password = $("#password").val();
        if (userName === null || userName === "") {
            $("#error").hide().html("账号不能为空！").show(300);
            return false;
        }
        if (password === null || password === "") {
            $("#error").hide().html("密码不能为空！").show(300);
            return false;
        }
        return true;
    }
    // 强制登出提醒
    function kickOut() {
        var href = location.href;
        if(href.indexOf("kickOut") > 0) {
            alert("您的账号在另一台设备上登录，您被挤下线，若非您本人操作，请立即修改密码！");
            window.top.location.href = "${pageContext.request.contextPath}/admin/blogger/logon";
        }
    }
    window.onload = kickOut();
</script>
</body>
</html>