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
<link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap-theme.css" rel="stylesheet">
<link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.css" rel="stylesheet">
<link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
<script src="/js/jquery.slim.min.js"></script>
<script src="/js/jquery-1.7.min.js"></script>
<script src="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/bootstrap.js"></script>
<script src="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/js/bootstrap.min.js"></script>
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
					<li class="active"><a href="/newVbPlace">球场审核区</a></li>
					<li><a href="/goodsAudit">用品审核区</a></li>
					<li><a href="/postManage">发帖管理区</a></li>
					<li><a href="/userManage">用户管理区</a></li>
				</ul>
			</div>
		</div>
		<br>
		<form modelAttribute="PCA" id="pcaGo" action="/newVbPlace">
			<div class="row clearfix">
				<!-- 省市区三级联动开始 -->
				<div class="col-md-2 column">
					<div class="btn-group">
						<select id="province" name="province" path="province">
							<option>请选择省份</option>
						</select>
					</div>
				</div>
				<div class="col-md-2 column">
					<div class="btn-group">
						<select id="city" name="city" path="city">
							<option>请选择城市</option>
						</select>
					</div>
				</div>
				<div class="col-md-2 column">
					<div class="btn-group">
						<select id="area" name="area" path="area">
							<option>请选择地区</option>
						</select>
					</div>
				</div>

				<div class="col-md-2 column">
					<button type="button" class="btn btn-default" id="query">GO!</button>
				</div>
				<!-- 省市区三级联动结束 -->
			</div>
		</form>
		<br>
		<div class="row clearfix">
			<div class="col-md-12 column">
				<ul class="nav nav-pills">
					<li><a href="#">未审核</a></li>
					<li><a href="#">已审核</a></li>
					<li><a href="#">审核通过</a></li>
					<li><a href="#">审核不通过</a></li>
				</ul>
				<c:forEach items="${newVbPlaces}" var="newplace">
					<blockquote>
						<div class="row clearfix">
							<div class="col-md-3 column">
								<img alt="140x140"
									src="http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg" />
							</div>
							<div class="col-md-9 column">
								<div class="row clearfix">
									<div class="col-md-12 column text-center">
										<h3>【${newplace.province}-${newplace.city}-${newplace.area}】${newplace.location}</h3>
									</div>
								</div>
								<div class="row clearfix">
									<div class="col-md-4 column">
										<p>上传者：【${newplace.uploaderName}】</p>
									</div>
									<div class="col-md-4 column">
										<p>
											上传时间：
											<fmt:formatDate value="${newplace.uploadTime}"
												pattern="yyyy-MM-dd" />
										</p>
									</div>
									<div class="col-md-4 column text-right">
										<c:choose>
											<c:when test="${ newplace.isaudited == 1 }">
												<button type="button" class="btn btn-default"
													disabled="disabled">审核通过</button>
											</c:when>
											<c:when test="${ newplace.isaudited == 2 }">
												<button type="button" class="btn btn-default"
													disabled="disabled">审核不通过</button>
											</c:when>
											<c:otherwise>
												<a href="/auditVbPlace?id=${newplace.id}&audit=pass"><button type="button"
														class="btn btn-default" id="pass">通过</button></a>
												<a href="/auditVbPlace?id=${newplace.id}&audit=fail"><button type="button"
														class="btn btn-default" id="fail">不通过</button></a>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
						</div>
					</blockquote>
				</c:forEach>
			</div>
			<!-- 分页开始 -->
			<div style="text-align: center">
				<ul class="pagination">
					<li <c:if test="${pageInfo.pageNum==1}">class="disabled"</c:if>><a
						href="/newVbPlace?page=1">&laquo;</a></li>
					<c:forEach begin="1" end="${pageInfo.pages}" step="1" var="pageNo">
						<li <c:if test="${pageInfo.pageNum==pageNo}">class="active"</c:if>><a
							href="/newVbPlace?page=${pageNo}">${pageNo}</a></li>
					</c:forEach>
					<li
						<c:if test="${pageInfo.pageNum==pageInfo.pages}">class="disabled"</c:if>><a
						href="/newVbPlace?page=${pageInfo.pages}">&raquo;</a></li>
				</ul>
			</div>
			<!-- 分页结束 -->
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	$(function() {
        $("#query").click(function () {
            $("#pcaGo").submit();
        });
		$.ajax({
			url : "/static/json/city.json",
			type : "get",
			dataType : "json",
			success : function(data) {
			for ( var key in data) {
				$("#province").append("<option value="+key+">" + key + "</option>");
			}
		$("#province").change(function() {
			var now_province = $("#province option:selected").val();
				$("#city").empty();
				$("#city").append("<option >请选择城市</option>");
				for ( var key in data[now_province]) {
					$("#city").append("<option value="+key+">"+ key+ "</option>");
				}
					$("#city").change(function() {
					var now_city = $("#city option:selected").val();
					$("#area").empty();
					$("#area").append("<option >请选择地区</option>");
					for ( var k in data[now_province][now_city]) {
						$("#area").append("<option value="+data[now_province][now_city][k]+">" + data[now_province][now_city][k] + "</option>");
					}
		})})}})});
</script>