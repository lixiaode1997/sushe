package com.ambow.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import com.ambow.model.BigClass;
import com.ambow.model.SmallClass;
import com.ambow.service.BigClassService;
import com.ambow.util.DButil;

public class SmallClassDao {
	static String smallFileName="small.txt";
	BigClassService bcService = new BigClassService();
	Scanner sc = new Scanner(System.in);
	DButil db = new DButil();
	
	public SmallClassDao(){
		//判断文件是否存在
		db.isCheckFile(smallFileName);
	}
	
	public void writeSmallClass(String str,boolean flag){
		db.init(str, smallFileName, true);
	}
	
	//获取全部小类
	public List<SmallClass> getAllSmallClass(){
		List<SmallClass> list = new ArrayList<SmallClass>();
		
		List<String> liststr = db.readTxt(smallFileName);
		for(String item:liststr){
			
			if(!item.equals("")){
				String[] str = item.split(" ");	
				SmallClass smallClass = new SmallClass();
				smallClass.setSid(Integer.parseInt(str[0]));
				smallClass.setSname(str[1]);
				smallClass.setBigid(Integer.parseInt(str[2]));
				list.add(smallClass);
			}
		}
		return list;
	}
	
	
	//删除小类
	public void smallClassDel() throws IOException {
		System.out.println("请输入要删除的小类id");
		int inPutId=sc.nextInt();
		List<SmallClass> list=getAllSmallClass();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getSid()==inPutId){
				list.remove(i);
				break;
			}
		}
		String str="";
		for(int i=0;i<list.size();i++){
			if((list.size()-1)==i){
				str+=list.get(i).getSid()+" "+list.get(i).getSname()+" "+list.get(i).getBigid();
			}else{
				str+=list.get(i).getSid()+" "+list.get(i).getSname()+" "+list.get(i).getBigid()+"\n";
			}
		}
		db.init(str, smallFileName, false);
	}
	
	
	public void addSmallClass() {
		System.out.println("请输入小类名称：");
		String str = sc.next();
		System.out.println("请选择归属大类型 ");
		bcService.getAllBigClass();
		int str1=sc.nextInt();
		
		try {
			db.writeSmallClassFile(str,str1,smallFileName,true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int updateSmallClass() {
		System.out.println("请输入你要修改的 小类Id");
		int inPutId=sc.nextInt();
		List<SmallClass> list=getAllSmallClass();
		for (int i = 0; i < list.size(); i++) {
			//System.out.println(list.get(i).getBid());
			if(list.get(i).getSid()==inPutId){
				System.out.println("请输入新的小类名称：");
				String updateName=sc.next();
				bcService.getAllBigClass();
				System.out.println("请输入大类类型 ");
				int updateType=sc.nextInt();
				list.get(i).setSname(updateName);
				list.get(i).setBigid(updateType);
				break;
			}
		}
		String str="";
		for(int i=0;i<list.size();i++){
			if((list.size()-1)==i){
				str+=list.get(i).getSid()+" "+list.get(i).getSname()+" "+list.get(i).getBigid();
			}else{
				str+=list.get(i).getSid()+" "+list.get(i).getSname()+" "+list.get(i).getBigid()+"\n";
			}
		}
		db.init(str, smallFileName, false);
		return inPutId;
	}
}
