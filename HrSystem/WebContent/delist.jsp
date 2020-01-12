
<%@page import="cn.hr.pojo.Department"%>
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
	
		function delDepartment(id){
			var flag=window.confirm("确认删除此用户吗？");
			if(flag){
				window.location.href="DepartmentServlet?method=deleteDepartment&&id="+id;
			}
			else{
				return false;
			}
		}
		
		function find(){
			var val = parseInt($("#id_find").val());
			if(isNaN(val))
			{
				alert("请输入部门id（数字）");
			}
			else
			{
				alert("查找部门的id："+val);
				window.location.href="DepartmentServlet?method=checkDepartment&&id="+val;
			}
			
		}
</script>
</head>
<body>
	<%
				Pages pages=(Pages)request.getAttribute("pages");
				if(pages==null){
					request.getRequestDispatcher("DepartmentServlet?method=findDepartmentPage").forward(request, response);
				}else{
					List<Department> list=pages.getList();
			%>
				<table class="tab">
					<tr class="h" >
						<th  height="50">id</th>
						<th  height="50">部门编码</th>
						<th  height="50">部门名称</th>
						<th  height="50">部门领导</th>
						<th  height="50">电话</th>
						<th  height="50">地址</th>
						<th  height="50">备注</th>
						<th  height="50">操作</th>
					</tr>
					
					<%
						for(Department de:list){
					%>
					<tr class="t">
						<td><%=de.getId() %></td>
						<td><%=de.getDepartment_number() %></td>
						<td><%=de.getName() %></td>
						<td><%=de.getManager() %></td>
						<td><%=de.getTelephone()%></td>
						<td><%=de.getAddress()%></td>
						<td><%=de.getNotes()%></td>
						<td >
									<a href="DepartmentServlet?method=checkDepartment&&id=<%=de.getId()%>">查看</a>
									 | 
									<a href="DepartmentServlet?method=toUpdateDepartment&&id=<%=de.getId()%>">修改</a>
									 | 
									<a href="javascript:delDepartment(<%=de.getId()%>)">删除</a>
						</td>
					</tr>
					<%		
						}//for循环结束
					%>
					<tr class="tag">	
						<td colspan="8" height="25">
							当前 <%=pages.getPageNum() %>/<%=pages.getPageCount() %> 
							&nbsp&nbsp&nbsp 总记录数:<%=pages.getTotalCount() %> 
						</td>
					</tr>
					<tr>
						<td colspan="8" height="30">
								<a href="DepartmentServlet?method=findDepartmentPage&pageNum=<%=pages.getPageNum()-1%>">上一页</a> &nbsp
								Id查找:
								<input type="text" id="id_find" />
								<button onclick="find()">搜索</button>
							<a href="DepartmentServlet?method=findDepartmentPage&pageNum=<%=pages.getPageNum()+1%>">下一页</a>&nbsp
						</td>
				</table>
			<%		
				}// else结束
			%>
</body>
</html>