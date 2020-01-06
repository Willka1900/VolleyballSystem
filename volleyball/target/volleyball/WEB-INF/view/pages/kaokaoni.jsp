<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>省市区三级联动下拉菜单</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=path %>/js/jquery-1.7.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/json-minified.js"></script>
  </head>
  
  <body>
  省份：
    <select name="province" id="province" onchange="onSelectChange(this,'city');">
    </select>
  城市：
  <select name="city" id="city" onchange="onSelectChange(this,'district');">
  	<option value="">请选择</option>
  </select>
  区(县)：
  <select name="district" id="district">
  	<option value="">请选择</option>
  </select>
  </body>
</html>
<script type="text/javascript">

function onSelectChange(obj,toSelId){
	setSelect(obj.value,toSelId);
}


function setSelect(fromSelVal,toSelId){
	//alert(document.getElementById("province").selectedIndex);
	document.getElementById(toSelId).innerHTML="";
	jQuery.ajax({
	  url: "<%=path%>/getDropdownDataServlet",
	  cache: false,
	  data:"parentId="+fromSelVal,
	  success: function(data){
	    createSelectObj(data,toSelId);
	  }
	});
}

function createSelectObj(data,toSelId){
	var arr = jsonParse(data);
	if(arr != null && arr.length>0){
		var obj = document.getElementById(toSelId);
		obj.innerHTML="";
		var nullOp = document.createElement("option");
		nullOp.setAttribute("value","");
		nullOp.appendChild(document.createTextNode("请选择"));
		obj.appendChild(nullOp);
		for(var o in arr){
			var op = document.createElement("option");
			op.setAttribute("value",arr[o].id);
			//op.text=arr[o].name;//这一句在ie下不起作用，用下面这一句或者innerHTML
			op.appendChild(document.createTextNode(arr[o].name));
			obj.appendChild(op);
		}
		
	}
}

setSelect('1','province');
</script>
