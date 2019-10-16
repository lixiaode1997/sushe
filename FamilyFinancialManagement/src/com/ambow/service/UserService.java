package com.ambow.service;

import java.io.IOException;
import java.util.List;

import com.ambow.dao.UserDao;
import com.ambow.model.User;
import com.ambow.util.DButil;
//����dao�㣬��Action
public class UserService {
	UserDao userDao = new UserDao();
	DButil db = new DButil();
	//��ȡ�����û���Ϣ
	public List<User> getAllUsers(){
		return userDao.getAllUsers();
	}
	
	
	public void userInit(){

		String userinit="1 admin 123 1";
		userDao.writeUser(userinit, true);
	}
	
	public void reg(){
		userDao.regist();
	}
	
	public void log(){
		userDao.login();
	}
	
	
	public void userDelete(){
		userDao.userDel();
	}
	
	public void updateUserName(){
		userDao.updateUserName();
	}
	
	
	public void updateUserPwd(){
		userDao.updateUserPwd();
	}
	public void getAllUser(){
		List<User> list=userDao.getAllUsers();
		String userflag=null;
		System.out.println("+----------------------------------------------+");
		System.out.println("+-----ID------用户名称------用户身份-------------+");
		for(User item:list){
			if(item.getUflag()==1)
				userflag="管理员";else userflag="家庭成员";
			
			System.out.println("       "+item.getUid()+"        "+item.getUname()+"             "+userflag);
		}
		
	}
}
