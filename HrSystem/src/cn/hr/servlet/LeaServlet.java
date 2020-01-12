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

import cn.hr.dao.LeaDao;
import cn.hr.dao.PositionDao;
import cn.hr.pojo.Lea;
import cn.hr.pojo.Pages;
import cn.hr.pojo.Position;

/**
 * Servlet implementation class LeaServlet
 */
@WebServlet("/LeaServlet")
public class LeaServlet extends HttpServlet {
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
	protected void findLeaList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			LeaDao ledao=new LeaDao();
			List<Lea> list=ledao.selectLea();
			request.setAttribute("lelist", list);
			request.getRequestDispatcher("/lelist.jsp").forward(request, response);
	}
	protected void findLeaPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNum=1;//当前页码
		int pageSize=6;//每页显示条数
		int totalCount=1;//总记录数
		int pageCount=1;//总页数
		LeaDao ledao=new LeaDao();
		totalCount=ledao.selectCount();//获取总记录数
		
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
		List<Lea> list=ledao.selectPages(pageNum, pageSize);
		//分页实体
		Pages pages=new Pages(pageNum, pageSize, totalCount, pageCount, null,null,list);
		request.setAttribute("pages", pages);
		request.getRequestDispatcher("/lelist.jsp").forward(request, response);
	}
	
	
	/**
	 * 部门信息增加
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addLea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			LeaDao ledao=new LeaDao();
			//接受用户提交的信息
			String employee_number=request.getParameter("employee_number");
			String department_number=request.getParameter("department_number");
			String start_time=request.getParameter("start_time");
			String end_time=request.getParameter("end_time");
			String days=request.getParameter("days");
			String reason=request.getParameter("reason");
			String type=request.getParameter("type");
			String manager=request.getParameter("manager");
			String status=request.getParameter("status");
			String notes=request.getParameter("notes");
			if(employee_number!=""&&days!=""&&reason!="")
			{
					int e_number=Integer.parseInt(employee_number); 
					int s_number=Integer.parseInt(department_number); 
					Lea le=new Lea(0,e_number,s_number,start_time,end_time,days,reason,type,manager,status,notes);
					int i=ledao.insertLea(le);
					if(i>0){
						response.sendRedirect("LeaServlet?method=findLeaList");
						System.out.println("有效的用户信息");
					}
			}else{
				response.sendRedirect("leadd.jsp");
				System.out.println("无效的用户信息");
			}
	}

	
	/**
	 * 查看员工请假信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void checkLea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		LeaDao ledao=new LeaDao();
		Lea le=ledao.selectLeaById(id);
		if(le!=null)
		{
			request.setAttribute("le", le);
			request.getRequestDispatcher("/leshow.jsp").forward(request, response);
		}
		else
		{
			System.out.println("no!error!");
			PrintWriter out = response.getWriter();
		    out.println("<script>alert('此部门id错误，请重新输入！');window.location.href='lelist.jsp';</script>");
		}
		
	}
	
/**
 * 修改部门信息
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 */
	protected void toUpdateLea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		LeaDao ledao=new LeaDao();
		Lea le=ledao.selectLeaById(id);
		System.out.println("测试3："+le.getId());
		request.setAttribute("le", le);
		request.getRequestDispatcher("/leupdate.jsp").forward(request, response);
		
}
	protected void doUpdateLea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out=response.getWriter();
	String sid=request.getParameter("id");
	int id=Integer.parseInt(sid);
	String employee_number=request.getParameter("employee_number");
	String department_number=request.getParameter("department_number");
	String start_time=request.getParameter("start_time");
	String end_time=request.getParameter("end_time");
	String days=request.getParameter("days");
	String reason=request.getParameter("reason");
	String type=request.getParameter("type");
	String manager=request.getParameter("manager");
	String status=request.getParameter("status");
	String notes=request.getParameter("notes");
	if(employee_number!=""&&department_number!=""&&start_time!=""&&end_time!="")
	{
			int e_number=Integer.parseInt(employee_number); 
			int s_number=Integer.parseInt(department_number); 
			Lea le=new Lea(id,e_number,s_number,start_time,end_time,days,reason,type,manager,status,notes);
			LeaDao ledao=new LeaDao();
			int i=ledao.updateLea(le);
			ledao.updateLea(le);
			System.out.println("测试4："+i);
			if(i>0)
			{
				response.sendRedirect("LeaServlet?method=findLeaList");
			}
	}else{
			out.print("<script type='text/javascript'>alert('修改失败!!!');location.href='lelist.jsp';</script>");
	}
	
}


/**
 * 删除
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 */
protected void deleteLea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out=response.getWriter();//可以输出信息到客户端
	//接受用户参数
	String sid=request.getParameter("id");
	int id=Integer.parseInt(sid);
	//调用数据访问类型
	LeaDao ledao=new LeaDao();
	int i=ledao.delLea(id);
	if(i>0)
	{
		out.print("<script type='text/javascript'>alert('删除成功！');location.href='LeaSerlvet?method=findLeaList';</script>");
		response.sendRedirect("LeaServlet?method=findLeaList");
	}
	else
	{
		response.sendRedirect("LeaServlet?method=findLeaList");
		out.print("<script type='text/javascript'>alert('删除失败！');location.href='LeaSerlvet?method=findLeaList';</script>");
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
