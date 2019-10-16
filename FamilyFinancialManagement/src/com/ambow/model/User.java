package com.ambow.model;

public class User {
	private int uid;
	private String uname;
	private String upwd;
	private int uflag;
	
	public User() {
		super();
	}
	public User(int uid, String uname, String upwd, int uflag) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.upwd = upwd;
		this.uflag = uflag;
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
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public int getUflag() {
		return uflag;
	}
	public void setUflag(int uflag) {
		this.uflag = uflag;
	}
}
