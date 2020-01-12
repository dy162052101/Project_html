<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录界面</title>
	<style type="text/css">
		
		body{
			background:url(image/1.jpg) top left;background-size:cover;
			background-color:black;
		}
		table{
			opacity: 0.8;
			width:600px;
			height:200px;
			margin:150px  auto;
			background-color:#C0C0C0;
			color:white;
		}
		
		td,th{
			text-align:center;
			background-color:#505050 ;
		}
		#in1{
			
			width:98%;
			height:90%;
			font-size:20px;
			border:none;
		}
		#in2{
			
			width:98%;
			height:90%;
			font-size:20px;
			border:none;
		}
		#d{
			width:50%;
			height:100%;
			font-size:15px;
			float:left;
		}
		#d:hover{
			background-color:black;
			color:white;	
		}
		#q{
			width:50%;
			height:100%;
			font-size:15px;
			float:right;
		}
		#q:hover{
			background-color:black;
			color:white;	
		}
	</style>
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>

</head>
<body >
	<form action="DepartmentServlet?method=findLogin" method="post" id="myForm">
		<table>
			<tr >
				<th colspan="2">管理员登录</th>
			</tr>
			<tr>
				<td >用户名:</td>
				<td>
					<input type="text" name="username" id="in1" />
					<span id="span_name"></span>
				</td>
			</tr>
			<tr>
				<td>密码:</td>
				<td>
					<input type="password" name="password" id="in2" />
					<span id="span_pwd"></span>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="登录" id="d"/>
					<input type="reset" value="取消"id="q"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>