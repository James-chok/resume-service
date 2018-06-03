package com.Model;

public class AdminInfo {

	
	//�ļ�ģ���࣬�����ݿ�������Ӧ
	
	private int uid;
	private String uname;
	private String password;
	private boolean Manager; 
	
	public AdminInfo(int uid, String uname, String password, boolean Manager) {
		this.uid = uid;
		this.uname = uname;
		this.password = password;
		this.Manager = Manager;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isManager() {
		return Manager;
	}

	public void setManager(boolean Manager) {
		this.Manager = Manager;
	}
	
}
