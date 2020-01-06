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
				<h4>【${sessionScope.admin.name}】</h4>
				<a href="/admin/logout"><button type="button"
						class="btn btn-default">管理员登出</button></a>
			</div>
		</div>
		<br> <br> <br>

		<div class="row clearfix">
			<div class="col-md-12 column">
				<ul class="nav nav-tabs">
					<li><a href="/admin">首页</a></li>
					<li><a href="/newVbPlace">球场审核区</a></li>
					<li><a href="/goodsAudit">用品审核区</a></li>
					<li class="active"><a href="/postManage">发帖管理区</a></li>
					<li><a href="/userManage">用户管理区</a></li>
				</ul>
			</div>
		</div>
		<br>
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="row clearfix">
					<div class="col-md-4 column"></div>
					<div class="col-md-4 column input-group text-center">
						<input type="text" class="form-control input-lg" id="searchTitle"
							placeholder="标题"><span
							class="input-group-addon btn btn-primary" id="search">搜索</span>
					</div>
					<div class="col-md-4 column"></div>
				</div>
				<c:forEach items="${post}" var="post">
					<blockquote>
						<div class="row clearfix">
							<div class="col-md-3 column">
								<img alt="140x140"
									src="http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg" />
							</div>
							<div class="col-md-9 column">
								<div class="row clearfix">
									<div class="col-md-12 column text-center">
										<h3>${post.title }</h3>
									</div>
								</div>
								<div class="row clearfix">
									<div class="col-md-4 column">
										<p>发帖者：【${post.publisherName}】</p>
									</div>
									<div class="col-md-4 column">
										<p>
											发帖时间：
											<fmt:formatDate value="${post.publishTime}"
												pattern="yyyy-MM-dd" />
										</p>
									</div>
									<div class="col-md-4 column">
										<p>跟帖数 ：暂无</p>
									</div>
								</div>
								<br>
								<div class="row clearfix">
									<div class="col-md-6 column text-right">
										<a href="/postDetailManage?followid=${post.id}&title=${title}"><button
												type="button" class="btn btn-default">看帖</button></a>
									</div>
									<div class="col-md-6 column text-right">
										<a href="/postDelete?followid=${post.id}&title=${title}"><button
												type="button" class="btn btn-default">删帖</button></a>
									</div>
								</div>
							</div>
						</div>
					</blockquote>
					<hr style="height: 1px; border: none; border-top: 1px solid #ddd;" />
				</c:forEach>
			</div>
			<!-- 分页开始 -->
			<div style="text-align: center">
				<ul class="pagination">
					<li
						<c:if test="${pageInfo.pageNum==1}">class="disabled"
						</c:if>><a
						href="/postManage?page=1&title=${title }">&laquo;</a></li>
					<c:forEach begin="1" end="${pageInfo.pages}" step="1" var="pageNo">
						<li <c:if test="${pageInfo.pageNum==pageNo}">class="active"</c:if>><a
							href="/postManage?page=${pageNo}&title=${title }">${pageNo}</a></li>
					</c:forEach>
					<li
						<c:if test="${pageInfo.pageNum==pageInfo.pages}">class="disabled"</c:if>><a
						href="/postManage?page=${pageInfo.pages}&title=${title }">&raquo;</a></li>
				</ul>
			</div>
			<!-- 分页结束 -->
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	$(function() {
		$("#search").click(function() {
			var searchTitle = document.getElementById("searchTitle").value;
			window.location.href = '/postManage?title=' + searchTitle;
		});
	});
</script>