
<%@page import="cn.hr.pojo.Position"%>
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
		}
		.t{
			text-align:center;
		}
		.tag{
			text-align:center;
		}
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
</style>

<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	
		function delPosition(id){
			var flag=window.confirm("确认删除此用户吗？");
			if(flag){
				window.location.href="PositionServlet?method=deletePosition&&id="+id;
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
				window.location.href="PositionServlet?method=checkPosition&&id="+val;
			}
			
		}
</script>
</head>
<body>
	<%
				Pages pages=(Pages)request.getAttribute("pages");
				if(pages==null){
					request.getRequestDispatcher("PositionServlet?method=findPositionPage").forward(request, response);
				}else{
					List<Position> list=pages.getList1();
			%>
				<table class="tab">
					<tr class="h" >
						<th  height="50">id</th>
						<th  height="50">职称编号</th>
						<th  height="50">职称名称</th>
						<th  height="50">职称级别</th>
						<th  height="50">备注</th>
						<th  height="50">操作</th>
					</tr>
					
					<%
						for(Position po:list){
					%>
					<tr class="t">
						<td><%=po.getId() %></td>
						<td><%=po.getPosition_number() %></td>
						<td><%=po.getName() %></td>
						<td><%=po.getLevel() %></td>
						<td><%=po.getNotes()%></td>
						<td >
									<a href="PositionServlet?method=checkPosition&&id=<%=po.getId()%>">查看</a>
									 | 
									<a href="PositionServlet?method=toUpdatePosition&&id=<%=po.getId()%>">修改</a>
									 | 
									<a href="javascript:delPosition(<%=po.getId()%>)">删除</a>
						</td>
					</tr>
					<%		
						}//for循环结束
					%>
					<tr class="tag">	
						<td colspan="6" height="25">
							当前 <%=pages.getPageNum() %>/<%=pages.getPageCount() %> 
							&nbsp&nbsp&nbsp 总记录数:<%=pages.getTotalCount() %> 
						</td>
					</tr>
					<tr>
						<td colspan="6" height="50" id="y">
							<a href="PositionServlet?method=findPositionPage&pageNum=<%=pages.getPageNum()-1%>">上一页</a> &nbsp
						Id查找:
								<input type="text" id="id_find" />
								<button onclick="find()">搜索</button>
							<a href="PositionServlet?method=findPositionPage&pageNum=<%=pages.getPageNum()+1%>">下一页</a>&nbsp
						</td>
				</table>
			<%		
				}// else结束
			%>
</body>
</html>