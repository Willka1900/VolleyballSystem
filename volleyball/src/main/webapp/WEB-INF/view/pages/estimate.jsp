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
					<li><a href="/vbPlace">球场</a></li>
					<li class="active"><a href="/shopping">购物</a></li>
					<li><a href="/about">关于</a></li>
				</ul>
			</div>
		</div>
		<br>
		<div class="row clearfix">
			<div class="col-md-4 column text-left">
				<a href="/shopping?kind=${goods.kind }"><button type="button"
						class="btn btn-default">返回</button></a>
			</div>
			<br>
			<div class="col-md-4 column text-center">
				<div class="card" style="width: 200px">
					<img class="card-img-top" src="/static/images/Mikasa.jpg"
						alt="Card image" style="width: 100%">
					<div class="card-body">
						<h4 class="card-title">型号：${goods.name }</h4>
						<h4 class="card-title">售价：${goods.price }RMB</h4>
					</div>
					<div class="card-footer"></div>
				</div>
			</div>
			<div class="col-md-4 column text-right">
				<!-- 按钮触发模态框 -->

				<c:choose>
					<c:when test="${ sessionScope.user != null }">
						<button type="button" class="btn btn-primary btn-lg"
							data-toggle="modal" data-target="#myModal">评价</button>
					</c:when>
					<c:otherwise>
						<a href="/login"><button type="button"
								class="btn btn-default btn-lg" disabled="disabled">评价</button></a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<br>
		<div class="row clearfix">
			<c:forEach items="${estimates}" var="estimate">
				<div class="col-md-12 column text-center">
					<blockquote>
						<div class="row clearfix">
							<div class="col-md-3 column">
								<img alt="40x40"
									src="http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg" />
							</div>
							<div class="col-md-9 column text-left">
								<h3>${estimate.publisherName }:</h3>
								<p>${estimate.content }</p>
								<br> 发帖时间：
								<fmt:formatDate value="${estimate.publishTime}"
									pattern="yyyy-MM-dd" />
							</div>
						</div>
					</blockquote>
					<hr style="height: 1px; border: none; border-top: 1px solid #ddd;" />
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-12 column">
			<!-- 分页开始 -->
			<div style="text-align: center">
				<ul class="pagination">
					<li <c:if test="${pageInfo.pageNum==1}">class="disabled"</c:if>><a
						href="/estimate?page=1&goodsId=${goods.id }">&laquo;</a></li>
					<c:forEach begin="1" end="${pageInfo.pages}" step="1" var="pageNo">
						<li <c:if test="${pageInfo.pageNum==pageNo}">class="active"</c:if>><a
							href="/estimate?page=${pageNo}&goodsId=${goods.id }">${pageNo}</a></li>
					</c:forEach>
					<li
						<c:if test="${pageInfo.pageNum==pageInfo.pages}">class="disabled"</c:if>><a
						href="/estimate?page=${pageInfo.pages}&goodsId=${goods.id }">&raquo;</a></li>
				</ul>
			</div>
			<!-- 分页结束 -->
		</div>
	</div>
	<!-- 模态框（Modal） -->
	<form modelAttribute="Estimate" id="estimateSubmit"
		action="/submitEstimate">
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">评价用品</h4>
					</div>
					<div class="modal-body">
						<div class="row clearfix">
							<div class="col-md-12 column">
								<div class="form-inline">
									<div class="input-group">
										<span class="input-group-addon">昵称</span> <input type="text"
											class="form-control" name="name" id="name"
											value="${sessionScope.user.name}" disabled="disabled">
									</div>
									<br />
									<div class="input-group">
										<span class="input-group-addon">内容</span>
										<textarea id="content" class="form-control" rows="5"
											name="content" placeholder="文明上网，理性发言"></textarea>
									</div>
									<div class="input-group">
										<input type="hidden" class="form-control" name="goodsId"
											id="goodsId" value="${goods.id }">
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="es">提交</button>
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
		$("#es").click(function() {
			$("#estimateSubmit").submit();
		});
	});
</script>