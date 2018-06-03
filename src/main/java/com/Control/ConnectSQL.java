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
		String url = "jdbc:mysql://"数据库地址":"数据库端口号"/resume?useUnicode=true&characterEncoding=utf-8&useSSL=false";  // ���ݿ���Ϣ�����Ϊdatabase����
		String username = "数据库账号";  // ���ݿ��˻���һ��Ϊroot
		String password = "数据库密码";  // ���ݿ�����		   
		try{
		    conn = DriverManager.getConnection(url, username, password);  // ��������
	    } catch (SQLException e) {
		    e.printStackTrace();
		}
		return conn;
	}
}
