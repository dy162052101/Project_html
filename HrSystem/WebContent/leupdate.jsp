<%@page import="cn.hr.pojo.Lea"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户修改</title>
<style>
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
	 Lea le=(Lea)request.getAttribute("le");
	 %>
	 <form action="LeaServlet?method=doUpdateLea" method="post">
	 	<table>
	 		<tr>
	 			<td>ID：</td>
	 			<td><input type="text" name="id" value="<%=le.getId()%>" readonly="readonly"id="i1"/></td>
	 		</tr>
	 		 <tr>
				<td>员工工号</td>
				<td><input type="text" name="employee_number" value="<%=le.getEmployee_number()%>"id="i1"/></td>
			</tr>
			<tr>
				<td>部门编号</td>
				<td><input type="text" name="department_number" value="<%=le.getDepartment_number()%>"id="i1"/></td>
			</tr>
			<tr>
				<td>开始时间</td>
				<td>
					<input type="date" name="start_time" value="<%=le.getStart_time()%>"id="i1"/>
				</td>
			</tr>
			<tr>
				<td>结束时间</td>
				<td><input type="date" name="end_time" value="<%=le.getEnd_time()%>"id="i1"/></td>
			</tr>
			<tr>
				<td>天数</td>
				<td><input type="text" name="days" value="<%=le.getDays()%>"id="i1"/></td>
			</tr>
			<tr>
				<td>请假事由</td>
				<td><input type="text" name="reason" value="<%=le.getReason()%>"id="i1"/></td>
			</tr>
			<tr>
				<td>请假类型</td>
				<td>
					<%
	 				String type=le.getType();
	 				if("事假".equals(type)){
	 				%>
	 				<input type="radio" name="type" value="1" checked="checked"/>事假
	 				<input type="radio" name="type" value="2" />病假
	 				<%		
	 				}else{
	 				%>	
	 					<input type="radio" name="type" value="1" />事假
	 					<input type="radio" name="type" value="2" checked="checked"/>病假
	 				<%
	 				}
	 				%>
				</td>
			</tr>
			<tr>
				<td>部门领导</td>
				<td><input type="text" name="manager" value="<%=le.getManager()%>"id="i1"/></td>
			</tr>
			<tr>
				<td>请假状态</td>
				<td>
					<%
	 				String status=le.getStatus();
	 				if("已批准".equals(status)){
	 				%>
	 				<input type="radio" name="status" value="1" checked="checked"/>已批准
	 				<input type="radio" name="status" value="2"/>未批准
	 				<%		
	 				}else{
	 				%>	
	 					<input type="radio" name="status" value="1" />已批准
	 					<input type="radio" name="status" value="2" checked="checked"/>未批准
	 				<%
	 				}
	 				%>
				</td>
			</tr>
			<tr>
				<td>备注</td>
				<td><input type="text" name="notes" value="<%=le.getNotes()%>"id="i1"/></td>
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