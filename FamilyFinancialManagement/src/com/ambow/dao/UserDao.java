package com.ambow.dao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import com.ambow.model.User;
import com.ambow.util.DButil;

public class UserDao {
	Scanner sc =  new Scanner(System.in);
	static String userfilename = "user.txt";
	DButil db = new DButil();
	
	public UserDao(){
		db.isCheckFile(userfilename);
	}
	
	
	public void writeUser(String str,boolean flag){
		db.init(str,userfilename,true);
	}
	
	
	public List<User> getAllUsers(){
		List<User> list = new ArrayList<User>();
		List<String> liststr = db.readTxt(userfilename);
		for(String item:liststr){
			if(!item.equals("")){
				String[] str = item.split(" ");
				User user = new User();
				user.setUid(Integer.parseInt(str[0]));
				user.setUname(str[1]);
				user.setUpwd(str[2]);
				user.setUflag(Integer.parseInt(str[3]));
				list.add(user);
			}
		}
		return list;
	}
	
	
	public void getUserById(){
		
	}
	
	
	public void regist(){
		System.out.println("请输入用户名：");
		String str =sc.next();
		System.out.println("请输入密码：");
		String str1 =sc.next();
		try {
			db.writeFile(str,str1,userfilename,true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	public boolean login(){
		boolean flag=false;
		System.out.println("请输入用户名登录");
		String str1=sc.next();
		System.out.println("请输入密码");
		String str2 =sc.next();
		List<String> liststr = db.readTxt(userfilename);
		if(liststr.size()==0){}
		for(String item:liststr){
			String arr[]=item.split(" ");
			if(str1.equals(arr[1])&&str2.equals(arr[2])){
				flag=true;
				break;
			}
		}
		
		return flag;
	}
	
	
	
	public boolean initFirstUser(){
		boolean flag=false;
		System.out.println("请输入姓名");
		String str1=sc.next();
		System.out.println("请输入密码");
		String str2 =sc.next();
		System.out.println("请确认密码");
		String str3 =sc.next();
		List<String> liststr = db.readTxt(userfilename);
		for(String item:liststr){
			String arr[]=item.split(" ");
			if(str1.equals(arr[1])&&str2.equals(arr[2])&&str3.equals(str2)){
				flag=true;
				break;
			}
		}
		
		return flag;
	}
	
	
	/*@Test
	public void testgetall() {
		// �ж��ļ��Ƿ����
		List<User> list = getAllUsers();
		for(User item:list){
			System.out.println(item.getUid());
		}
	}*/

	//根据id删除用户
	public void userDel(){
			System.out.println("请输入要删除的用户id");
			int inPutId=sc.nextInt();
			List<User> list=getAllUsers();
			
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getUid());
				if(list.get(i).getUid()==inPutId){
					list.remove(i);
					break;
				}
			}
			String str="";
			for(int i=0;i<list.size();i++){
				if((list.size()-1)==i){
					str+=list.get(i).getUid()+" "+list.get(i).getUname()+" "+list.get(i).getUpwd()+" "+list.get(i).getUflag();
				}else{
					str+=list.get(i).getUid()+" "+list.get(i).getUname()+" "+list.get(i).getUpwd()+" "+list.get(i).getUflag()+"\n";;
				}
			}
			db.init(str, userfilename, false);
		}
	
	
	
	//修改
	
	public void updateUserName(){
			System.out.println("请输入要修改的用户id");
			int inPutId=sc.nextInt();
			List<User> list=getAllUsers();
			String updateName=null;
			System.out.println("");
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getUid()==inPutId){
					if(!list.get(i).getUname().equals(updateName)){
						System.out.println("当前用户名为："+list.get(i).getUname());
						System.out.println("请修改：");
						updateName=sc.next();
						list.get(i).setUname(updateName);
						System.out.println("修改用户名成功！");
						break;
					
					}else{
						System.out.println("该用户名已存在，请重新输入：");
						updateName=sc.next();
						list.get(i).setUname(updateName);
					}
				}
			}
			String str="";
			for(int i=0;i<list.size();i++){
				if((list.size()-1)==i){
					str+=list.get(i).getUid()+" "+list.get(i).getUname()+" "+list.get(i).getUpwd()+" "+list.get(i).getUflag();
				}else{
					str+=list.get(i).getUid()+" "+list.get(i).getUname()+" "+list.get(i).getUpwd()+" "+list.get(i).getUflag()+"\n";;
				}
			}
			db.init(str, userfilename, false);
	}
	
	
	
	//修改密码
	public void updateUserPwd(){
		System.out.println("请输入要修改的用户id");
		int inPutId=sc.nextInt();
		List<User> list=getAllUsers();
		String updatePwd=null;
		System.out.println("");
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getUid()==inPutId){
					System.out.println("当前用户密码为："+list.get(i).getUpwd());
					System.out.println("请修改：");
					updatePwd=sc.next();
					list.get(i).setUpwd(updatePwd);
					System.out.println("修改密码成功！");
					break;
				}
			}
		String str="";
		for(int i=0;i<list.size();i++){
			if((list.size()-1)==i){
				str+=list.get(i).getUid()+" "+list.get(i).getUname()+" "+list.get(i).getUpwd()+" "+list.get(i).getUflag();
			}else{
				str+=list.get(i).getUid()+" "+list.get(i).getUname()+" "+list.get(i).getUpwd()+" "+list.get(i).getUflag()+"\n";;
			}
		}
		db.init(str, userfilename, false);
	}


}
	
