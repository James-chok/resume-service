package com.Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Server {

    /**
     * @author wrf �����û���Ϣ������uid���������롢�ǳ�
     */
    public static boolean modifyInfo(int uid, String uname, String password) {
	if (Login.isExistUser(uid) == false)
	    return false;
	Connection conn = ConnectSQL.getConn();
	String sql = "update user set uname=?, password=? where uid=?";
	PreparedStatement pstmt;
	try {
	    pstmt = (PreparedStatement) conn.prepareStatement(sql);
	    pstmt.setString(1, uname);
	    pstmt.setString(2, password);
	    pstmt.setInt(3, uid);
	    pstmt.executeUpdate();
	    pstmt.close();
	    conn.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	    return false;
	}
	return true;
    }

    /**
     * @author wrf �г������û���id
     */
    public static List<Integer> getAllUser() {
	List<Integer> user = new ArrayList<>();
	Connection conn = ConnectSQL.getConn();
	String sql = "select* from user";
	PreparedStatement pstmt;
	try {
	    pstmt = (PreparedStatement) conn.prepareStatement(sql);
	    ResultSet rs = pstmt.executeQuery(sql);
	    while (rs.next()) {
		user.add(rs.getInt(1));
	    }
	    return user;
	} catch (SQLException e) {
	}
	return null;
    }

    /**
     * @author James
     */
    public static String getUser(int user_uid) {
	List<String> user = new ArrayList<>();
	Connection conn = ConnectSQL.getConn();
	String sql = "select* from user where uid = " + String.valueOf(user_uid);
	PreparedStatement pstmt;
	try {
	    pstmt = (PreparedStatement) conn.prepareStatement(sql);
	    ResultSet rs = pstmt.executeQuery(sql);
	    while (rs.next()) {
		user.add(rs.getString(2) + " " + rs.getInt(4));
	    }
	    return user.get(0);
	} catch (SQLException e) {
	}
	return null;
    }

    /**
     * @author wrf �г�����File��id+�ļ���
     */
    public static List<String> getAllFile() {
	List<String> file = new ArrayList<>();
	Connection conn = ConnectSQL.getConn();
	String sql = "select* from file";
	PreparedStatement pstmt;
	try {
	    pstmt = (PreparedStatement) conn.prepareStatement(sql);
	    ResultSet rs = pstmt.executeQuery(sql);
	    while (rs.next()) {
		file.add(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(5));
	    }
	    return file;
	} catch (SQLException e) {
	}
	return null;
    }

    /**
     * @author James
     */
    public static List<String> getUserFile(int user_uid) {
	List<String> file = new ArrayList<>();
	Connection conn = ConnectSQL.getConn();
	String sql = "select* from file where user_uid = " + String.valueOf(user_uid);
	PreparedStatement pstmt;
	try {
	    pstmt = (PreparedStatement) conn.prepareStatement(sql);
	    ResultSet rs = pstmt.executeQuery(sql);
	    while (rs.next()) {
		file.add(rs.getInt(1) + " " + rs.getString(2));
	    }
	    return file;
	} catch (SQLException e) {
	}
	return null;
    }

    /**
     * @author James
     */
    public static int generateId() {
	String id = "";
	for (int i = 0; i < 5; i++) {
	    int temp = (int) (Math.random() * 10);
	    id += String.valueOf(temp);
	}
	return Integer.parseInt(id);
    }
}
