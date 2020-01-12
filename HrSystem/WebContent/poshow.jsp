<%@page import="cn.hr.pojo.Position"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户修改</title>
<style type="text/css">
	table{
		width:600px;
		height:300px;
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
	 Position po=(Position)request.getAttribute("po");
	 %>
	 	<table>
	 		<tr>
	 			<td>ID：</td>
	 			<td><input type="text" name="id" value="<%=po.getId()%>" readonly="readonly"/></td>
	 		</tr>
	 		 <tr>
				<td>职称编号</td>
				<td><input type="text" name="position_number" value="<%=po.getPosition_number()%>"readonly="readonly"/></td>
			</tr>
			<tr>
				<td>职称名称</td>
				<td><input type="text" name="name" value="<%=po.getName()%>"readonly="readonly"/></td>
			</tr>
			<tr>
				<td>职称级别</td>
				<td>
					<input type="text" name="level" value="<%=po.getLevel()%>" id="a"readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td>备注</td>
				<td><input type="text" name="notes" value="<%=po.getNotes()%>"readonly="readonly"/></td>
			</tr>
	 		<tr>
	 			<td colspan="2">
	 				<a href="polist.jsp">返回</a>
	 			</td>
	 		</tr>
	 	</table>
</body>
</html>