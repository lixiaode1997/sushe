package com.ambow.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import com.ambow.model.BigClass;
import com.ambow.model.User;
import com.ambow.util.DButil;

public class BigClassDao {
	static String bigFileName = "big.txt";
	Scanner sc = new Scanner(System.in);
	DButil db = new DButil();
	
	public BigClassDao(){
		
		db.isCheckFile(bigFileName);
	}
	
	//初始化BigClass
	public void writeBigClass(String str,boolean flag){
		db.init(str, bigFileName,true);
	}
	

	public List<BigClass> getAllBigClass(){
		List<BigClass> list = new ArrayList<BigClass>();	
		List<String> liststr = db.readTxt(bigFileName);
		for(String item:liststr){	
			if(!item.equals("")){
				String[] str = item.split(" ");	
				BigClass bigClass = new BigClass();
				bigClass.setBid(Integer.parseInt(str[0]));
				bigClass.setBname(str[1]);
				bigClass.setBtype(Integer.parseInt(str[2]));
				list.add(bigClass);
			}
		}
		return list;
	}
	
	
	@Test
	public void testgetall() {
		// �ж��ļ��Ƿ����
		List<BigClass> list = getAllBigClass();
		for(BigClass item:list){
			System.out.println(item);
		}
	}

	
	

	
	public void bigClassDel() {
			System.out.println("请输入你要删除的 大类Id");
			int inPutId=sc.nextInt();
			List<BigClass> list=getAllBigClass();
			
			for (int i = 0; i < list.size(); i++) {
				//System.out.println(list.get(i).getBid());
				if(list.get(i).getBid()==inPutId){
					list.remove(i);
					break;
				}
			}
			String str="";
			for(int i=0;i<list.size();i++){
				if((list.size()-1)==i){
					str+=list.get(i).getBid()+" "+list.get(i).getBname()+" "+list.get(i).getBtype();
				}else{
					str+=list.get(i).getBid()+" "+list.get(i).getBname()+" "+list.get(i).getBtype()+"\n";
				}
			}
			db.init(str, bigFileName, false);
		}
	
	
	
	public void addbigClass(){
		System.out.println("请输入大类名称：");
		String str = sc.next();
		System.out.println("请选择类型   收入：0   支出：1");
		String str1=sc.next();
		try {
			db.writeBigClassFile(str,str1,bigFileName,true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
	public int updatebigClass(){
		List<BigClass> list=getAllBigClass();
		System.out.println("请输入你要修改的 大类Id");
		int inPutId=sc.nextInt();
		
		
		for (int i = 0; i < list.size(); i++) {
			//System.out.println(list.get(i).getBid());
			if(list.get(i).getBid()==inPutId){
				System.out.println("请输入新的大类名称：");
				String updateName=sc.next();
				System.out.println("请输入类型   0：收入   1：支出");
				int updateType=sc.nextInt();
				list.get(i).setBname(updateName);
				list.get(i).setBtype(updateType);
				break;
			}
		}
		String str="";
		for(int i=0;i<list.size();i++){
			if((list.size()-1)==i){
				str+=list.get(i).getBid()+" "+list.get(i).getBname()+" "+list.get(i).getBtype();
			}else{
				str+=list.get(i).getBid()+" "+list.get(i).getBname()+" "+list.get(i).getBtype()+"\n";
			}
		}
		db.init(str, bigFileName, false);
		return inPutId;
	}
	


	
}
