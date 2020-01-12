<%@page import="cn.hr.pojo.Lea"%>
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
		height:30px;
		text-align:center;
		color:white;
		background-color:black;
	}
	input{height:90%;width:95%;}
	a{
			float:left;
			width:100%;
			height:30px;
			line-height:30px;
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
	 Lea le=(Lea)request.getAttribute("le");
	 %>
	 	<table>
	 		<tr>
	 			<td>ID：</td>
	 			<td><input type="text" name="id" value="<%=le.getId()%>" readonly="readonly"/></td>
	 		</tr>
	 		 <tr>
				<td>员工工号</td>
				<td><input type="text" name="employee_number" value="<%=le.getEmployee_number()%>"readonly="readonly"/></td>
			</tr>
			<tr>
				<td>部门编号</td>
				<td><input type="text" name="department_number" value="<%=le.getDepartment_number()%>"readonly="readonly"/></td>
			</tr>
			<tr>
				<td>开始时间</td>
				<td>
					<input type="text" name="start_time" value="<%=le.getStart_time()%>" id="a"readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td>结束时间</td>
				<td><input type="text" name="end_time" value="<%=le.getEnd_time()%>"readonly="readonly"/></td>
			</tr>
			<tr>
				<td>天数</td>
				<td><input type="text" name="days" value="<%=le.getDays()%>"readonly="readonly"/></td>
			</tr>
			<tr>
				<td>请假事由</td>
				<td><input type="text" name="reason" value="<%=le.getReason()%>"readonly="readonly"/></td>
			</tr>
			<tr>
				<td>请假类型</td>
				<td><input type="text" name="type" value="<%=le.getType()%>"readonly="readonly"/></td>
			</tr>
			<tr>
				<td>部门领导</td>
				<td><input type="text" name="manager" value="<%=le.getManager()%>"readonly="readonly"/></td>
			</tr>
			<tr>
				<td>请假状态</td>
				<td><input type="text" name="status" value="<%=le.getStatus()%>"readonly="readonly"/></td>
			</tr>
			<tr>
				<td>备注</td>
				<td><input type="text" name="notes" value="<%=le.getNotes()%>"readonly="readonly"/></td>
			</tr>
	 		<tr>
	 			<td colspan="2" >
	 				<a href="lelist.jsp">返回</a>
	 			</td>
	 		</tr>
	 	</table>
</body>
</html>