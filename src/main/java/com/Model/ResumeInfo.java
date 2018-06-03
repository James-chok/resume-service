package com.Model;

public class ResumeInfo {

	//����Ա�࣬���û������ƣ�Ϊ�˶�Ӧ��ͼ
	
	private int fid;
	private String fname;
	private byte[] fcontent;
	private float star;
	
	
	public ResumeInfo(int fid, String fname, byte[] fcontent, float star) {
		this.fid = fid;
		this.fname = fname;
		this.fcontent = fcontent;
		this.star = star;
	}


	public int getFid() {
		return fid;
	}


	public void setFid(int fid) {
		this.fid = fid;
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public byte[] getFcontent() {
		return fcontent;
	}


	public void setFcontent(byte[] fcontent) {
		this.fcontent = fcontent;
	}


	public float getStar() {
		return star;
	}


	public void setStar(float star) {
		this.star = star;
	} 
	
	
	
}
