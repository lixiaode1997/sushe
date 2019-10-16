package com.ambow.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import com.ambow.dao.BigClassDao;
import com.ambow.dao.SmallClassDao;
import com.ambow.dao.UserDao;
import com.ambow.model.BigClass;
import com.ambow.model.Recode;
import com.ambow.model.SmallClass;
import com.ambow.model.User;

public class DButil {
	
	
	private static String path="d:/family/";
	private static File file=new File(path);
	BigClass bc=new BigClass();
	public DButil(){
		if(!file.exists()){
			file.mkdir();
		}
	}
	public void writeFile(String str,String str1,String fileName1,boolean flag) throws IOException{
		
		File file = new File(path+fileName1);
		OutputStream os = new FileOutputStream(file, flag);
		PrintWriter pw = new PrintWriter(os);
		pw.write(UserIndexAdd(fileName1)+" ");
		pw.write(str+" ");
		pw.println(str1+" "+0);
		pw.close();
		os.close();
	}
	
	public void writeRecodeFile(String str,String fileName1,boolean flag) throws IOException{
		
		File file = new File(path+fileName1);
		OutputStream os = new FileOutputStream(file, flag);
		PrintWriter pw = new PrintWriter(os);
		//int rid=RecodeIndexAdd(fileName1);
		pw.println(RecodeIndexAdd(fileName1)+" "+str);
		//pw.write(str);
		pw.close();
		os.close();
	}
	
	
	private int RecodeIndexAdd(String fileName1) {
		
		Recode recode=new Recode();
		List<String> list=readTxt(fileName1);
		List<Recode> list1=new ArrayList<Recode>();
		for (String item : list){			
			String o[]=item.split(" ");
			recode.setRid(Integer.parseInt(o[0]));
			recode.setBigName(o[1]);
			recode.setSmallName(o[2]);
			recode.setDate(o[3]);
			recode.setMoney(o[4]);
			recode.setUserName(o[5]);
			recode.setUserRecocdName(o[6]);
			recode.setRecocd(o[7]);
			list1.add(recode);
		}
		
		if(list.size()==0){
			return 1;
		}else{
		Recode rc=list1.get(list1.size()-1);	
		return rc.getRid()+1;}
	}
	//初始化
	public void init(String str,String fileName1,boolean flag){
		
		try {
			File file = new File(path+fileName1);
			OutputStream os = new FileOutputStream(file, flag);
			PrintWriter pw = new PrintWriter(os);
			pw.println(str);
			pw.close();
			os.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	
	//读
	public List<String>  readTxt(String fileName){
		List<String> list = new ArrayList<String>();
		try {
			FileReader fr = new FileReader(new File(path+fileName));
			BufferedReader br=new BufferedReader(fr);
			String str = null;
			while((str = br.readLine())!=null){
				list.add(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
   //user下标自加
	public int UserIndexAdd(String filename) throws IOException{
		
		User user=new User();
		List<String> list=readTxt(filename);
		List<User> list1=new ArrayList<User>();
		for (String item : list) {
			
			String o[]=item.split(" ");
			user.setUid(Integer.parseInt(o[0]));
			user.setUname(o[1]);
			user.setUpwd(o[2]);
			list1.add(user);
		}
		User uu=list1.get(list1.size()-1);
		return uu.getUid()+1;
		
	}
	
	public int BigClassrIndexAdd(String filename) throws IOException{
		
		BigClass bigClass=new BigClass();
		List<String> list=readTxt(filename);
		List<BigClass> list1=new ArrayList<BigClass>();
		for (String item : list){			
			String o[]=item.split(" ");
			bigClass.setBid(Integer.parseInt(o[0]));
			bigClass.setBname(o[1]);
			bigClass.setBtype(Integer.parseInt(o[2]));
			list1.add(bigClass);
		}
		BigClass bc=list1.get(list1.size()-1);
		return bc.getBid()+1;
		
	}
	
	public int SmallClassrIndexAdd(String filename) throws IOException{
		
		SmallClass smallClass=new SmallClass();
		List<String> list=readTxt(filename);
		List<SmallClass> list1=new ArrayList<SmallClass>();
		for (String item : list){			
			String o[]=item.split(" ");
			smallClass.setSid(Integer.parseInt(o[0]));
			smallClass.setSname(o[1]);
			smallClass.setBigid(Integer.parseInt(o[2]));
			list1.add(smallClass);
		}
		SmallClass sc=list1.get(list1.size()-1);
		return sc.getSid()+1;
		
	}
	
	
	
	public void writeBigClassFile(String str,String str1,String fileName1,boolean flag) throws IOException{
		
		File file = new File(path+fileName1);
		OutputStream os = new FileOutputStream(file, flag);
		PrintWriter pw = new PrintWriter(os);
		pw.write(BigClassrIndexAdd(fileName1)+" ");
		pw.write(str+" ");
		pw.write(str1);
		pw.close();
		os.close();
	}

	//名称，大类id
	public void writeSmallClassFile(String str,int str1,String fileName1,boolean flag) throws IOException{
		
		File file = new File(path+fileName1);
		OutputStream os = new FileOutputStream(file, flag);
		PrintWriter pw = new PrintWriter(os);
		BigClass bc = getBigClassById(str1);
		pw.write(SmallClassrIndexAdd(fileName1)+" ");
		pw.write(str+" ");
		pw.write(bc.getBid()+" ");
		pw.write(bc.getBtype());
		pw.close();
		os.close();
	}


	public  BigClass getBigClassById(int a){
		
		BigClass bc = new BigClass();
		BigClassDao big=new BigClassDao();
		
		List<BigClass> list1 = null;
		list1 = big.getAllBigClass();
		for (int i = 0;i <list1.size();i++) {
			if(list1.get(i).getBid()==a){
				bc=list1.get(i);
				break;
			}
		}
		return bc;
		
	}
	
	public SmallClass getSmallClassById(int a){
		
		SmallClass sc = new SmallClass();
		SmallClassDao small=new SmallClassDao();
		
		List<SmallClass> list1 = null;
		list1 = small.getAllSmallClass();
		for (int i = 0;i <list1.size();i++) {
			if(list1.get(i).getSid()==a){
				sc=list1.get(i);
				break;
			}
		}
		return sc;
		
	}
	
	public void getSmallClassByBigId(int a){
		List<SmallClass> list =new SmallClassDao().getAllSmallClass();
		List<SmallClass> list1=new ArrayList<SmallClass>();
		for (int i = 0;i <list.size();i++) {
			
			if(list.get(i).getBigid()==a){
				SmallClass sc = new SmallClass();
				sc=list.get(i);
				list1.add(sc);
			}
		}
		
		for(SmallClass item:list1){
			System.out.println("     "+item.getSid()+"       "+item.getSname());
		}
		
		
	}
		
	
	public User getUserById(int a){
		
		User user = new User();
		UserDao userDao=new UserDao();
		
		List<User> list1 = null;
		list1 = userDao.getAllUsers();
		for (int i = 0;i <list1.size();i++) {
			if(list1.get(i).getUid()==a){
				user=list1.get(i);
				break;
			}
		}
		return user;
		
	}

	//判断文件是否存在
	public void isCheckFile(String filename){
		File file = new File(path+filename);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void showRecode(List<Recode> list){
		System.out.println("+----------------------------------------------------------------------------------------+");
		System.out.println("+   编号       收支类型      收支大类        收支小类      收支日期              金额             家庭成员           记录人员     收支摘要          +");
		System.out.println("+----------------------------------------------------------------------------------------+");
		for(Recode item:list){
			int type=getBigClassById(item.getRid()).getBtype();
			String str="收入";
			if(type==0)str="支出";
			System.out.println("      "+item.getRid()+"    "+str+"    "+item.getBigName()+"     "+item.getSmallName()+"     "+item.getDate()+"     "+item.getMoney()+"      "+item.getUserName()+"       "+item.getUserRecocdName()+"      "+item.getRecocd());
		}
		
	}
	
	
	
	
	/*// 查询日期
		public  List<String> getDays(String startTime, String endTime) {

			// 返回的日期集合
			List<String> days = new ArrayList<String>();

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date start = dateFormat.parse(startTime);
				Date end = dateFormat.parse(endTime);

				Calendar tempStart = Calendar.getInstance();
				tempStart.setTime(start);

				Calendar tempEnd = Calendar.getInstance();
				tempEnd.setTime(end);
				tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
				while (tempStart.before(tempEnd)) {
					days.add(dateFormat.format(tempStart.getTime()));
					tempStart.add(Calendar.DAY_OF_YEAR, 1);
				}

			} catch (ParseException e) {
				e.printStackTrace();
			}

			return days;
		}*/
	

	
	
}

