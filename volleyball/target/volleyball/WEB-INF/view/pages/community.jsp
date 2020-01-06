<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="stylesheet" href="/css/bootstrap4.0.min.css">
<style>
.jumbotron {
	background: url("/img/vb-top.png") no-repeat center center;
	background-size: cover;
	padding-top: 20%;
	padding-bottom: 20%;
	color: Red;
	text-shadow: black 0.3em 0.3em 0.3em;
}

#footer {
	position: relative;
	margin-bottom: 0px;
	height: 100px;
	border-top: 1px solid #eee;
	color: hsla(0, 0%, 100%, .69);
	font-size: 12px;
	text-align: center;
	border-top: 2px dashed #98b7ff;
	transition: height .1s 1s;
}

#copyright {
	position: absolute;
	top: 0;
	left: 0;
	background: #0e0e0e;
	width: 100%;
	height: 100%;
	transform-origin: 100% 0;
	transition: all .5s;
	z-index: 2;
}

#copyright p {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	line-height: 2;
}
</style>
</head>
<body background="/img/bg.png">

	<nav class="navbar navbar-expand-sm bg-info navbar-dark fixed-top">
		<ul class="navbar-nav">
			<li class="nav-item active"><a class="nav-link" href="#">首页</a>
			</li>
			<li class="nav-item active"><a class="nav-link" href="#">活动</a>
			</li>
			<li class="nav-item active"><a class="nav-link" href="#">购球讨论</a>
			</li>
		</ul>
		<form class="form-inline mx-20">
			<input class="form-control" type="text" placeholder="搜索">
			<button class="btn btn-success" type="button" id="search">搜索</button>
		</form>
	</nav>



	<div class="container-fluid" style="margin-top: 56px;">
		<div class="row">
			<div class="col-1" style=""></div>
			<div class="col-10" style="background-color: orange;">
				<div class="jumbotron jumbotron-fluid text-center"
					style="margin-top: 0px;">
					<h1>排球人聚集地</h1>
					<!-- <img src="/img/vb-top.png" class="img-fluid"> -->
				</div>
				<h1>888</h1>
				<h1>777</h1>
			</div>
			<div class="col-1" style=""></div>
		</div>
	</div>

	<footer id="footer">
		<section id="copyright">
			<p style="font-size: 20px; margin-bottom: 0">
				© 2019 <a href="/">后生仔、</a>
			</p>
		</section>
	</footer>

	<script src="/js/jquery.slim.min.js"></script>
	<script src="/js/popper.min.js"></script>
	<script src="/js/bootstrap4.0.min.js"></script>
	<script src="/js/layer.js"></script>
</body>
</html>