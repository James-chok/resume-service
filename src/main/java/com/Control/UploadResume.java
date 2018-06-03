package com.Control;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UploadResume {

    /**
     * @author wrf
     * �ļ���blob���ͱ��������ݿ⣬fidĬ�ϳ�ʼΪ1�ҵ���������Ҫ����ȥ(�����fidΪ1���ļ���ɾ�ˣ�fid���ǻ��������fcontent��¼�����ļ����ڵ�λ�ã���D:\\�����ĵ�.docx
     */
    public static boolean storeFile(int fid, String fname, File file, int user_uid) throws Exception {
	Connection con = ConnectSQL.getConn();
	FileInputStream fis = new FileInputStream(file);
	String sql = "insert into file (fid, fname, fcontent, fstar, user_uid) " + "values(?,?,?,0.0,?)"; // Ĭ�ϳ�ʼ����Ϊ0
	PreparedStatement pstmt;
	try {
	    pstmt = (PreparedStatement) con.prepareStatement(sql);
	    pstmt.setInt(1, fid);
	    pstmt.setString(2, fname);
	    pstmt.setBinaryStream(3, fis, (int) file.length());
	    pstmt.setInt(4, user_uid);
	    pstmt.executeUpdate();
	    pstmt.close();
	    return true;
	} catch (SQLException e) {
	    return false;
	} finally {
	    fis.close();
	    con.close();
	}
    }

}
