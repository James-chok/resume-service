package com.Control;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DeleteResume {

	
	/**
	 * @author wrf
	 * �ж��Ƿ���ڸ��ļ�����ĳЩ�����е���
	 */
	public static boolean isExistFile(int fid) {
		Connection conn = ConnectSQL.getConn();
		String sql = "select * from file";
		PreparedStatement pstmt;
		try {
		    pstmt = (PreparedStatement)conn.prepareStatement(sql);
		    ResultSet rs = pstmt.executeQuery(sql);
		    while (rs.next()) {
		    	if (fid == rs.getInt(1)) {  // �ҵ�ƥ���uid��¼
		    		return true;
		    	}
		    }
		} catch (SQLException e) {return false;}
		return false;
	}
	
	
	/**
	 * @author wrf
	 * ɾ��ĳ������
	 */
	public static boolean deleteFile(int fid, String path) {
		if (DeleteResume.isExistFile(fid) == false) return false; // ���������
		Connection conn = ConnectSQL.getConn();
		String sql = "delete from file where fid=?";
		PreparedStatement pstmt;
		try {
		    pstmt = (PreparedStatement) conn.prepareStatement(sql);
		    pstmt.setInt(1, fid);
		    pstmt.executeUpdate();       
		    pstmt.close();
		    conn.close();
		    File deleteFile = new File(path);
		    if (deleteFile.exists() && deleteFile.isFile()) {
			deleteFile.delete();
		    }
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
}
