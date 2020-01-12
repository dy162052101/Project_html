<%@page import="cn.hr.pojo.Position"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户修改</title>
<style>
	table{
		width:900px;
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
	 Position po=(Position)request.getAttribute("po");
	 %>
	 <form action="PositionServlet?method=doUpdatePosition" method="post">
	 	<table>
	 		<tr>
	 			<td>ID：</td>
	 			<td><input type="text" name="id" value="<%=po.getId()%>" id="i1"/></td>
	 		</tr>
	 		 <tr>
				<td>职称编号</td>
				<td><input type="text" name="position_number" value="<%=po.getPosition_number()%>"id="i1"/></td>
			</tr>
			<tr>
				<td>职称名称</td>
				<td><input type="text" name="name" value="<%=po.getName()%>"id="i1"/></td>
			</tr>
			<tr>
				<td>职称级别</td>
				<td>
					<%
	 				String level=po.getLevel();
	 				if("人事部员工".equals(level)){
	 				%>
	 				<input type="radio" name="level" value="1" checked="checked"/>人事部员工
	 				<input type="radio" name="level" value="2" />人事部主任
	 				<input type="radio" name="level" value="3" />部门员工
	 				<input type="radio" name="level" value="4" />部门主任
	 				<%		
	 				}else if("人事部主任".equals(level)){
	 				%>	
	 					<input type="radio" name="level" value="1" />人事部员工
	 					<input type="radio" name="level" value="2" checked="checked"/>人事部主任
	 					<input type="radio" name="level" value="3" />部门员工
	 					<input type="radio" name="level" value="4" />部门主任
	 				<%
	 				}else if("部门员工".equals(level)){
	 				%>	
	 					<input type="radio" name="level" value="1" />人事部员工
	 					<input type="radio" name="level" value="2" />人事部主任
	 					<input type="radio" name="level" value="3" checked="checked"/>部门员工
	 					<input type="radio" name="level" value="4" />部门主任
	 				<%
	 				}else if("部门主任".equals(level)){
	 				%>	
	 					<input type="radio" name="level" value="1" />人事部员工
	 					<input type="radio" name="level" value="2" />人事部主任
	 					<input type="radio" name="level" value="3" />部门员工
	 					<input type="radio" name="level" value="4" checked="checked"/>部门主任
	 				<%
	 				}
	 				%>
				</td>
			</tr>
			<tr>
				<td>备注</td>
				<td><input type="text" name="notes" value="<%=po.getNotes()%>"id="i1"/></td>
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