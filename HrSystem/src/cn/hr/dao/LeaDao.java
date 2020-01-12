package cn.hr.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.hr.pojo.Lea;
import cn.hr.tools.C3P0Conn;

public class LeaDao {
	private Connection conn=null;
	//�Ż����������ٴ���
	private QueryRunner qr=new QueryRunner();
	
	
	/**
	 * ��ѯ�����б���
	 * @return
	 */
	public List<Lea> selectLea(){
		List<Lea> list =null;
		conn=C3P0Conn.getConnction();
		String sql="select * from lea";
		try {
			list=qr.query(conn, sql, new BeanListHandler<Lea>(Lea.class));
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
		String sql="select count(*) cn from lea";
		try {
			long num=qr.query(conn, sql, new ScalarHandler<Long>("cn"));
			count=(int)num;
			System.out.println("jieguo:"+count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Conn.closeConnection(conn);
		}
		return count;
	}
	public List<Lea> selectPages(int pageNum,int pageSize){
		List<Lea> list=null;
		conn=C3P0Conn.getConnction();
		String sql="select * from lea order by id limit ?,?";
		try {
			list=qr.query(conn, sql, new BeanListHandler<Lea>(Lea.class),(pageNum-1)*pageSize,pageSize);
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
	public int insertLea(Lea le)
	{
		int i=0;
		conn=C3P0Conn.getConnction();
		String sql="insert into lea (employee_number,department_number,start_time,end_time,days,reason,type,manager,status,notes) values(?,?,?,?,?,?,?,?,?,?)";
	
			try {
				i=qr.update(conn,sql,le.getEmployee_number(),le.getDepartment_number(),le.getStart_time(),le.getEnd_time(),le.getDays(),le.getReason(),le.getType(),le.getManager(),le.getStatus(),le.getNotes());
		
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
	public int updateLea(Lea le)
	{
		int i=0;
		conn=C3P0Conn.getConnction();
		String sql="update lea set employee_number=?,department_number=?,start_time=?,end_time=?,days=?,reason=?,type=?,manager=?,status=?,notes=?  where id=?";
		try {
			
			i=qr.update(conn, sql,le.getEmployee_number(),le.getDepartment_number(),le.getStart_time(),le.getEnd_time(),le.getDays(),le.getReason(),le.getType(),le.getManager(),le.getStatus(),le.getNotes(),le.getId());
			System.out.println("����1��"+i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Conn.closeConnection(conn);
		}
		return i;
		
	}
	public Lea selectLeaById(int id){
		Lea le=null;
		conn=C3P0Conn.getConnction();
		String sql="select *from lea where id=?";
		try {
			le=qr.query(conn, sql, new BeanHandler<Lea>(Lea.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			C3P0Conn.closeConnection(conn);
		}
		return le;
		
	}
	
	
	/**
	 * ɾ��������Ϣ
	 * @param id
	 * @return
	 */
	public int delLea(int id){
		int i=0;
		conn=C3P0Conn.getConnction();
		String sql="delete from  lea where id=?";
		try {
			i=qr.update(conn, sql, id);
			System.out.println("ɾ����"+id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Conn.closeConnection(conn);
		}
		return i;
	}

	
	
	public static void main(String[] args){
		LeaDao dao=new LeaDao();
		Lea le=new Lea(2,2,2,"2010-1-1","2010-1-3","3","we","1","man","1","nothing");
		//Users user=new Users(3,"wjsn","starrymoment","wjsn@c.com","woman");
		dao.delLea(2);
		//dao.selectLogin("Choa", "likeacat");
		//List<Users> list=dao.selectPages(1, 10);
		//System.out.println(list);
		//int i=dao.selectCount();
		//System.out.println();
	}
	
	
	
	
}

