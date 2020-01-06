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
					<li><a href="/postManage">发帖管理区</a></li>
					<li class="active"><a href="/userManage">用户管理区</a></li>
				</ul>
			</div>
		</div>
		<br>
		<div class="row clearfix">
			<div class="col-md-12 column">
				<form modelAttribute="User" id="userSearch" action="/userManage">
					<div class="row clearfix">
						<div class="col-md-12 column input-group text-center">
							<div class="text-center">
								<label>昵称：</label><input type="text" name="name"
									value="${user.name }"> &nbsp;&nbsp;&nbsp;&nbsp;<label>邮箱：</label><input
									type="text" name="email" value="${user.email }" />&nbsp;&nbsp;&nbsp;&nbsp;
								<select name="sex" id="sex">
									<option value="">性别</option>
									<option value="0">男</option>
									<option value="1">女</option>
								</select>&nbsp;&nbsp;&nbsp;&nbsp; <select name="status" id="status">
									<option value="">状态</option>
									<option value="0">正常</option>
									<option value="1">冻结</option>
								</select>&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="submit" class="btn btn-primary" id="upload">搜索</button>
							</div>
						</div>
					</div>
				</form>
				<c:forEach items="${users}" var="users">
					<blockquote>
						<div class="row clearfix">
							<div class="col-md-3 column">
								<img alt="40x40"
									src="http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg" />
							</div>
							<div class="col-md-9 column">
								<div class="row clearfix">
									<div class="col-md-4 column">
										<br>
										<h4>昵称：【${users.name}】</h4>
										<br>
										<h4>
											性别：
											<c:if test="${users.sex == 0}">【男】</c:if>
											<c:if test="${users.sex == 1}">【女】</c:if>
										</h4>
									</div>
									<br>
									<div class="col-md-5 column">
										<h4>邮箱：【${users.email}】</h4>
										<br>
										<h4>
											状态：
											<c:if test="${users.status == 0}">【正常】</c:if>
											<c:if test="${users.status == 1}">【冻结】</c:if>
										</h4>
									</div>
									<div class="col-md-3 column">
										<c:choose>
											<c:when test="${ users.status == 0 }">
												<a
													href="/changeStatus?id=${users.id}&status=1&name=${user.name}&email=${user.email}&sex=${user.sex}&userStatus=${user.status}&page=${pageInfo.pageNum}"><button
														type="button" class="btn btn-default">用户冻结</button></a>
												<br>
												<br>
											</c:when>
											<c:otherwise>
												<a
													href="/changeStatus?id=${users.id}&status=0&name=${user.name}&email=${user.email}&sex=${user.sex}&userStatus=${user.status}&page=${pageInfo.pageNum}"><button
														type="button" class="btn btn-default">用户解冻</button></a>
												<br>
												<br>
												<br>
											</c:otherwise>
										</c:choose>
										<%-- <a
											href="/deleteUser?id=${users.id}&name=${user.name}&email=${user.email}&sex=${user.sex}&status=${user.status}&page=${pageInfo.pageNum}"><button
												type="button" class="btn btn-default">用户删除</button></a> --%>
										<!-- 用户删除待启用，只能为逻辑删除 -->
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
						href="/userManage?page=1&name=${user.name}&email=${user.email}&sex=${user.sex}&status=${user.status}">&laquo;</a></li>
					<c:forEach begin="1" end="${pageInfo.pages}" step="1" var="pageNo">
						<li <c:if test="${pageInfo.pageNum==pageNo}">class="active"</c:if>><a
							href="/userManage?page=${pageNo}&name=${user.name}&email=${user.email}&sex=${user.sex}&status=${user.status}">${pageNo}</a></li>
					</c:forEach>
					<li
						<c:if test="${pageInfo.pageNum==pageInfo.pages}">class="disabled"</c:if>><a
						href="/userManage?page=${pageInfo.pages}&name=${user.name}&email=${user.email}&sex=${user.sex}&status=${user.status}">&raquo;</a></li>
				</ul>
			</div>
			<!-- 分页结束 -->
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	$(function() {
		var sex = "${user.sex}";
		$("#sex").find("option[value=" + sex + "]")
				.attr('selected', 'selected');
		var status = "${user.status}";
		$("#status").find("option[value=" + status + "]").attr('selected',
				'selected');
	});
</script>