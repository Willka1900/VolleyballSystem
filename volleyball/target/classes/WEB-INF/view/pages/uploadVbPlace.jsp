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
					<small>————排球人的聚集地</small>
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
					<li><a href="/">首页</a></li>
					<li class="active"><a href="/vbPlace">球场</a></li>
					<li><a href="/shopping">购物</a></li>
					<li><a href="/about">关于</a></li>
				</ul>
			</div>
		</div>
		<br>
		<form id="pcaGo" action="/uploadVbPlace" method="post" enctype="multipart/form-data">
			<div class="text-center">
				<div class="custom-file">
					<label style="" class="custom-file-label" for="customFile">球场图片:</label>
					<input style="display:block;margin:auto" type="file" class="custom-file-input" name="file" id="customFile">
				</div>
				<br>
				<!-- 省市区三级联动开始 -->
				<label>区域：</label>
				<div class="btn-group">
					<select id="province" name="province" path="province">
						<option>请选择省份</option>
					</select>
				</div>
				<div class="btn-group">
					<select id="city" name="city" path="city">
						<option>请选择城市</option>
					</select>
				</div>
				<div class="btn-group">
					<select id="area" name="area" path="area">
						<option>请选择地区</option>
					</select>
				</div>
				<br>
				<br>
				<!-- 省市区三级联动结束 -->
				<label>位置：</label><input type="text" name="location" /><br>
				<br> <label>联系方式：</label><input type="text" name="contact"
					placeholder="非收费场馆可不填" /><br>
				<br>
				<button type="submit" class="btn btn-primary" id="upload">上传</button>
			</div>
		</form>
	</div>
</body>
</html>
<script type="text/javascript">
	$(function() {
		$
				.ajax({
					url : "/static/json/city.json",
					type : "get",
					dataType : "json",
					success : function(data) {
						for ( var key in data) {
							$("#province").append(
									"<option value="+key+">" + key
											+ "</option>");
						}
						$("#province")
								.change(
										function() {
											var now_province = $(
													"#province option:selected")
													.val();
											$("#city").empty();
											$("#city").append(
													"<option >请选择城市</option>");
											for ( var key in data[now_province]) {
												$("#city").append(
														"<option value="+key+">"
																+ key
																+ "</option>");
											}
											$("#city")
													.change(
															function() {
																var now_city = $(
																		"#city option:selected")
																		.val();
																$("#area")
																		.empty();
																$("#area")
																		.append(
																				"<option >请选择地区</option>");
																for ( var k in data[now_province][now_city]) {
																	$("#area")
																			.append(
																					"<option value="+data[now_province][now_city][k]+">"
																							+ data[now_province][now_city][k]
																							+ "</option>");
																}
															})
										})
					}
				})
	});
</script>