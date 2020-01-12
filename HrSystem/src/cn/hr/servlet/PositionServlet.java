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
	 * �����û��б��Լ���ҳ
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
		int pageNum=1;//��ǰҳ��
		int pageSize=6;//ÿҳ��ʾ����
		int totalCount=1;//�ܼ�¼��
		int pageCount=1;//��ҳ��
		PositionDao podao=new PositionDao();
		totalCount=podao.selectCount();//��ȡ�ܼ�¼��
		
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
		List<Position> list=podao.selectPages(pageNum, pageSize);
		//��ҳʵ��
		Pages pages=new Pages(pageNum, pageSize, totalCount, pageCount, null,list,null);
		request.setAttribute("pages", pages);
		request.getRequestDispatcher("/polist.jsp").forward(request, response);
	}
	
	/**
	 * ������Ϣ����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addPosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PositionDao podao=new PositionDao();
			//�����û��ύ����Ϣ
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
						System.out.println("��Ч���û���Ϣ");
					}
			}else{
				response.sendRedirect("poadd.jsp");
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
	protected void toUpdatePosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String sid=request.getParameter("id");
			int id=Integer.parseInt(sid);
			PositionDao podao=new PositionDao();
			Position po=podao.selectUserById(id);
			System.out.println("����3"+po.getId());
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
				System.out.println("����4"+i);
				if(i>0)
				{
					response.sendRedirect("PositionServlet?method=findPositionList");
				}
		}else{
			out.print("<script type='text/javascript'>alert('�޸�ʧ��!!!');location.href='polist.jsp';</script>");
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
		    out.println("alert('��Ա��id�������������룡');");
		    out.println("window.location.href='polist.jsp'");
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
	protected void deletePosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();//���������Ϣ���ͻ���
		//�����û�����
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		//�������ݷ�������
		PositionDao dedao=new PositionDao();
		int i=dedao.delPosition(id);
		if(i>0)
		{
			out.print("<script type='text/javascript'>alert('ɾ���ɹ���');location.href='PositionSerlvet?method=findPositionList';</script>");
			response.sendRedirect("PositionServlet?method=findPositionList");
		}
		else
		{
			response.sendRedirect("PositionServlet?method=findPositionList");
			out.print("<script type='text/javascript'>alert('ɾ��ʧ�ܣ�');location.href='PositionSerlvet?method=findPositionList';</script>");
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
