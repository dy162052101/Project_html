
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
<style type="text/css">
		#home{
			float:right;
			width:61px;
			height:61px;
			background-color:rgb(50,50,50,0);
			z-index: 11;
		}
		#home:hover{background-color:rgb(150,150,150);}
		#image{
			
			width:45px;
			height:45px;
			margin:7px auto;
		}
		#m1{
			z-index: 10;
			position:fixed;
			top:0;
			background-color:rgb(90,90,90);
			color:white;
			text-align:center;
			width:100%;
			margin:auto;
			height:61px;
			line-height:61px;
			font-size:20px;
			
		}
		#m2{
			background-color:rgb(100,100,100);
			height:700px;
			width:20%;
			float:left;
			margin-top:61px;
		}
		#m3{
			background-color:#C0C0C0;
			color:white;
			height:700px;
			width:80%;
			float:right;
			margin-top:61px;
			
		}
		#f1{
			background-color:#888888;
			text-align:center;
		}
		table{
			width:100%;
		}
		.t{
			text-align:center;
		}
		.tag{
			text-align:center;
		}
		a{
			font-weight:bold;
			float:left;
			width:100%;
			height:60px;
			line-height:50px;
			text-decoration:none; 
			text-align:center;
			color:white;
		}
		a:hover{color:black;}
		#d0{
			outline:none;
			color:white;
			user-select:none;
			cursor:pointer;
			font-weight:bold;
			width:100%;
			height:50px;
			line-height:50px;
			text-align:center;
			background-color:black;
		}
		#d0:hover{
			background-color:#C0C0C0;
			color:black;
		}
		#p0{
			color:white;
			outline:none;
			user-select:none;
			cursor:pointer;
			font-weight:bold;
			width:100%;
			height:50px;
			line-height:50px;
			text-align:center;
			background-color:black;
		}
		#p0:hover{
			background-color:#C0C0C0;
			color:black;
		}
		#l0{
			color:white;
			user-select:none;
			cursor:pointer;
			outline:none;
			font-weight:bold;
			width:100%;
			height:50px;
			line-height:50px;
			text-align:center;
			background-color:black;
		}
		#l0:hover{
			background-color:#C0C0C0;
			color:black;
		}
		#d1{
			user-select:none;
			cursor:pointer;
			opacity: 0.8;
			width:100%;
			height:50px;
			line-height:50px;
			text-align:center;
			background-color:rgb(100,100,100);
		}
		#d1:hover{background-color:#B0B0B0;}
		#d2 {
			user-select:none;
			cursor:pointer;
			opacity: 0.8;
			width:100%;
			height:50px;
			line-height:50px;
			text-align:center;
			background-color:rgb(100,100,100);
		}
		#d2:hover{background-color:#B0B0B0;}
		#p1 {
			opacity: 0.8;
			width:100%;
			height:50px;
			text-align:center;
			background-color:rgb(100,100,100);
		}
		#p1:hover{background-color:#B0B0B0;}
		#p2{
			opacity: 0.8;
			width:100%;
			height:50px;
			text-align:center;
			background-color:rgb(100,100,100);
		}
		#p2:hover{background-color:#B0B0B0;}
		#l1 {
			opacity: 0.8;
			width:100%;
			height:50px;
			text-align:center;
			background-color:rgb(100,100,100);
		}
		#l1:hover{background-color:#B0B0B0;}
		#l2 {
			opacity: 0.8;
			width:100%;
			height:50px;
			text-align:center;
			background-color:rgb(100,100,100);
		}
		#l2:hover{background-color:#B0B0B0;}
	</style>

<script type="text/javascript">
	function show(){
		document.all["d1"].style.display="block";
	}
</script>
</head>
<body marginwidth="0" marginheight="0">
	
	<div id="m1">
		HR管理系统
		<a href="welcome.jsp" id="home" target="f2"><img src="image/home.png" id="image"/></a>
	</div>
	<div id="m2">
		<table>
			<details >
				<summary id="d0" >部门信息管理</summary>
				<p id="d1"><a href="delist.jsp" target="f2">部门信息列表</a></p>
				<p id="d2"><a href="deadd.jsp" target="f2" >部门信息添加</a></p>
			</details>
			<details >
				<summary id="p0">员工职称信息管理</summary>
				<p id="p1"><a href="polist.jsp" target="f2">员工职称信息列表</a></p>
				<p id="p2"><a href="poadd.jsp" target="f2" >员工职称信息添加</a></p>
			</details>
			<details >
				<summary id="l0">请假员工信息管理</summary>
				<p id="l1"><a href="lelist.jsp" target="f2" >请假员工信息列表</a></p>
				<p id="l2"><a href="leadd.jsp" target="f2" >请假员工信息添加</a></p>
			</details>
			
		</table>
	</div>
	<div id="m3">
		<iframe src="welcome.jsp"  id="f1" name="f2"width="100%"height="100%"frameborder="no" border="0">
		</iframe>
	</div>
</body>
</html>