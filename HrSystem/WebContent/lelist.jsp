
<%@page import="cn.hr.pojo.Lea"%>
<%@page import="java.util.List"%>
<%@page import="cn.hr.pojo.Pages"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人主页</title>
<style type="text/css">
		table{
			width:100%;
			text-align:center;
		}
		.t{
			text-align:center;
		}
		.tag{
			text-align:center;
		}
		td{background-color:rgb(112,112,112);color:#D8D8D8;}
		th{background-color:rgb(100,100,100);color:#D8D8D8;}
		a{
			font-family:'Microsoft JhengHei';
			text-decoration: none;
			color:white;
		}
		a:hover{color:red;}
		#btn1{
			background-color:#404040;
			color:#A8A8A8;
			border:none;
			width:100px;
			height:30px;
		}
		#btn1:hover{
			background-color:#A8A8A8;
			color:#404040;
		}
</style>

<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	
		function delLea(id){
			var flag=window.confirm("确认删除？");
			if(flag){
				window.location.href="LeaServlet?method=deleteLea&&id="+id;
			}
			else{
				return false;
			}
		}
		
		function find(){
			var val = parseInt($("#id_find").val());
			if(isNaN(val))
			{
				alert("请输入员工id（数字）");
			}
			else
			{
				alert("查找部门的id："+val);
				window.location.href="LeaServlet?method=checkLea&&id="+val;
			}
			
		}
</script>
</head>
<body>
	<%
				Pages pages=(Pages)request.getAttribute("pages");
				if(pages==null){
					request.getRequestDispatcher("LeaServlet?method=findLeaPage").forward(request, response);
				}else{
					List<Lea> list=pages.getList2();
			%>
				<table class="tab">
					<tr >
						<th  height="50">id</th>
						<th  height="50">员工工号</th>
						<th  height="50">部门编号</th>
						<th  height="50">开始时间</th>
						<th  height="50">结束时间</th>
						<th  height="50">天数</th>
						<th  height="50">请假事由</th>
						<th  height="50">请假类型</th>
						<th  height="50">部门领导</th>
						<th  height="50">请假状态</th>
						<th  height="50">部门备注</th>
						<th  height="50">操作</th>
					</tr>
					
					<%
						for(Lea le:list){
					%>
					<tr class="t">
						<td height="30"><%=le.getId() %></td>
						<td height="30"><%=le.getEmployee_number() %></td>
						<td height="30"><%=le.getDepartment_number() %></td>
						<td height="30"><%=le.getStart_time() %></td>
						<td height="30"><%=le.getEnd_time()%></td>
						<td height="30"><%=le.getDays()%></td>
						<td height="30"><%=le.getReason()%></td>
						<td height="30"><%=le.getType()%></td>
						<td height="30"><%=le.getManager()%></td>
						<td height="30"><%=le.getStatus()%></td>
						<td height="30"><%=le.getNotes()%></td>
						<td >
									<a href="LeaServlet?method=checkLea&&id=<%=le.getId()%>">查看</a>
									 | 
									<a href="LeaServlet?method=toUpdateLea&&id=<%=le.getId()%>">修改</a>
									 | 
									<a href="javascript:delLea(<%=le.getId()%>)">删除</a>
						</td>
					</tr>
					<%		
						}//for循环结束
					%>
					<tr class="tag">	
						<td colspan="12" height="25">
							当前 <%=pages.getPageNum() %>/<%=pages.getPageCount() %> 
							&nbsp&nbsp&nbsp 总记录数:<%=pages.getTotalCount() %> 
						</td>
					</tr>
					<tr>
						<td colspan="12" height="50" id="y">
							<a href="LeaServlet?method=findLeaPage&pageNum=<%=pages.getPageNum()-1%>" id="y">上一页</a> &nbsp&nbsp&nbsp&nbsp
									(Id查找)
								<input type="text" id="id_find" />
								<button onclick="find()" id="btn1">搜索</button>&nbsp&nbsp&nbsp&nbsp
							<a href="LeaServlet?method=findLeaPage&pageNum=<%=pages.getPageNum()+1%>" id="u">下一页</a>
						</td>
						
						
				</table>
			<%		
				}// else结束
			%>
</body>
</html>