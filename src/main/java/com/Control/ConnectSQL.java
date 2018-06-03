package com.Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectSQL {

	/**
	 * @author wrf
	 * ���ݿ����Ӻ������������޸�url��username��password
	 */
	static Connection getConn() {    
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");  // classLoader,���ض�Ӧ����      
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
		String url = "jdbc:mysql://gz-cdb-70c1yxw0.sql.tencentcdb.com:62558/resume?useUnicode=true&characterEncoding=utf-8&useSSL=false";  // ���ݿ���Ϣ�����Ϊdatabase����
		String username = "root";  // ���ݿ��˻���һ��Ϊroot
		String password = "wangzihao123";  // ���ݿ�����		   
		try{
		    conn = DriverManager.getConnection(url, username, password);  // ��������
	    } catch (SQLException e) {
		    e.printStackTrace();
		}
		return conn;
	}
}
