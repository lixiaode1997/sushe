package com.ambow.service;

import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import com.ambow.dao.BigClassDao;
import com.ambow.model.BigClass;

	public class BigClassService {
		BigClassDao bcDao = new BigClassDao();
		Scanner sc = new Scanner(System.in);
		//��ʼ��
		
		public void bigClassInit(){
			String[] bigclassInit = {"10 职业薪资 0",
				    "11 业余工薪 0","12 偶然收入 0",
				    "13 杂项收入 0","14 衣服饰品 1",
				    "15 食品酒水 1","16 居家物业 1",
				    "17 行车交通 1","18 交通通讯 1",
				    "19 玩乐休闲 1","20 学习培训 1","21 医疗保健 1"};
			for(String item:bigclassInit){
					bcDao.writeBigClass(item,true);
				
			}		
		}
		
		
		
		//添加大类
		
		public void addBigClass(){
			bcDao.addbigClass();
			//showBigClass(bc);
			

		}
		
		
		
		//
		public void delBigClass(){
			getAllBigClass();
			bcDao.bigClassDel();
		}

		
		public void updateBigClass(){
			getAllBigClass();
			int id=bcDao.updatebigClass();
			List<BigClass> list =bcDao.getAllBigClass();
			System.out.println("修改大类成功！信息如下：");
			BigClass bc =list.get(id-10);
			showBigClass(bc);
			
		}
		
		
		//获取所有大类
		
		public void getAllBigClass(){
			System.out.println("+-----------------------------------------+");
			System.out.println("+--ID------大类名称----大类类型-------------+");
			List<BigClass> list = bcDao.getAllBigClass();
			String str = null;
			for(BigClass item:list){
				
				if(item.getBtype()==0){
					str="收入";
				}else{
					str="支出";
				}
				System.out.println("   "+item.getBid()+"      "+item.getBname()+"        "+str);
			}
			
		}
		
		//显示单个大类信息
		public void showBigClass(BigClass bc){
			System.out.println("+-----------------------------------------+");
			System.out.println("+--ID------大类名称----大类类型-------------+");
			String str=null;
			if(bc.getBtype()==0){
				str="收入";
			}else{
				str="支出";
			}
			System.out.println("   "+bc.getBid()+"      "+bc.getBname()+"        "+str);
			
		}
		
		
		
}
