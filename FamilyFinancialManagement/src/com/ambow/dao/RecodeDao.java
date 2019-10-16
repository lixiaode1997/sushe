package com.ambow.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import com.ambow.model.BigClass;
import com.ambow.model.Recode;
import com.ambow.model.SmallClass;
import com.ambow.model.User;
import com.ambow.service.BigClassService;
import com.ambow.service.RecodeService;
import com.ambow.service.SmallClassService;
import com.ambow.service.UserService;
import com.ambow.util.DButil;

public class RecodeDao {
	Scanner sc =  new Scanner(System.in);
	BigClassService bcService = new BigClassService();
	SmallClassService scService = new SmallClassService();
	BigClassDao bcDao = new BigClassDao();
	SmallClassDao scDao = new SmallClassDao();
	UserDao userDao = new UserDao();
//	RecodeService recodeService=new RecodeService();
	UserService userService = new UserService();
	static String recodefilename = "recode.txt";
	DButil db = new DButil();
	
	public RecodeDao(){
		db.isCheckFile(recodefilename);
	}
	
	public String getBigClassNameById(int a){
		String bigName=db.getBigClassById(a).getBname();
		return bigName;
	}
	
	public String getSmallClassNameById(int a){
		String smallName=db.getSmallClassById(a).getSname();
		return smallName;
	}
	
	public String getUserNameById(int a){
		String userName=db.getUserById(a).getUname();
		return userName;
	}
	
	
	public void addRecode(){
		
		List<Recode> list = new ArrayList<Recode>();
		
		System.out.println("请输入大类编号");
		bcService.getAllBigClass();
		int bigId=sc.nextInt();
		String bigName = getBigClassNameById(bigId);//获得该ID下BigClass的名称
		System.out.println("请输入小类编号");
		//scService.getAllSmallClass();
		db.getSmallClassByBigId(bigId);
		int smallId = sc.nextInt();
		String smallName = getSmallClassNameById(smallId);
		System.out.println("请输入收支日期，例如：2010-03-01");
		String date=sc.next();
		System.out.println("请输入金额：");
		String money = sc.next();
		System.out.println("请输入产生消费记录的用户名");
		userService.getAllUser();
		int userId = sc.nextInt();
		String userName = getUserNameById(userId);
		System.out.println("请输入记录消费记录的用户名");
		userService.getAllUser();
		int recodeUserId = sc.nextInt();
		String recodUserName = getUserNameById(recodeUserId);
		System.out.println("摘要");
		String imp=sc.next();
		
		String str=bigName+" "+smallName+" "+date+" "+money+" "+userName+" "+recodUserName+" "+imp;
		try {
			db.writeRecodeFile(str,recodefilename,true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Recode> getAllRecode(){
		List<Recode> list=new ArrayList<Recode>();
		List<String> liststr = db.readTxt(recodefilename);
		for (String item : liststr){			
			String o[]=item.split(" ");
			Recode recode = new Recode();
			recode.setRid(Integer.parseInt(o[0]));
			recode.setBigName(o[1]);
			recode.setSmallName(o[2]);
			recode.setDate(o[3]);
			recode.setMoney(o[4]);
			recode.setUserName(o[5]);
			recode.setUserRecocdName(o[6]);
			recode.setRecocd(o[7]);
			list.add(recode);
		}
		return list;
		
	}


	public void delRecode() {
	//	recodeService.getAllRecode();
		List<Recode> list=getAllRecode();
		db.showRecode(list);
		System.out.println("请输入要删除记录的ID：");
		int inputid = sc.nextInt();
		
		for (int i = 0; i < list.size(); i++) {
			//System.out.println(list.get(i).getBid());
			if(list.get(i).getRid()==inputid){
				list.remove(i);
				break;
			}
		}
		String str="";
		for(int i=0;i<list.size();i++){
			if((list.size()-1)==i){
				str+=list.get(i).getRid()+" "+list.get(i).getBigName()+" "+list.get(i).getSmallName()+" "+list.get(i).getDate()+" "+list.get(i).getMoney()+" "+list.get(i).getUserName()+" "+list.get(i).getUserRecocdName()+" "+list.get(i).getRecocd();
			}else{
				str+=list.get(i).getRid()+" "+list.get(i).getBigName()+" "+list.get(i).getSmallName()+" "+list.get(i).getDate()+" "+list.get(i).getMoney()+" "+list.get(i).getUserName()+" "+list.get(i).getUserRecocdName()+" "+list.get(i).getRecocd()+"\n";
			}
		}
		db.init(str, recodefilename, false);
	}

	public void updateRecode() {
		List<Recode> list=getAllRecode();
		db.showRecode(list);
		System.out.println("请输入要修改记录的ID：");
		int inputid = sc.nextInt();	
		
		for(int i = 0;i <list.size();i++){
			if(list.get(i).getRid()==inputid){
				System.out.println("请输入大类编号");
				bcService.getAllBigClass();
				int bigId=sc.nextInt();
				String bigName = getBigClassNameById(bigId);//获得该ID下BigClass的名称
				System.out.println("请输入小类编号");
				scService.getAllSmallClass();
				int smallId = sc.nextInt();
				String smallName = getSmallClassNameById(smallId);
				System.out.println("请输入收支日期，例如：2010-03-01");
				String date=sc.next();
				System.out.println("请输入金额：");
				String money = sc.next();
				System.out.println("请输入产生消费记录的用户名");
				userService.getAllUser();
				int userId = sc.nextInt();
				String userName = getUserNameById(userId);
				System.out.println("请输入记录消费记录的用户名");
				userService.getAllUser();
				int recodeUserId = sc.nextInt();
				String recodUserName = getUserNameById(recodeUserId);
				System.out.println("摘要");
				String imp=sc.next();
				
				list.get(i).setBigName(bigName);
				list.get(i).setSmallName(smallName);
				list.get(i).setDate(date);
				list.get(i).setMoney(money);
				list.get(i).setUserName(userName);
				list.get(i).setUserRecocdName(recodUserName);
				list.get(i).setRecocd(imp);
				
				break;
				
			}
		}
		String str="";
		for(int i=0;i<list.size();i++){
			if((list.size()-1)==i){
				str+=list.get(i).getRid()+" "+list.get(i).getBigName()+" "+list.get(i).getSmallName()+" "+list.get(i).getDate()+" "+list.get(i).getMoney()+" "+list.get(i).getUserName()+" "+list.get(i).getUserRecocdName()+" "+list.get(i).getRecocd();
			}else{
				str+=list.get(i).getRid()+" "+list.get(i).getBigName()+" "+list.get(i).getSmallName()+" "+list.get(i).getDate()+" "+list.get(i).getMoney()+" "+list.get(i).getUserName()+" "+list.get(i).getUserRecocdName()+" "+list.get(i).getRecocd()+"\n";
			}
		}
		db.init(str, recodefilename, false);
	}

	public List<Recode> getRecodeByBigClass() {
		List<Recode> list=getAllRecode();
		List<Recode> listAddAfter = new ArrayList<Recode>();
		List<BigClass> listbig=bcDao.getAllBigClass();
		System.out.println("请输入大类ID");
		int inputId=sc.nextInt();
		BigClass bc=db.getBigClassById(inputId);
		for (int i = 0; i < list.size(); i++) {
			if(bc.getBname().equals(list.get(i).getBigName())){
				
						listAddAfter.add(list.get(i));
				}
		}
		return listAddAfter;
	}

	public List<Recode> getRecodeBySmallClass() {
		List<Recode> list=getAllRecode();
		List<Recode> listAddAfter = new ArrayList<Recode>();
		List<SmallClass> listsmall=scDao.getAllSmallClass();
		System.out.println("请输入小类ID");
		int inputId=sc.nextInt();
		SmallClass sc=db.getSmallClassById(inputId);
		for (int i = 0; i < list.size(); i++) {
			if(sc.getSname().equals(list.get(i).getSmallName())){
				
						listAddAfter.add(list.get(i));
				}
		}
		return listAddAfter;
	}
	
	public List<Recode> getRecodeByUser() {
		List<Recode> list=getAllRecode();
		List<Recode> listAddAfter = new ArrayList<Recode>();
		List<User> listuser=userDao.getAllUsers();
		System.out.println("请输入用户ID");
		int inputId=sc.nextInt();
		User user=db.getUserById(inputId);
		for (int i = 0; i < list.size(); i++) {
			if(user.getUname().equals(list.get(i).getUserName())){
				
						listAddAfter.add(list.get(i));
				}
		}
		return listAddAfter;
	}
	
	public List<Recode> getRecodeByMoney() {
		List<Recode> list=getAllRecode();
		List<Recode> listAddAfter = new ArrayList<Recode>();
		System.out.println("请输入查询金额");
		String money = sc.next();
		for (int i = 0; i < list.size(); i++) {
			if(money.equals(list.get(i).getMoney())){
				
						listAddAfter.add(list.get(i));
				}
		}
		return listAddAfter;
	}

	public List<Recode> getRecodeByType() {
		List<Recode> list=getAllRecode();
		List<Recode> listAddAfter = new ArrayList<Recode>();
		System.out.println("请输入收支类型   0：支出  1：收入");
		int type=sc.nextInt();
		for (int i = 0; i < list.size(); i++) {
			int rtype=db.getBigClassById(list.get(i).getRid()).getBtype();
			if(type==rtype){
						listAddAfter.add(list.get(i));
				}
		}
		return listAddAfter;
	}
@Test
	public void getRecodeByDate() {
		List<Recode> list=getAllRecode();
		List<Recode> listAddAfter = new ArrayList<Recode>();
		List<String> date = new ArrayList<String>();
		List<Integer> intDate=new ArrayList<Integer>();
		/*System.out.println("请输入开始日期：");
		String startDate = sc.next();
		System.out.println("请输入结束日期：");
		String endDate = sc.next();*/
		String[] str1 =null;
		
		for(int i=0;i<list.size();i++)
		{
				date.add(list.get(i).getDate());//获取Recode的Date
			}
		for(int i=0;i<date.size();i++)
		{
			str1=date.get(i).split("-");
			String str ="";
			for(int j =0;j<str1.length;j++){
				
				str+=str1[j];
				System.out.println(str);
			}
			}
		
		for(int k = 0;k<str1.length;k+=3)
		{					
			//str+=str1[k]+str1[k+1]+str1[k+2];
			//System.out.println(k+"  "+str);		
			//str=str1[k]+str1[k+1]+str1[k+2];
			//System.out.println(k+"  "+str);				
			}				
		//intDate.add(Integer.parseInt(str));//强转为整形add到intDate中
		
		/*	for(Integer it:intDate){
				if(it>=Integer.parseInt(startDate)&&it<=Integer.parseInt(endDate)){
					listAddAfter.add(list.get(i));
				}
			}*/
		
		
		//return listAddAfter;
	}
	
	
	
	
	
}
