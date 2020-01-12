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
import cn.hr.pojo.Pages;
import cn.hr.pojo.Department;

/**
 * Servlet implementation class DepartmentServlet
 */
@WebServlet("/DepartmentServlet")
public class DepartmentServlet extends HttpServlet {
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
	 * 登录处理
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num=request.getParameter("username");
		String pwd=request.getParameter("password");
		//调用数据访问
		DepartmentDao dedao=new DepartmentDao();
		Department de=dedao.selectlogin(num, pwd);
		HttpSession session=request.getSession();
		if(de==null){
			//重定向
			response.sendRedirect("index.jsp");
		}else{
			
			response.sendRedirect("index1.jsp");
		}
		
	}

	/**
	 * 查找用户列表以及分页
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findDepartmentList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			DepartmentDao dedao=new DepartmentDao();
			List<Department> list=dedao.selectDepartment();
			request.setAttribute("delist", list);
			request.getRequestDispatcher("/delist.jsp").forward(request, response);
	}
	protected void findDepartmentPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNum=1;//当前页码
		int pageSize=6;//每页显示条数
		int totalCount=1;//总记录数
		int pageCount=1;//总页数
		DepartmentDao dedao=new DepartmentDao();
		totalCount=dedao.selectCount();//获取总记录数
		
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
		List<Department> list=dedao.selectPages(pageNum, pageSize);
		//分页实体
		Pages pages=new Pages(pageNum, pageSize, totalCount, pageCount, list,null,null);
		request.setAttribute("pages", pages);
		request.getRequestDispatcher("/delist.jsp").forward(request, response);
	}
	
	/**
	 * 部门信息增加
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			DepartmentDao dedao=new DepartmentDao();
			//接受用户提交的信息
			String department_number=request.getParameter("department_number");
			String name=request.getParameter("name");
			String manager=request.getParameter("manager");
			String telephone=request.getParameter("telephone");
			String adress=request.getParameter("address");
			String notes=request.getParameter("notes");
			if(department_number!=""&&name!=""&&manager!=""&&telephone!=""&&adress!="")
			{
				int d_number=Integer.parseInt(department_number); 
				Department de=new Department(0,d_number,name,manager,telephone,adress,notes);
				int i=dedao.insertDepartment(de);
				if(i>0){
					response.sendRedirect("DepartmentServlet?method=findDepartmentList");
					System.out.println("有效的用户信息");
				}
			}else{
				response.sendRedirect("deadd.jsp");
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
	protected void toUpdateDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String sid=request.getParameter("id");
			int id=Integer.parseInt(sid);
			DepartmentDao dedao=new DepartmentDao();
			Department de=dedao.selectUserById(id);
			request.setAttribute("de", de);
			request.getRequestDispatcher("/deupdate.jsp").forward(request, response);
			
	}
	protected void doUpdateDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		String department_number=request.getParameter("department_number");
		String name=request.getParameter("name");
		String manager=request.getParameter("manager");
		String telephone=request.getParameter("telephone");
		String address=request.getParameter("address");
		String notes=request.getParameter("notes");
		if(department_number!="")
		{
				int d_number=Integer.parseInt(department_number); 
				Department de=new Department(id,d_number,name,manager,telephone,address,notes);
				DepartmentDao dedao=new DepartmentDao();
				int i=dedao.updateDepartment(de);
				if(i>0)
				{
					response.sendRedirect("DepartmentServlet?method=findDepartmentList");
				}
		}else{
			out.print("<script type='text/javascript'>alert('修改失败!!!');location.href='delist.jsp';</script>");
		}
		
	}

	
protected void checkDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		DepartmentDao dedao=new DepartmentDao();
		Department de=dedao.selectUserById(id);
		if(de!=null)
		{
			request.setAttribute("de", de);
			request.getRequestDispatcher("/deshow.jsp").forward(request, response);
		}
		else
		{
			 PrintWriter out = response.getWriter();
			out.flush();
		    out.println("<script>");
		    out.println("alert('此部门id错误，请重新输入！');");
		    out.println("window.location.href='delist.jsp'");
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
	protected void deleteDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();//可以输出信息到客户端
		//接受用户参数
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		//调用数据访问类型
		DepartmentDao dedao=new DepartmentDao();
		int i=dedao.delDepartment(id);
		if(i>0)
		{
			out.print("<script type='text/javascript'>alert('删除成功！');location.href='DepartmentSerlvet?method=findDepartmentList';</script>");
			response.sendRedirect("DepartmentServlet?method=findDepartmentList");
		}
		else
		{
			response.sendRedirect("DepartmentServlet?method=findDepartmentList");
			out.print("<script type='text/javascript'>alert('删除失败！');location.href='DepartmentSerlvet?method=findDepartmentList';</script>");
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
