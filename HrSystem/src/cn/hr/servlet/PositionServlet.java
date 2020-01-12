package cn.hr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.hr.dao.DepartmentDao;
import cn.hr.dao.PositionDao;
import cn.hr.pojo.Department;
import cn.hr.pojo.Pages;
import cn.hr.pojo.Position;

/**
 * Servlet implementation class PositionServlet
 */
@WebServlet("/PositionServlet")
public class PositionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param=request.getParameter("method");//获取方法名
		try {
			//通过反射来获取目标方法的名称 目标方法参数的类型
			Method method=this.getClass().getDeclaredMethod(param, HttpServletRequest.class,HttpServletResponse.class);
			//动态调用目标方法
			method.invoke(this, request,response);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	/**
	 * 查找用户列表以及分页
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findPositionList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PositionDao podao=new PositionDao();
			List<Position> list=podao.selectPosition();
			request.setAttribute("polist", list);
			request.getRequestDispatcher("/polist.jsp").forward(request, response);
	}
	protected void findPositionPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNum=1;//当前页码
		int pageSize=6;//每页显示条数
		int totalCount=1;//总记录数
		int pageCount=1;//总页数
		PositionDao podao=new PositionDao();
		totalCount=podao.selectCount();//获取总记录数
		
		//页面用户提交的页码
		String num=request.getParameter("pageNum");
		if(num!=null){
			pageNum=Integer.parseInt(num);
		}
		//计算总页数
		if(totalCount%pageSize==0){
			pageCount=totalCount/pageSize;
		}
		if(totalCount%pageSize!=0){
			pageCount=totalCount/pageSize+1;
		}
		//当页码超出总页码时
		if(pageNum>pageCount){
			pageNum=pageCount;
		}
		//当页码小于1时
		if(pageNum<1){
			pageNum=1;
		}
		List<Position> list=podao.selectPages(pageNum, pageSize);
		//分页实体
		Pages pages=new Pages(pageNum, pageSize, totalCount, pageCount, null,list,null);
		request.setAttribute("pages", pages);
		request.getRequestDispatcher("/polist.jsp").forward(request, response);
	}
	
	/**
	 * 部门信息增加
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addPosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PositionDao podao=new PositionDao();
			//接受用户提交的信息
			String position_number=request.getParameter("position_number");
			String name=request.getParameter("name");
			String level=request.getParameter("level");
			String notes=request.getParameter("notes");
			
			if(position_number!=""&&name!=""&&level!="")
			{
					int p_number=Integer.parseInt(position_number); 
					Position po=new Position(0,p_number,name,level,notes);
					int i=podao.insertPosition(po);
					if(i>0){
						response.sendRedirect("PositionServlet?method=findPositionList");
						System.out.println("有效的用户信息");
					}
			}else{
				response.sendRedirect("poadd.jsp");
				System.out.println("无效的用户信息");
			}
	}

	
	/**
	 * 修改部门信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toUpdatePosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String sid=request.getParameter("id");
			int id=Integer.parseInt(sid);
			PositionDao podao=new PositionDao();
			Position po=podao.selectUserById(id);
			System.out.println("测试3"+po.getId());
			request.setAttribute("po", po);
			request.getRequestDispatcher("/poupdate.jsp").forward(request, response);
			
	}
	protected void doUpdatePosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		String position_number=request.getParameter("position_number");
		String name=request.getParameter("name");
		String level=request.getParameter("level");
		String notes=request.getParameter("notes");
		if(position_number!="")
		{
				int p_number=Integer.parseInt(position_number); 
				Position po=new Position(id,p_number,name,level,notes);
				PositionDao podao=new PositionDao();
				int i=podao.updatePosition(po);
				System.out.println("测试4"+i);
				if(i>0)
				{
					response.sendRedirect("PositionServlet?method=findPositionList");
				}
		}else{
			out.print("<script type='text/javascript'>alert('修改失败!!!');location.href='polist.jsp';</script>");
		}
		
	}

	
protected void checkPosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		PositionDao podao=new PositionDao();
		Position po=podao.selectUserById(id);
		if(po!=null)
		{
			request.setAttribute("po", po);
			request.getRequestDispatcher("/poshow.jsp").forward(request, response);
		}
		else
		{
			 PrintWriter out = response.getWriter();
			out.flush();
		    out.println("<script>");
		    out.println("alert('此员工id错误，请重新输入！');");
		    out.println("window.location.href='polist.jsp'");
		    out.println("</script>");
			
		}
		
	}


	/**
	 * 删除部门信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deletePosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();//可以输出信息到客户端
		//接受用户参数
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		//调用数据访问类型
		PositionDao dedao=new PositionDao();
		int i=dedao.delPosition(id);
		if(i>0)
		{
			out.print("<script type='text/javascript'>alert('删除成功！');location.href='PositionSerlvet?method=findPositionList';</script>");
			response.sendRedirect("PositionServlet?method=findPositionList");
		}
		else
		{
			response.sendRedirect("PositionServlet?method=findPositionList");
			out.print("<script type='text/javascript'>alert('删除失败！');location.href='PositionSerlvet?method=findPositionList';</script>");
		}
		
	}
	
	
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				doGet(request, response);
	}

}
