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
		String param=request.getParameter("method");//��ȡ������
		try {
			//ͨ����������ȡĿ�귽�������� Ŀ�귽������������
			Method method=this.getClass().getDeclaredMethod(param, HttpServletRequest.class,HttpServletResponse.class);
			//��̬����Ŀ�귽��
			method.invoke(this, request,response);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	/**
	 * ��¼����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num=request.getParameter("username");
		String pwd=request.getParameter("password");
		//�������ݷ���
		DepartmentDao dedao=new DepartmentDao();
		Department de=dedao.selectlogin(num, pwd);
		HttpSession session=request.getSession();
		if(de==null){
			//�ض���
			response.sendRedirect("index.jsp");
		}else{
			
			response.sendRedirect("index1.jsp");
		}
		
	}

	/**
	 * �����û��б��Լ���ҳ
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
		int pageNum=1;//��ǰҳ��
		int pageSize=6;//ÿҳ��ʾ����
		int totalCount=1;//�ܼ�¼��
		int pageCount=1;//��ҳ��
		DepartmentDao dedao=new DepartmentDao();
		totalCount=dedao.selectCount();//��ȡ�ܼ�¼��
		
		//ҳ���û��ύ��ҳ��
		String num=request.getParameter("pageNum");
		if(num!=null){
			pageNum=Integer.parseInt(num);
		}
		//������ҳ��
		if(totalCount%pageSize==0){
			pageCount=totalCount/pageSize;
		}
		if(totalCount%pageSize!=0){
			pageCount=totalCount/pageSize+1;
		}
		//��ҳ�볬����ҳ��ʱ
		if(pageNum>pageCount){
			pageNum=pageCount;
		}
		//��ҳ��С��1ʱ
		if(pageNum<1){
			pageNum=1;
		}
		List<Department> list=dedao.selectPages(pageNum, pageSize);
		//��ҳʵ��
		Pages pages=new Pages(pageNum, pageSize, totalCount, pageCount, list,null,null);
		request.setAttribute("pages", pages);
		request.getRequestDispatcher("/delist.jsp").forward(request, response);
	}
	
	/**
	 * ������Ϣ����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			DepartmentDao dedao=new DepartmentDao();
			//�����û��ύ����Ϣ
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
					System.out.println("��Ч���û���Ϣ");
				}
			}else{
				response.sendRedirect("deadd.jsp");
				System.out.println("��Ч���û���Ϣ");
			}
	}
	
	/**
	 * �޸Ĳ�����Ϣ
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
			out.print("<script type='text/javascript'>alert('�޸�ʧ��!!!');location.href='delist.jsp';</script>");
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
		    out.println("alert('�˲���id�������������룡');");
		    out.println("window.location.href='delist.jsp'");
		    out.println("</script>");
			
		}
		
	}

	
	/**
	 * ɾ��������Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();//���������Ϣ���ͻ���
		//�����û�����
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		//�������ݷ�������
		DepartmentDao dedao=new DepartmentDao();
		int i=dedao.delDepartment(id);
		if(i>0)
		{
			out.print("<script type='text/javascript'>alert('ɾ���ɹ���');location.href='DepartmentSerlvet?method=findDepartmentList';</script>");
			response.sendRedirect("DepartmentServlet?method=findDepartmentList");
		}
		else
		{
			response.sendRedirect("DepartmentServlet?method=findDepartmentList");
			out.print("<script type='text/javascript'>alert('ɾ��ʧ�ܣ�');location.href='DepartmentSerlvet?method=findDepartmentList';</script>");
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
