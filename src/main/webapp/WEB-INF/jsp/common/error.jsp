<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh">
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	  <link rel="icon" href="${pageContext.request.contextPath }/static/common/images/favicon.ico" type="image/x-icon">
	  <link rel="shortcut icon" href="${pageContext.request.contextPath }/static/common/images/favicon.ico" type="image/x-icon"/>
    <title>大爱我小宝哥的博客---错误提示</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/common/css/error-page.css">
  </head>
  <body>
    <div align="center">
		<h1>${requestScope.message }</h1>
      <h2>页面找不到了,休息一下呗，玩玩小游戏呗！</h2>
	  <canvas height="450" width="750"></canvas>
	  <h5>您所访问的页面不存在，可能已被删除或您输错了网址！</h5>
	  <font size="5">
	    &lt;&lt;<a href="${pageContext.request.contextPath }/index">返回首页</a>&gt;&gt;
	    &nbsp;&nbsp;&lt;&lt;<a href="#" onclick="history.go(-1)">返回上一页</a>&gt;&gt;</font>
	  <h4>====游戏玩法====</h4>
	  <p>  ← 左转 ↑ 前进   暂停 ↓ 右转 →  </p>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/common/js/error-game.js"></script>
  </body>
</html>
