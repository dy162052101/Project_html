<%@page import="cn.hr.pojo.Department"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户修改</title>
<style type="text/css">
	table{
		width:400px;
		height:400px;
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
</head>
<body>
	<!-- 
	本页面，需要获取请求中保存的用户信息，再把用户信息显示到页面表单元素中
	 -->
	 <%
	 Department de=(Department)request.getAttribute("de");
	 %>
	 <form action="DepartmentServlet?method=doUpdateDepartment" method="post">
	 	<table>
	 		<tr>
	 			<td>ID：</td>
	 			<td><input type="text" name="id" value="<%=de.getId()%>" readonly="readonly"id="i1"/></td>
	 		</tr>
	 		 <tr>
	 			<td>部门编码：</td>
	 			<td><input type="text" name="department_number" value="<%=de.getDepartment_number()%>" id="i1"/></td>
	 		</tr>
	 		<tr>
	 			<td>部门名称：</td>
	 			<td><input type="text" name="name" value="<%=de.getName()%>" id="i1"/></td>
	 		</tr>
	 		<tr>
	 			<td>部门领导：</td>
	 			<td><input type="text" name="manager" value="<%=de.getManager()%>" id="i1"/></td>
	 		</tr>
	 		<tr>
	 			<td>电话：</td>
	 			<td><input type="text" name="telephone" value="<%=de.getTelephone()%>" id="i1"/></td>
	 		</tr>
	 		<tr>
	 			<td>地址：</td>
	 			<td><input type="text" name="address" value="<%=de.getAddress()%>" id="i1"/></td>
	 		</tr>
	 		<tr>
	 			<td>备注：</td>
	 			<td><input type="text" name="notes" value="<%=de.getNotes()%>" id="i1"/></td>
	 		</tr> 
	 		<tr>
	 			<td colspan="2">
	 				<input type="submit" value="修改" id="i2"/> <input type="reset" value="重置" id="i3"/> 
	 			</td>
	 		</tr>
	 	</table>
	 </form>
</body>
</html>