<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>排球之家</title>
<link rel="shortcut icon" type="image/x-icon" href="/img/vb.ico"
	media="screen" />
<!-- 引入 Bootstrap -->
<link
	href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap-theme.css"
	rel="stylesheet">
<link
	href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap-theme.min.css"
	rel="stylesheet">
<link
	href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.css"
	rel="stylesheet">
<link
	href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css"
	rel="stylesheet">
<script src="/js/jquery.slim.min.js"></script>
<script src="/js/jquery-1.7.min.js"></script>
<script
	src="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/bootstrap.js"></script>
<script
	src="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/bootstrap.min.js"></script>
<script src="/js/json-minified.js"></script>
</head>
<body background="/img/bg.png">
	<br>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-2 column">
				<img width="140" height="140" src="/static/images/amazingVB.jpg"
					class="img-circle" />
			</div>
			<div class="col-md-8 column">
				<br> <br>
				<h1 class="text-danger text-center">
					<strong>排球之家</strong>
				</h1>
				<br>
				<h1 class="text-right">
					<small>————排球人聚集地</small>
				</h1>
			</div>
			<div class="col-md-2 column">
				<c:choose>
					<c:when test="${ sessionScope.user != null }">
						<h4>【${sessionScope.user.name}】</h4>
						<a href="/user/logout"><button type="button"
								class="btn btn-default">用户登出</button></a>
					</c:when>
					<c:otherwise>
						<a href="/login"><button type="button" class="btn btn-default">登陆</button></a>
						<a href="/reg"><button type="button" class="btn btn-default">注册</button></a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<br> <br> <br>
		<div class="row clearfix">
			<div class="col-md-12 column">
				<ul class="nav nav-tabs">
					<li class="active"><a href="/">首页</a></li>
					<li><a href="/vbPlace">球场</a></li>
					<li><a href="/shopping">购物</a></li>
					<li><a href="/about">关于</a></li>
				</ul>
				<div class="">
					<img height="510" src="/static/images/top.jpg" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>