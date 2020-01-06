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
<style>
#login {
	float: left;
	height: 250px;
	width: 330px;
	margin-left: 6%;
	margin-top: 9%;
	display: inline;
	z-index: 999;
}
</style>
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
					<small>————排球人的聚集地</small>
				</h1>
			</div>
			<div class="col-md-2 column">
				<a href="/login"><button type="button" class="btn btn-default">登陆</button></a>
				<a href="/reg"><button type="button" class="btn btn-default">注册</button></a>
			</div>
		</div>
		<br> <br> <br>

		<div class="row clearfix">
			<div class="col-md-12 column">
				<ul class="nav nav-tabs">
					<li><a href="/">首页</a></li>
					<li><a href="/vbPlace">球场</a></li>
					<li><a href="/shopping">购物</a></li>
					<li><a href="/about">关于</a></li>
				</ul>
			</div>
		</div>
		<br>
		<div class="row clearfix">
			<div class="col-md-4 column"></div>
			<div class="col-md-4 column">
				<div class="form-inline">
					<div class="input-group">
						<span class="input-group-addon">类型</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label for="type" class="choose-box"><input type="radio"
							id="type" name="type" value="user" />用户</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label for="type" class="choose-box"><input type="radio"
							id="type" name="type" value="admin" />管理员</label>
					</div>
					<br /> <br />
					<div class="input-group">
						<span class="input-group-addon">邮箱</span> <input type="text"
							class="form-control" name="email" id="email">
					</div>
					<br /> <br />
					<div class="input-group">
						<span class="input-group-addon">密码</span> <input type="password"
							class="form-control" name="passwd" id="passwd">
					</div>
					<br />
					<div class="text-center">
						<p style="text-align: center; color: red" id="info"></p>
						<br />
						<button id="loginButton" class="btn btn-primary">登陆</button>
					</div>
				</div>
			</div>
			<div class="col-md-4 column"></div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	document.getElementsByName("type")[0].checked = "checked";
	$("#email").keyup(function() {
		var re = /^\w+@[a-z0-9]+\.[a-z]+$/i;/*邮箱校验,邮箱不区分大小写*/
		if (re.test($("#email").val())) {
			$("#info").text("");
		} else {
			$("#info").text("提示:邮箱格式不正确");
		}
	})
	$("#loginButton").click(function() {
		if ($("#email").val() == '' && $("#passwd").val() == '') {
			$("#info").text("提示:邮箱和密码不能为空");
		} else if ($("#email").val() == '') {
			$("#info").text("提示:邮箱不能为空");
		} else if ($("#passwd").val() == '') {
			$("#info").text("提示:密码不能为空");
		} else {
			$.ajax({
				type : "POST",
				url : "/api/loginCheck",
				data : {
					email : $("#email").val(),
					password : $("#passwd").val(),
					type : $('input:radio:checked').val()
				},
				dataType : "json",
				success : function(data) {
					if (data.stateCode.trim() == "0") {
						$("#info").text("提示:该用户不存在");
					} else if (data.stateCode.trim() == "1") {
						$("#info").text("提示:密码错误");
					} else if (data.stateCode.trim() == "2") {
						$("#info").text("提示:登陆成功，跳转中...");
						if ($('input:radio:checked').val() == "admin") {
							window.location.href = "/admin";
						} else {
							window.location.href = "/";
						}
					} else if (data.stateCode.trim() == "3") {
						$("#info").text("提示:该用户已被冻结");
					}
				}
			});
		}
	})
</script>