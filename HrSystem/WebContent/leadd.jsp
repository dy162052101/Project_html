<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加用户信息</title>
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
<script type="text/javascript">
	
</script>
</head>


<body>
<!-- 需要用户填写的所要增加的信息.... -->
	<form action="LeaServlet?method=addLea" method="post">
		<table>
			<tr>
				<td>员工工号</td>
				<td><input type="text" name="employee_number" id="i1"/></td>
			</tr>
			<tr>
				<td>部门编号</td>
				<td><input type="text" name="department_number"id="i1"/></td>
			</tr>
			<tr>
				<td>开始时间</td>
				<td><input type="date" name="start_time"id="i1"/></td>
			</tr>
			<tr>
				<td>结束时间</td>
				<td><input type="date" name="end_time"id="i1"/></td>
			</tr>
			<tr>
				<td>天数</td>
				<td><input type="text" name="days"id="i1"/></td>
			</tr>
			<tr>
				<td>请假事由</td>
				<td><input type="text" name="reason" id="i1"/></td>
			</tr>
			<tr>
				<td>请假类型</td>
				<td>
					<input type="radio" name="type" value="1" />
					<lable >事假</lable>
					<input type="radio" name="type" value="2" />
					<lable >病假</lable>
				</td>
			</tr>
			<tr>
				<td>部门领导</td>
				<td><input type="text" name="manager" id="i1"/></td>
			</tr>
			<tr>
				<td>请假状态</td>
				<td>
					<input type="radio" name="status" value="1" />
					<lable >已批准</lable>
					<input type="radio" name="status" value="2" />
					<lable >未批准</lable>
				</td>
			</tr>
			<tr>
				<td>备注</td>
				<td><input type="text" name="notes" id="i1"/></td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="增加"id="i2"/><input  type="reset" value="重置" id="i3"/>
				</th>
			</tr>
		</table>
	</form>
</body>
</html>