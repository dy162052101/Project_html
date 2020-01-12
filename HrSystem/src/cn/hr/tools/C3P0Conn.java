package cn.hr.tools;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Conn {
	//默认读取项目src路径下的c3p0.config.xml文件，获取数据源，相当于连接池
	private static ComboPooledDataSource ds=new ComboPooledDataSource();
	
	/**
	 * 获取连接
	 * @return
	 */
	public static Connection getConnction()
	{
			Connection conn=null;
			try {
				conn=ds.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return conn;
			
	}
	/**
	 * 关闭连接
	 * @param conn
	 */
	public static void closeConnection(Connection conn)
	{
		if(conn!=null)
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 测试连接
	 * @param args
	 */
	public static void main(String[] args){
			getConnction();
	}
	
	
}

