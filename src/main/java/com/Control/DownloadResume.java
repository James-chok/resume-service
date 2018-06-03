package com.Control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DownloadResume {

    /**
     * @author wrf
     * �ļ���blob���ͱ��������ݿ⣬pathΪҪ���浽�ı�����ַ����Ҫ���ļ�����ΪE://���ݿ�.docx��fidΪ�����ļ��ı��
     */
    public static boolean readFile(String path, int fid) throws Exception {
	Connection con = ConnectSQL.getConn(); // ���������ݿ������
	FileOutputStream outputFile = null;
	InputStream is = null;
	String sql = "select fcontent from file where fid =?"; // Ĭ�ϳ�ʼ����Ϊ0
	PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
	try {
	    pstmt.setInt(1, fid);
	    ResultSet rs = pstmt.executeQuery();
	    rs.next();
	    File file = new File(path);
	    if (!file.exists()) {
		file.createNewFile();
	    }
	    outputFile = new FileOutputStream(file);
	    Blob blob = rs.getBlob("fcontent"); // fcontentΪ���ݿ���ͼƬ�ֶ�����
	    is = blob.getBinaryStream();
	    byte[] buffer = new byte[is.available()];
	    int size = 0;
	    while ((size = is.read(buffer)) != -1) {
		outputFile.write(buffer, 0, size);
	    }
	    return true;
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    is.close();
	    outputFile.close();
	    pstmt.close();
	    con.close();
	}
	return false;
    }

}
