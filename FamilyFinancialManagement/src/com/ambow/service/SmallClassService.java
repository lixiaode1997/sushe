package com.ambow.service;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.ambow.dao.SmallClassDao;
import com.ambow.model.BigClass;
import com.ambow.model.SmallClass;
import com.ambow.util.DButil;

public class SmallClassService {
	DButil db = new DButil();
	SmallClassDao scDao=new SmallClassDao();
	public void smallClassInit(){
		String[] smallInit={ "100 工资 10 0", "101 奖金 10 0", "102 补助津贴 10 0",
			    "103 加班工资 10 0", "104 兼职工资 11 0", "105 业余项目 11 0", "106 稿费版税 11 0",
			    "107 其他兼职 11 0", "108 中奖奖金 12 0", "109 其他收入 12 0", "110 利息收入 13 0",
			    "111 租赁所得 13 0", "112 收回债款 13 0", "113 服装 14 1", "114 鞋帽 14 1",
			    "115 饰品 14 1", "116 其他饰品 14 1", "117 食品 15 1", "118 副食 15 1",
			    "119 烟酒茶 15 1", "120 其他餐食 15 1", "121 日常用品 16 1", "122 水电煤气 16 1",
			    "123 房租费 6 1", "124 取暖费 16 1", "125 物业管理 16 1", "126 居家其他 16 1",
			    "127 公共交通 17 1", "128 打车租车 17 1", "129 私家车费 17 1", "130 长途车费 17 1",
			    "131 电话费 18 1", "132 上网费 18 1", "133 外出就餐 19 1", "134 运动健身 19 1",
			    "135 歌舞娱乐 19 1", "136 花鸟鱼虫 19 1", "137 旅游度假 19 1", "138 培训费 20 1",
			    "139 会员费 20 1", "140 书报音杂 20 1", "141 住院费 21 1", "142 药品费 21 1",
			    "143 保健费 21 1" };
		for(String item:smallInit){
				scDao.writeSmallClass(item,true);
			
		}		
	}
	public void updateSmallClass() {
		getAllSmallClass();
		int id=scDao.updateSmallClass();
		List<SmallClass> list =scDao.getAllSmallClass();
	
		SmallClass sc =list.get(id-100);
		String str = db.getBigClassById(sc.getBigid()).getBname();
		System.out.println("修改小类成功！信息如下：");
		System.out.println("+-----------------------------------------+");
		System.out.println("+--ID------小类名称----所属大类-------------+");
		System.out.println("   "+sc.getSid()+"      "+sc.getSname()+"        "+str);
	}
//service
	public void getAllSmallClass() {
		System.out.println("+---------------------------------------------+");
		System.out.println("+----ID------小类名称----归属大类-----收支类型----+");
		List<SmallClass> list = scDao.getAllSmallClass();
		String str = null;
		int str1=0;
		String str2=null;
		for(SmallClass item:list){
			str = db.getBigClassById(item.getBigid()).getBname();
			str1=db.getBigClassById(item.getBigid()).getBtype();
			if(str1==0)str2="收入";else str2="支出";
			System.out.println("     "+item.getSid()+"       "+item.getSname()+"       "+str+"         "+str2);
		}
		
	}
	public void addSmallClass() {
		
		scDao.addSmallClass();
		
	}
	
	public void delSmallClass() {
		
		try {
			scDao.smallClassDel();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
