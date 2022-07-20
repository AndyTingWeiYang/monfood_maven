package com.model.user.dao.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

import com.model.user.UserVO;
import com.model.user.dao.UserDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestUserJDBCDAOImpl {
	public static void main(String[] args) throws ParseException, IOException {
		Scanner sc = new Scanner(System.in);
		
//System.out.println(----------insert----------);
//		System.out.println("請輸入姓名");
//		String userName = sc.next();
//		
//		System.out.println("請輸入電子郵件(帳號)");
//		String userAccount = sc.next();
//		
//		System.out.println("請輸入密碼");
//		String userPassword = sc.next();
//		
//		System.out.println("請輸入手機號碼");
//		String userTel = sc.next();
//		
//		System.out.println("自我介紹");
//		String userProfile = sc.next();
//		
//		System.out.println("請輸入生日");
//		String birth = sc.next();
//		java.util.Date d = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
//		java.sql.Date birthday = new java.sql.Date(d.getTime());
//		
//		System.out.println("卡路里");
//		Integer calories = sc.nextInt();
//		
//		System.out.println("每日預算");
//		Integer budget = sc.nextInt();
//		
//		System.out.println("用檔案上傳大頭貼");
//		byte[] profilePic = getPicByteArray("C:/JavaFramework/eclipse-workspace/MonFood_Pic/userPic_02.jpg");
// 		
//		System.out.println("怪獸等級");
//		Integer monsLevel = sc.nextInt();
//		
//		System.out.println("怪獸名字");
//		String monsName = sc.next();
//		
//		System.out.println("更新時間系統加入");
//	
//		sc.close();
//		
//		//用註冊的vo包裝要新增的會員資料(getter/setter)
//		UserVO userVO = new UserVO(); 
//		userVO.setUserName(userName);
//		userVO.setUserAccount(userAccount);
//		userVO.setUserPassword(userPassword);
//		userVO.setUserTel(userTel);
//		userVO.setUserProfile(userProfile);
//		userVO.setBirthday(birthday);
//		userVO.setCalories(calories);
//		userVO.setBudget(budget);
//		userVO.setProfilePic(profilePic);
//		userVO.setMonsLevel(monsLevel);
//		userVO.setMonsName(monsName);
//		// sqlTimestamp 
//		long now = System.currentTimeMillis();
//	    Timestamp sqlTimestamp = new Timestamp(now);
//	    userVO.setUpdateTime(sqlTimestamp);
//	    
//	    UserDAO dao = new UserDAOImpl();//產生會員註冊的dao物件,準備對會員表註冊表格進行操作
//		dao.insert(userVO);
//		
//		System.out.println("insert successful");
		
//System.out.println(----------update----------);
//		System.out.println("請輸入要修改的會員編號(userID)");
//		Integer userId = sc.nextInt();
//		
//		System.out.println("請輸入姓名");
//		String userName = sc.next();
//		
//		System.out.println("請輸入電子郵件(帳號)");
//		String userAccount = sc.next();
//		
//		System.out.println("請輸入密碼");
//		String userPassword = sc.next();
//		
//		System.out.println("請輸入手機號碼");
//		String userTel = sc.next();
//		
//		System.out.println("自我介紹");
//		String userProfile = sc.next();
//		
//		System.out.println("請輸入生日");
//		String birth = sc.next();
//		java.util.Date d = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
//		java.sql.Date birthday = new java.sql.Date(d.getTime());
//		
//		System.out.println("卡路里");
//		Integer calories = sc.nextInt();
//		
//		System.out.println("每日預算");
//		Integer budget = sc.nextInt();
//		
//		System.out.println("用檔案上傳大頭貼");
//		byte[] profilePic = getPicByteArray("C:/JavaFramework/eclipse-workspace/MonFood_Pic/userPic_04.jpg");
// 		
//		System.out.println("怪獸等級");
//		Integer monsLevel = sc.nextInt();
//		
//		System.out.println("怪獸名字");
//		String monsName = sc.next();
//		
//		System.out.println("更新時間系統加入");
//		Long now = System.currentTimeMillis();
//		Timestamp updateTime = new Timestamp(now);
//	
//		sc.close();
//		
//		UserVO userVO = new UserVO(userName,userAccount,userPassword,userTel,userProfile,birthday,calories,budget,profilePic,monsLevel,monsName,updateTime,userId);
//		UserDAO dao = new UserDAOImpl();//產生會員註冊的dao物件,準備對會員表註冊表格進行操作
//		dao.update(userVO);
//		System.out.println("update successful");
		
//System.out.println(----------delete----------);
//		System.out.println("請輸入要刪除的會員編號(userId)");
//		Integer userId = sc.nextInt();
//		
//		sc.close();
//		
//		UserDAO dao = new UserDAOImpl();//產生會員註冊的dao物件,準備對會員表註冊表格進行操作
//		dao.delete(userId);
//		System.out.println("delete successful");
		
//System.out.println(----------selectByUserId----------);	
		System.out.println("請輸入要查詢的會員編號(userId)");
		Integer userId = sc.nextInt();
		
		sc.close();
		
		UserDAO dao = new UserDAOImpl();//產生會員註冊的dao物件,準備對會員表註冊表格進行操作
		UserVO userVO = dao.selectByUserId(userId);
		
		// 取得DB資料
		System.out.println("userName = " + userVO.getUserName());
		System.out.println("userAccount = " + userVO.getUserAccount());
		System.out.println("userPassword = " + userVO.getUserPassword());
		System.out.println("userTel = " + userVO.getUserTel());
		System.out.println("userProfile = " + userVO.getUserProfile());
		System.out.println("birthday = " + userVO.getBirthday());
		System.out.println("calories = " + userVO.getCalories());
		System.out.println("budget = " + userVO.getBudget());
		System.out.println("profilePic = " + userVO.getProfilePic());
		System.out.println("monsLevel = " + userVO.getMonsLevel());
		System.out.println("monsName = " + userVO.getMonsName());
		System.out.println("updateTime = " + userVO.getUpdateTime());

		System.out.println("selectByUserId successful");
		
//System.out.println(----------getAll----------);			
//		System.out.println("為您查詢所有的會員資料");
//		
//		UserDAO dao = new UserDAOImpl();//產生會員註冊的dao物件,準備對會員表註冊表格進行操作
//		List<UserVO> listUserVO = dao.getAll();
//		int count = 0 ;
//		for(UserVO userVO : listUserVO) {
//			System.out.println(userVO.toString());
//			count++;
//		}
//		
//		System.out.println("查詢完畢，"+"共"+count++ +"筆資料");
		
//System.out.println(----------getAllUserId----------);		
//		System.out.println("為您查詢所有的會員編號");
//		
//		UserDAO dao = new UserDAOImpl();//產生會員註冊的dao物件,準備對會員表註冊表格進行操作
//		List<UserVO> listUserVO = dao.getAllUserId();
//		int count = 0 ;
//		for(UserVO userVO : listUserVO) {
//			System.out.println("USER_ID：" + userVO.getUserId());
//			count++;
//		}
//		
//		System.out.println("查詢完畢，"+"共"+count++ +"位會員");

		
	}
	
	

	
	
	
	
	
	
	
	
	//Pic IO method
	public static byte[] getPicByteArray(String path) throws IOException{
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

}
