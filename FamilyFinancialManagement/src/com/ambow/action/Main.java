package com.ambow.action;
import java.io.File;
import java.util.List;
import java.util.Scanner;

import com.ambow.dao.UserDao;
import com.ambow.model.User;
import com.ambow.service.BigClassService;
import com.ambow.service.RecodeService;
import com.ambow.service.SmallClassService;
import com.ambow.service.UserService;
import com.ambow.util.DButil;

public class Main {

	private static String path = "d:/family/";
	private static File file = new File(path);
	DButil db = new DButil();
	UserDao userDao = new UserDao();
	UserService userService = new UserService();
	BigClassService bigClassService = new BigClassService();
	SmallClassService smallClassService = new SmallClassService();
	RecodeService recodeService = new RecodeService();
	Scanner sc = new Scanner(System.in);
	FamilyInit init=new FamilyInit();
	public static void main(String[] args) {
		Main familyMain = new Main();
		familyMain.welcome();
	}

	public void welcome() {
		System.out.println("+----------------------------------+");
		System.out.println("|----欢迎使用家庭财务通管理软件 -----|");
		System.out.println("+----------------------------------+");
		
		UserService userService = new UserService();
		List<User> list = userService.getAllUsers();//获取所有
		if(list.size() == 0){
			FamilyInit init=new FamilyInit();
			init.initBigClass();
			init.initSmallClass();
			init.initUser();
			boolean flag = userDao.initFirstUser();
			if(flag){
				System.out.println("第一位用户初始化已完成,请重新登录系统");
				}
			else{
				System.out.println("请重新输入");
				userDao.initFirstUser();
			}
		}else{
			log();
		}
	}
	
	public void familyHome(){
		System.out.println("+----------------------------------+");
		System.out.println("|---------------主菜单 -------------|");
		System.out.println("+----------------------------------+");
		System.out.println("+-1.用户管理--------4.收支记录管理--+");
		System.out.println("+-2.收支大类管理----5.收支统计------+");
		System.out.println("+-3.收支小类管理----0.退出系统------+");
		System.out.println("请输入你的选择：");
		
		int choose = sc.nextInt();
		switch (choose) {
		case 1:
			userManagement();
			break;
		case 2:
			bigClassManagement();
			break;
		case 3:
			smallClassManagement();
			break;
		case 4:
			recodeManagement();
			break;
		case 5:
			userManagement();
			break;
		case 0:
			exitWelcome();
			break;
		default:
			System.out.println("输入有误");
			familyHome();
			break;
		}
	}

	private void recodeManagement() {
		System.out.println("+----------------------------------+");
		System.out.println("|------------收支记录管理 ----------|");
		System.out.println("+----------------------------------+");
		System.out.println("+-1.新增记录---------4.删除记录-----+");
		System.out.println("+-2.修改记录 -----------------------+");
		System.out.println("+-3.查询记录---------0.返回主菜单---+");
		System.out.println("请输入你的选择：");
		int choose = sc.nextInt();
		switch (choose) {
		case 1:
			//添加记录
			recodeService.addRecode();
			System.out.println("添加记录成功");
			recodeManagement();
		case 2:
			//修改记录
			recodeService.updateRecode();
			System.out.println("修改记录成功");
			recodeManagement();
		case 3:
			//遍历记录
			getRecodeManagement();
		case 4:
			//删除记录
			recodeService.delRecode();
			System.out.println("删除记录成功");
			getRecodeManagement();
		case 0:
			familyHome();
			break;
		default:
			System.out.println("输入有误");
			familyHome();
			break;
		}
		
	}

	private void getRecodeManagement() {
		System.out.println("+----------------------------------+");
		System.out.println("|------------收支记录管理 ----------|");
		System.out.println("+----------------------------------+");
		System.out.println("+-1.查询全部记录----------5.按用户查询----------------+");
		System.out.println("+-2.按时间查询记录 -------6.按金额查询----------------+");
		System.out.println("+-3.按收支大类查询-------7.按收支类型查询-------------+");
		System.out.println("+-4.按收支小类查询-------0.返回上级菜单---------------+");
		System.out.println("请输入你的选择：");
		int choose = sc.nextInt();
		switch (choose) {
		case 1:
			//查询全部记录
			recodeService.getAllRecode();
			getRecodeManagement();
		case 2:
			//按时间查询
			recodeService.getRecodeByDate();
			getRecodeManagement();
		case 3:
			//按收支大类查询
			recodeService.getRecodeByBigClass();
			getRecodeManagement();
		case 4:
			//按收支大类查询
			recodeService.getRecodeBySmallClass();
			getRecodeManagement();
		case 5:
			//按用户查询
			recodeService.getRecodeByUser();
			getRecodeManagement();
		case 6:
			//按金额查询
			recodeService.getRecodeByMoney();
			getRecodeManagement();
		case 7:
			//收支类型
			recodeService.getRecodeByType();
			getRecodeManagement();
		case 0:
			familyHome();
			break;
		default:
			System.out.println("输入有误");
			familyHome();
			break;
		}
	}

	private void smallClassManagement() {
		System.out.println("+----------------------------------+");
		System.out.println("|------------收支小类管理 ----------|");
		System.out.println("+----------------------------------+");
		System.out.println("+-1.新增小类---------4.删除小类-----+");
		System.out.println("+-2.修改小类 -----------------------+");
		System.out.println("+-3.查询小类---------0.返回主菜单---+");
		System.out.println("请输入你的选择：");
		
		int choose = sc.nextInt();
		switch (choose) {
		case 1:
			//添加小类
			smallClassService.addSmallClass();
			System.out.println("添加小类成功");
			smallClassManagement();
		case 2:
			//修改小类
			smallClassService.updateSmallClass();
			System.out.println("修改小类成功");
			smallClassManagement();
		case 3:
			//遍历小类
			smallClassService.getAllSmallClass();
			System.out.println("查询所有小类");
			smallClassManagement();
		case 4:
			//删除小类
			smallClassService.delSmallClass();
			System.out.println("删除小类成功");
			smallClassManagement();
		case 0:
			familyHome();
			break;
		default:
			System.out.println("输入有误");
			familyHome();
			break;
		}
	}


	private void bigClassManagement() {
		System.out.println("+----------------------------------+");
		System.out.println("|------------收支大类管理 ----------|");
		System.out.println("+----------------------------------+");
		System.out.println("+-1.新增大类---------4.删除大类-----+");
		System.out.println("+-2.修改大类 -----------------------+");
		System.out.println("+-3.查询大类---------0.返回主菜单---+");
		System.out.println("请输入你的选择：");
		
		
		int choose = sc.nextInt();
		switch (choose) {
		case 1:
			//添加大类
			bigClassService.addBigClass();
			bigClassManagement();
		case 2:
			//修改大类
			bigClassService.updateBigClass();
			bigClassManagement();
		case 3:
			//查询所有大类
			bigClassService.getAllBigClass();
			System.out.println("查询所有大类结果");
			bigClassManagement();
		case 4:
			//删除大类
			bigClassService.delBigClass();
			System.out.println("删除大类成功");
			bigClassManagement();
		case 0:
			familyHome();
			break;
		default:
			System.out.println("输入有误");
			familyHome();
			break;
		}
	}


	
	private void exitWelcome() {
		welcome();
	}

	private void userManagement() {
		System.out.println("+----------------------------------+");
		System.out.println("|--------------用户管理 ------------|");
		System.out.println("+----------------------------------+");
		System.out.println("+-1.新增用户--------4.删除用户------+");
		System.out.println("+-2.修改用户名------5.密码修改------+");
		System.out.println("+-3.查询所有用户----0.返回主菜单----+");
		System.out.println("请输入你的选择：");
		
		int choose = sc.nextInt();
		switch (choose) {
		case 1:
			userService.reg();
			userManagement();
		case 2:
			userService.updateUserName();
			userManagement();
		case 3:
			List<User> liststr = userDao.getAllUsers();
			userService.getAllUser();
			userManagement();
		case 4:
			userService.userDelete();
			System.out.println("删除用户成功");
			userManagement();
		case 5:
			userUpdatePwd();
			break;
		case 0:
			exitFamilyHome();
			break;
		default:
			System.out.println("输入有误");
			userManagement();
			break;
		}
	}

	private void userUpdatePwd() {
		userService.updateUserPwd();
		userManagement();
	}

	private void userUpdateName() {
		userService.updateUserName();
		userManagement();
	}

	private void reg() {
		userService.reg();
		System.out.println("注册成功");
	}

	private void log() {
		if(userDao.login()){
			System.out.println("登录成功");
			familyHome();
		}else{
			System.out.println("登录失败");
			welcome();
		}
	}

	private void exitFamilyHome(){
		familyHome();
	}
	
}
