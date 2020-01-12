package cn.hr.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.hr.pojo.Department;
import cn.hr.tools.C3P0Conn;

public class DepartmentDao {
	private Connection conn=null;
	//�Ż����������ٴ���
	private QueryRunner qr=new QueryRunner();
	
	/**
	 * ��¼��ѯ
	 * @param num ����Ա����
	 * @param pwd ����Ա����
	 * @return
	 */
	public Department selectlogin(String num,String pwd)
	{
		Department de=null;
		conn=C3P0Conn.getConnction();
		String sql="select *from message where username=? and password=?";
		try {
			de=qr.query(conn, sql, new BeanHandler<Department>(Department.class),num,pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Conn.closeConnection(conn);
		}
		return de;
	}

	
	/**
	 * ��ѯ�����б���
	 * @return
	 */
	public List<Department> selectDepartment(){
		List<Department> list =null;
		conn=C3P0Conn.getConnction();
		String sql="select * from department";
		try {
			list=qr.query(conn, sql, new BeanListHandler<Department>(Department.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Conn.closeConnection(conn);
		}
		return list;
		
	}

	
	
	/**
	 * ��ҳ����
	 * @return
	 */
	public int selectCount(){
		int count=0;
		conn=C3P0Conn.getConnction();
		String sql="select count(*) cn from department";
		try {
			long num=qr.query(conn, sql, new ScalarHandler<Long>("cn"));
			count=(int)num;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Conn.closeConnection(conn);
		}
		return count;
	}
	public List<Department> selectPages(int pageNum,int pageSize){
		List<Department> list=null;
		conn=C3P0Conn.getConnction();
		String sql="select * from department order by id limit ?,?";
		try {
			list=qr.query(conn, sql, new BeanListHandler<Department>(Department.class),(pageNum-1)*pageSize,pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Conn.closeConnection(conn);
		}
		return list;
		
	}
	
	
	/**
	 * ���Ӳ�����Ϣ
	 * @param d
	 * @return
	 */
	public int insertDepartment(Department de)
	{
		int i=0;
		conn=C3P0Conn.getConnction();
		String sql="insert into department(department_number,name,manager,telephone,address,notes) values(?,?,?,?,?,?)";
	
			try {
				i=qr.update(conn,sql,de.getDepartment_number(),de.getName(),de.getManager(),de.getTelephone(),de.getAddress(),de.getNotes());
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				C3P0Conn.closeConnection(conn);
			}
			return i;
	
	}

	/**
	 * �޸Ĳ�����Ϣ
	 * @param de
	 * @return
	 */
	public int updateDepartment(Department de)
	{
		int i=0;
		conn=C3P0Conn.getConnction();
		String sql="update department set department_number=?,name=?,manager=?,telephone=?,address=?,notes=? where id=?";
		try {
			
			i=qr.update(conn, sql,de.getDepartment_number(),de.getName(),de.getManager(),de.getTelephone(),de.getAddress(),de.getNotes(),de.getId());
			System.out.println("�޸�"+i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Conn.closeConnection(conn);
		}
		return i;
		
	}
	public Department selectUserById(int id){
		Department de=null;
		conn=C3P0Conn.getConnction();
		String sql="select *from department where id=?";
		try {
			de=qr.query(conn, sql, new BeanHandler<Department>(Department.class),id);
			if(de==null)
			{
				System.out.println("NULL!");
			}
			else
			{
				System.out.println("YES!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			C3P0Conn.closeConnection(conn);
		}
		return de;
		
	}
	
	
	/**
	 * ɾ��������Ϣ
	 * @param id
	 * @return
	 */
	public int delDepartment(int id){
		int i=0;
		conn=C3P0Conn.getConnction();
		String sql="delete from  department where id=?";
		try {
			i=qr.update(conn, sql, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Conn.closeConnection(conn);
		}
		return i;
	}
	
	
}
