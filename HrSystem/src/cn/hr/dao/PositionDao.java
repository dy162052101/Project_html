package cn.hr.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.hr.pojo.Position;
import cn.hr.tools.C3P0Conn;

public class PositionDao {
	private Connection conn=null;
	//优化操作，减少代码
	private QueryRunner qr=new QueryRunner();
	
	
	/**
	 * 查询部门列表集合
	 * @return
	 */
	public List<Position> selectPosition(){
		List<Position> list =null;
		conn=C3P0Conn.getConnction();
		String sql="select * from position";
		try {
			list=qr.query(conn, sql, new BeanListHandler<Position>(Position.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Conn.closeConnection(conn);
		}
		return list;
		
	}

	
	
	/**
	 * 分页操作
	 * @return
	 */
	public int selectCount(){
		int count=0;
		conn=C3P0Conn.getConnction();
		String sql="select count(*) cn from position";
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
	public List<Position> selectPages(int pageNum,int pageSize){
		List<Position> list=null;
		conn=C3P0Conn.getConnction();
		String sql="select * from position order by id limit ?,?";
		try {
			list=qr.query(conn, sql, new BeanListHandler<Position>(Position.class),(pageNum-1)*pageSize,pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Conn.closeConnection(conn);
		}
		return list;
		
	}
	
	
	/**
	 * 增加部门信息
	 * @param d
	 * @return
	 */
	public int insertPosition(Position po)
	{
		int i=0;
		conn=C3P0Conn.getConnction();
		String sql="insert into position (position_number,name,level,notes) values(?,?,?,?)";
	
			try {
				i=qr.update(conn,sql,po.getPosition_number(),po.getName(),po.getLevel(),po.getNotes());
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				C3P0Conn.closeConnection(conn);
			}
			return i;
	
	}

	/**
	 * 修改部门信息
	 * @param de
	 * @return
	 */
	public int updatePosition(Position po)
	{
		int i=0;
		conn=C3P0Conn.getConnction();
		String sql="update position set position_number=?,name=?,level=?,notes=? where id=?";
		try {
			
			i=qr.update(conn, sql,po.getPosition_number(),po.getName(),po.getLevel(),po.getNotes(),po.getId());
			System.out.println("测试1"+i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Conn.closeConnection(conn);
		}
		return i;
		
	}
	public Position selectUserById(int id){
		Position po=null;
		conn=C3P0Conn.getConnction();
		String sql="select *from position where id=?";
		try {
			po=qr.query(conn, sql, new BeanHandler<Position>(Position.class),id);
			System.out.println("测试2");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			C3P0Conn.closeConnection(conn);
		}
		return po;
		
	}
	
	
	/**
	 * 删除部门信息
	 * @param id
	 * @return
	 */
	public int delPosition(int id){
		int i=0;
		conn=C3P0Conn.getConnction();
		String sql="delete from  position where id=?";
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
