package com.Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.Control.ConnectSQL;

public class Regist {



	/**
	 * @author wrf
	 * �����û����ܣ����������Ϣ�������Ƿ�ɹ�����(uid����дһ���Զ����ɵ������Ҳ�������û��Լ����룩
	 */
	public static boolean createUser(int uid, String uname, String password) {
		if (password.length() <= 0 || uname.length()<= 0) return false;  // ȷ���ǳƺ����붼��Ϊ��
		Connection conn = ConnectSQL.getConn();
		String sql = "insert into user (uid, uname, password, Manager) "
				+ "values(?,?,?,false)";  // Ĭ���䲻Ϊ������
	    PreparedStatement pstmt;
	    try {
 	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
 	        pstmt.setInt(1, uid);
 	        pstmt.setString(2, uname);
 	        pstmt.setString(3, password);
	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
		} catch (SQLException e) {
		    return false;
		}
		return true;
	}
	
}
