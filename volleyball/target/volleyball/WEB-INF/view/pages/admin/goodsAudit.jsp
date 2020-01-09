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
					<li class="active"><a href="/goodsAudit">用品审核区</a></li>
					<li><a href="/postManage">发帖管理区</a></li>
					<li><a href="/userManage">用户管理区</a></li>
				</ul>
			</div>
		</div>
		<br>
		<div class="row clearfix">
			<div class="col-md-4 column"></div>
			<div class="col-md-4 column">
				<ul class="nav nav-pills">
					<li><a href="/goodsAudit?kind=1">排球</a></li>
					<li><a href="/goodsAudit?kind=2">护具</a></li>
					<li><a href="/goodsAudit?kind=3">鞋服</a></li>
					<li><a href="/goodsAudit?kind=4">其他</a></li>
				</ul>
			</div>
			<div class="col-md-4 column text-right"></div>
		</div>
		<br>
		<div class="row clearfix">
			<div class="col-md-12 column">
				<c:forEach items="${newGoods}" var="newGoods">
					<div class="col-md-3 column">
						<div class="card" style="width: 200px">
							<img class="card-img-top" alt="Card image" style="width: 100%"
								src="/images/goods/${newGoods.img}" />
							<div class="card-body">
								<h4 class="card-title">型号：${newGoods.name }</h4>
								<h4 class="card-title">
									类别：
									<c:if test="${newGoods.kind == 1}">排球</c:if>
									<c:if test="${newGoods.kind == 2}">护具</c:if>
									<c:if test="${newGoods.kind == 3}">鞋服</c:if>
									<c:if test="${newGoods.kind == 4}">其他</c:if>
								</h4>
								<h4 class="card-title">售价：${newGoods.price }RMB</h4>
								<h4 class="card-title">上传者： ${newGoods.uploaderName }</h4>
								<h4 class="card-title">
									上传时间：
									<fmt:formatDate value="${newGoods.uploadTime}"
										pattern="yyyy-MM-dd" />
								</h4>
							</div>
							<div class="card-footer">
								<c:choose>
									<c:when test="${ newGoods.isaudited == 1 }">
										<button type="button" class="btn btn-default"
											disabled="disabled">审核通过</button>
									</c:when>
									<c:when test="${ newGoods.isaudited == 2 }">
										<button type="button" class="btn btn-default"
											disabled="disabled">审核不通过</button>
									</c:when>
									<c:otherwise>
										<a href="/auditGoods?id=${newGoods.id}&audit=pass"><button
												type="button" class="btn btn-default" id="pass">通过</button></a>
										<a href="/auditGoods?id=${newGoods.id}&audit=fail"><button
												type="button" class="btn btn-default" id="fail">不通过</button></a>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
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
							href="/goodsAudit?page=1">&laquo;</a></li>
						<c:forEach begin="1" end="${pageInfo.pages}" step="1" var="pageNo">
							<li
								<c:if test="${pageInfo.pageNum==pageNo}">class="active"</c:if>><a
								href="/goodsAudit?page=${pageNo}">${pageNo}</a></li>
						</c:forEach>
						<li
							<c:if test="${pageInfo.pageNum==pageInfo.pages}">class="disabled"</c:if>><a
							href="/goodsAudit?page=${pageInfo.pages}">&raquo;</a></li>
					</ul>
				</div>
				<!-- 分页结束 -->
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	$(function() {
		/* $("#query").click(function() {
			$("#pcaGo").submit();
		}); */
	});
</script>