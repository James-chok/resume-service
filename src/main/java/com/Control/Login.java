package com.Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Control.ConnectSQL;

public class Login {

	
	/**
	 * @author wrf
	 * �ж��Ƿ���ڸ��û�����ĳЩ�����е���
	 */
	public static boolean isExistUser(int uid) {
		Connection conn = ConnectSQL.getConn();
		String sql = "select * from user";
		PreparedStatement pstmt;
		try {
		    pstmt = (PreparedStatement)conn.prepareStatement(sql);
		    ResultSet rs = pstmt.executeQuery(sql);
		    while (rs.next()) {
		    	if (uid == rs.getInt(1)) {  // �ҵ�ƥ���uid��¼
		    		return true;
		    	}
		    }
		} catch (SQLException e) {return false;}
		return false;
	}
	
	
	/**
	 * @author wrf
	 * �ж��Ƿ��½�ɹ���û�з���NULL���е��򷵻�uname��ps����Ҳ��֪������ɶ��
	 */
	public static String isokLogin(int uid, String password) {
		Connection conn = ConnectSQL.getConn();
		String sql = "select * from user";
		PreparedStatement pstmt;
		try {
		    pstmt = (PreparedStatement)conn.prepareStatement(sql);
		    ResultSet rs = pstmt.executeQuery(sql);
		    while (rs.next()) {
		    	if (uid == rs.getInt(1) && password.equals(rs.getString(3))) {  // �ҵ�ƥ���uid��¼
		    		return rs.getString(2);
		    	}
		    }
		} catch (SQLException e) {return null;}
		return null;
	}
	
	
	
}
