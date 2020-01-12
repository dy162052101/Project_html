package cn.hr.tools;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Conn {
	//Ĭ�϶�ȡ��Ŀsrc·���µ�c3p0.config.xml�ļ�����ȡ����Դ���൱�����ӳ�
	private static ComboPooledDataSource ds=new ComboPooledDataSource();
	
	/**
	 * ��ȡ����
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
	 * �ر�����
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
	 * ��������
	 * @param args
	 */
	public static void main(String[] args){
			getConnction();
	}
	
	
}

