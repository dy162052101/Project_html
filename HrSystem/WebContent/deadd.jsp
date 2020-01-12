<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加用户信息</title>
<style type="text/css">
	table{
		width:600px;
		height:300px;
		margin:0 auto;
	}
	td{
		opacity: 0.8;
		width:100px;
		height:30px;
		text-align:center;
		color:white;
		background-color:black;
	}
	th{
		height:50px;
		text-align:center;
		color:white;
		background-color:black;
	}
	#i1{width:95%;height:90%;}
	#i2{
		float:left;
		background-color:rgba(0,0,0,0);
		color:white;
		border:none;
		height:100%;
		width:50%;
	}
	#i2:hover{background-color:#707070;}
	#i3{
		float:right;
		background-color:rgba(0,0,0,0);
		color:white;
		border:none;
		height:100%;
		width:50%;
	}
	#i3:hover{background-color:#707070;}
</style>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
</head>


<body>
<!-- 需要用户填写的所要增加的信息.... -->
	<form action="DepartmentServlet?method=addDepartment" method="post">
		<table>
			<tr>
				<td>部门编码</td>
				<td><input type="text" name="department_number"id="i1"/></td>
			</tr>
			<tr>
				<td>部门名称</td>
				<td><input type="text" name="name"id="i1"/></td>
			</tr>
			<tr>
				<td>部门领导</td>
				<td><input type="text" name="manager"id="i1"/></td>
			</tr>
			<tr>
				<td>电话</td>
				<td><input type="text" name="telephone"id="i1"/></td>
			</tr>
			<tr>
				<td>地址</td>
				<td><input type="text" name="address"id="i1"/></td>
			</tr>
			<tr>
				<td>备注</td>
				<td><input type="text" name="notes"id="i1"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="增加" id="i2"/><input  type="reset" value="重置" id="i3"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>