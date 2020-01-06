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
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="row clearfix">
					<div class="col-md-3 column text-left">
						<a href="/vbPlace?vbPlaceId=${vbPlaceId}"><button
								type="button" class="btn btn-default">返回</button></a>
					</div>
					<div class="col-md-3 column text-left">
						<ul class="nav nav-pills">
							<li><a href="#">最新发表</a></li>
							<li><a href="#">最多帖数</a></li>
							<li><a href="#">最高人气</a></li>
						</ul>
					</div>
					<div class="col-md-3 column input-group text-right">
						<input type="text" class="form-control input-lg" id="searchTitle"><span
							class="input-group-addon btn btn-primary" id="search">搜索</span>
					</div>
					<div class="col-md-3 column text-right">
						<!-- 按钮触发模态框 -->
						<c:choose>
							<c:when test="${ sessionScope.user != null }">
								<button type="button" class="btn btn-primary btn-lg"
									data-toggle="modal" data-target="#myModal">发帖</button>
							</c:when>
							<c:otherwise>
								<a href="/login"><button type="button"
										class="btn btn-default btn-lg" disabled="disabled">发帖</button></a>
							</c:otherwise>
						</c:choose>
					</div>
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
									<div class="col-md-12 column text-center">
										<a
											href="/postDetail?followid=${post.id}&vbPlaceId=${vbPlaceId}"><button
												type="button" class="btn btn-default">看帖</button></a>
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
					<li <c:if test="${pageInfo.pageNum==1}">class="disabled"</c:if>><a
						href="/post?page=1&vbPlaceId=${vbPlaceId }">&laquo;</a></li>
					<c:forEach begin="1" end="${pageInfo.pages}" step="1" var="pageNo">
						<li <c:if test="${pageInfo.pageNum==pageNo}">class="active"</c:if>><a
							href="/post?page=${pageNo}&vbPlaceId=${vbPlaceId }">${pageNo}</a></li>
					</c:forEach>
					<li
						<c:if test="${pageInfo.pageNum==pageInfo.pages}">class="disabled"</c:if>><a
						href="/post?page=${pageInfo.pages}&vbPlaceId=${vbPlaceId }">&raquo;</a></li>
				</ul>
			</div>
			<!-- 分页结束 -->
		</div>
	</div>
	<!-- 模态框（Modal） -->
	<form modelAttribute="Post" id="postSubmit" action="/submitPost">
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">发帖</h4>
					</div>
					<div class="modal-body">
						<div class="row clearfix">
							<div class="col-md-12 column">
								<div class="form-inline">
									<div class="input-group">
										<span class="input-group-addon">标题</span> <input type="text"
											class="form-control" name="title" id="title">
									</div>
									<br />
									<div class="input-group">
										<span class="input-group-addon">内容</span>
										<textarea id="content" class="form-control" rows="5"
											name="content" placeholder="文明上网，理性发言"></textarea>
									</div>
									<div class="input-group">
										<input type="hidden" class="form-control" name="vbplaceid"
											id="vbplaceid" value="${vbPlaceId}">
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="po">提交</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
	</form>
</body>
</html>
<script type="text/javascript">
	$(function() {
		$("#po").click(function() {
			$("#postSubmit").submit();
		});
		$("#search").click(function() {
			var searchTitle = document
					.getElementById("searchTitle").value;
			var vbPlaceId = "${vbPlaceId}";
			window.location.href = '/post?title=' + searchTitle
					+ '&vbPlaceId=' + vbPlaceId;
		});
	});
</script>