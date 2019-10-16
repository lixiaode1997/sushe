package com.ambow.model;

import java.util.Date;

public class Recode {
	private int rid;
	private String bigName;
	private String smallName;
	private String date;
	private String money;
	private String userName;
	private String userRecocdName;
	private String recocd;
	
	
	
	public Recode() {
		super();
	}
	public Recode(int rid,String bigName, String smallName, String date, String money, String userName, String userRecocdName,
			String recocd) {
		super();
		this.rid=rid;
		this.bigName = bigName;
		this.smallName = smallName;
		this.date = date;
		this.money = money;
		this.userName = userName;
		this.userRecocdName = userRecocdName;
		this.recocd = recocd;
	}
	
	
	
	public Recode(String bigName, String smallName, String date, String money, String userName, String userRecocdName,
			String recocd) {
		super();
		this.bigName = bigName;
		this.smallName = smallName;
		this.date = date;
		this.money = money;
		this.userName = userName;
		this.userRecocdName = userRecocdName;
		this.recocd = recocd;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getBigName() {
		return bigName;
	}
	public void setBigName(String bigName) {
		this.bigName = bigName;
	}
	public String getSmallName() {
		return smallName;
	}
	public void setSmallName(String smallName) {
		this.smallName = smallName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserRecocdName() {
		return userRecocdName;
	}
	public void setUserRecocdName(String userRecocdName) {
		this.userRecocdName = userRecocdName;
	}
	public String getRecocd() {
		return recocd;
	}
	public void setRecocd(String recocd) {
		this.recocd = recocd;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	
	
}
