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
		opacity: 0.6;
		width:100px;
		height:40px;
		text-align:center;
		color:white;
		background-color:black;
	}
	input{height:90%;width:95%;}
	a{
			float:left;
			width:100%;
			height:40px;
			line-height:40px;
			font-family:'Microsoft JhengHei';
			text-decoration: none;
			color:white;
	}
	a:hover{background-color:white;color:black;}
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
	 			<td><input type="text" name="id" value="<%=de.getId()%>"readonly="readonly" /></td>
	 		</tr>
	 		 <tr>
	 			<td>部门编码：</td>
	 			<td><input type="text" name="department_number" value="<%=de.getDepartment_number()%>" readonly="readonly"/></td>
	 		</tr>
	 		<tr>
	 			<td>部门名称：</td>
	 			<td><input type="text" name="name" value="<%=de.getName()%>"readonly="readonly" /></td>
	 		</tr>
	 		<tr>
	 			<td>部门领导：</td>
	 			<td><input type="text" name="manager" value="<%=de.getManager()%>" readonly="readonly"/></td>
	 		</tr>
	 		<tr>
	 			<td>电话：</td>
	 			<td><input type="text" name="telephone" value="<%=de.getTelephone()%>" readonly="readonly"/></td>
	 		</tr>
	 		<tr>
	 			<td>地址：</td>
	 			<td><input type="text" name="address" value="<%=de.getAddress()%>" readonly="readonly"/></td>
	 		</tr>
	 		<tr>
	 			<td>备注：</td>
	 			<td><input type="text" name="notes" value="<%=de.getNotes()%>" readonly="readonly"/></td>
	 		</tr> 
	 		<tr>
	 			<td colspan="2">
	 				<a href="delist.jsp">返回</a>
	 			</td>
	 		</tr>
	 	</table>
	 </form>
</body>
</html>