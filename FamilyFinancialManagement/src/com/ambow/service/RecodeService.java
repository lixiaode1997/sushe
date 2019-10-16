package com.ambow.service;

import java.util.List;

import org.junit.Test;

import com.ambow.dao.RecodeDao;
import com.ambow.model.Recode;
import com.ambow.util.DButil;

public class RecodeService {
	RecodeDao recodeDao = new RecodeDao();
	DButil db= new DButil();
	public  void addRecode() {		
		//添加记录
		recodeDao.addRecode();
		
	}

	public void updateRecode() {
		//修改记录
		recodeDao.updateRecode();
	}

	public void getAllRecode() {
		//查询全部记录
		List<Recode> list = recodeDao.getAllRecode();
		db.showRecode(list);
	}
@Test
	public void getRecodeByDate() {
		// 按时间查询记录
	//	List<Recode> list = recodeDao.getRecodeByDate();
	//	db.showRecode(list);
		
	}
	public void getRecodeByBigClass() {
		// 按收支大类查询记录
		List<Recode> list = recodeDao.getRecodeByBigClass();
		db.showRecode(list);
	}

	public void getRecodeBySmallClass() {
		// 按收支小类查询记录
		List<Recode> list = recodeDao.getRecodeBySmallClass();
		db.showRecode(list);
	}

	public void getRecodeByUser() {
		//按用户查询记录
		List<Recode> list = recodeDao.getRecodeByUser();
		db.showRecode(list);
	}

	public void getRecodeByMoney() {
		//按金额查询记录
		List<Recode> list = recodeDao.getRecodeByMoney();
		db.showRecode(list);
	}
	public void getRecodeByType() {
		// 按收支类型查询记录
		List<Recode> list = recodeDao.getRecodeByType();
		db.showRecode(list);
	}

	public void delRecode() {
		// 删除记录
		recodeDao.delRecode();
		System.out.println("删除记录成功 ");
		
	}
	
	
	

}
