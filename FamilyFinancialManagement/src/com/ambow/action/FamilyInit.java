package com.ambow.action;

import com.ambow.service.BigClassService;
import com.ambow.service.SmallClassService;
import com.ambow.service.UserService;

public class FamilyInit {
	BigClassService bigService=new BigClassService();
	UserService userService = new UserService();
	SmallClassService smallService = new SmallClassService();
	public void initBigClass(){
		bigService.bigClassInit();
		System.out.println("初始化大类已完成");
		
	}
	
	public void initUser(){
		userService.userInit();
	}
	
	
	public void initSmallClass(){
		smallService.smallClassInit();
		System.out.println("初始化小类已完成");
	}
}
