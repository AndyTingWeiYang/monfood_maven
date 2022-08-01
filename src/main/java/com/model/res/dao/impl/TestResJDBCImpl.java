package com.model.res.dao.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.model.res.ResVO;
import com.model.res.dao.ResDAO;

public class TestResJDBCImpl {

	public static void main(String[] args) throws ParseException, IOException {
		Scanner sc = new Scanner(System.in);

//System.out.println(----------insert----------);	

//		System.out.println("餐廳類別");
//		Integer resCategory = sc.nextInt();
//
//		System.out.println("帳號");
//		String resAccount = sc.next();
//
//		System.out.println("更新時間系統加入");
//
//		System.out.println("商家名稱");
//		String resName = sc.next();
//
//		System.out.println("密碼");
//		String resPassword = sc.next();
//
//		System.out.println("電話");
//		String resTel = sc.next();
//
//		System.out.println("照片");
//		byte[] resPic = getPicByteArray("C:/JavaFramework/eclipse-workspace/MonFood_Pic/userPic_02.jpg");
//
//		System.out.println("負責人姓名");
//		String ownerName = sc.next();
//
//		System.out.println("負責人手機");
//		String ownerTel = sc.next();
//
//		System.out.println("營業地址");
//		String bzLocation = sc.next();
//
//		System.out.println("郵遞區號");
//		Integer zipCode = sc.nextInt();
//
//		System.out.println("營業開始時間");
//		String BOH = sc.next();
//		LocalTime O = LocalTime.parse(BOH);
//		Time bzOpenHours = Time.valueOf(O);
//		System.out.println(bzOpenHours);
//
//		System.out.println("營業結束時間");
//		String BCH = sc.next();
//		LocalTime C = LocalTime.parse(BCH);
//		Time bzCloseHours = Time.valueOf(C);
//
//		System.out.println("周間營業時間");
//		Integer bzWeekTime = sc.nextInt();
//
//		sc.close();
//
//		ResVO resVO = new ResVO();
////		resVO.setResId(resId);
//		resVO.setResCategory(resCategory);
//		resVO.setResAccount(resAccount);
//		long now = System.currentTimeMillis();
//		Timestamp sqlTimestamp = new Timestamp(now);
//		resVO.setUpdateTime(sqlTimestamp);
//		resVO.setResName(resName);
//		resVO.setResPassword(resPassword);
//		resVO.setResTel(resTel);
//		resVO.setResPic(resPic);
//		resVO.setOwnerName(ownerName);
//		resVO.setOwnerTel(ownerTel);
//		resVO.setBzLocation(bzLocation);
//		resVO.setZipCode(zipCode);
//		resVO.setBzOpenHours(bzOpenHours);
//		resVO.setBzCloseHours(bzCloseHours);
//		resVO.setBzWeekTime(bzWeekTime);
//
//		ResDAO dao = new ResDAOImpl();
//		dao.insert(resVO);
//
//		System.out.println("insert successful");

//System.out.println(----------update----------);	
//		System.out.println("請輸入要修改的商家編號(resID)");
//		Integer resId = sc.nextInt();
//
//		System.out.println("餐廳類別");
//		Integer resCategory = sc.nextInt();
//
//		System.out.println("帳號");
//		String resAccount = sc.next();
//
//		System.out.println("更新時間系統加入");
//		long now = System.currentTimeMillis();
//		Timestamp updateTime = new Timestamp(now);
//
//		System.out.println("商家名稱");
//		String resName = sc.next();
//
//		System.out.println("密碼");
//		String resPassword = sc.next();
//
//		System.out.println("電話");
//		String resTel = sc.next();
//
//		System.out.println("照片");
//		byte[] resPic = getPicByteArray("C:/JavaFramework/eclipse-workspace/MonFood_Pic/userPic_02.jpg");
//
//		System.out.println("負責人姓名");
//		String ownerName = sc.next();
//
//		System.out.println("負責人手機");
//		String ownerTel = sc.next();
//
//		System.out.println("營業地址");
//		String bzLocation = sc.next();
//
//		System.out.println("郵遞區號");
//		Integer zipCode = sc.nextInt();
//
//		System.out.println("營業開始時間");
//		String BOH = sc.next();
//		LocalTime O = LocalTime.parse(BOH);
//		Time bzOpenHours = Time.valueOf(O);
//		System.out.println(bzOpenHours);
//
//		System.out.println("營業結束時間");
//		String BCH = sc.next();
//		LocalTime C = LocalTime.parse(BCH);
//		Time bzCloseHours = Time.valueOf(C);
//
//		System.out.println("周間營業時間");
//		Integer bzWeekTime = sc.nextInt();
//
//		sc.close();
//
//		ResVO resVo = new ResVO(resId, resCategory, resAccount, updateTime, resName, resPassword, resTel, resPic,
//				ownerName, ownerTel, bzLocation, zipCode, bzOpenHours, bzCloseHours, bzWeekTime);
//		ResDAO dao = new ResDAOImpl();
//		dao.update(resVo);
//		System.out.println("update successful");

//System.out.println(----------delete----------);
//		System.out.println("請輸入要刪除的商家編號(resId)");
//		Integer resId = sc.nextInt();
//		
//		sc.close();
//		
//		ResDAO dao = new ResDAOImpl();//產生會員註冊的dao物件,準備對會員表註冊表格進行操作
//		dao.delete(resId);
//		System.out.println("delete successful");

//System.out.println(----------selectByUserId----------);	
//		System.out.println("請輸入要查詢的商家編號(resId)");
//		Integer resId = sc.nextInt();
//		
//		sc.close();
//		
//		ResDAO dao = new ResDAOImpl();//產生會員註冊的dao物件,準備對會員表註冊表格進行操作
//		ResVO resVO = dao.selectByResId(resId);
//		
//		// 取得DB資料
//		System.out.println("ResId = " + resVO.getResId());
//		System.out.println("ResCategory = " + resVO.getResCategory());
//		System.out.println("ResAccount = " + resVO.getResAccount());
//		System.out.println("UpdateTime = " + resVO.getUpdateTime());
//		System.out.println("ResName = " + resVO.getResName());
//		System.out.println("ResPassword = " + resVO.getResPassword());
//		System.out.println("ResTel = " + resVO.getResTel());
//		System.out.println("getResPic = " + resVO.getResPic());
//		System.out.println("OwnerName = " + resVO.getOwnerName());
//		System.out.println("OwnerTel = " + resVO.getOwnerTel());
//		System.out.println("BzLocation = " + resVO.getBzLocation());
//		System.out.println("ZipCode = " + resVO.getZipCode());
//		System.out.println("BzOpenHours = " + resVO.getBzOpenHours());
//		System.out.println("BzCloseHours = " + resVO.getBzCloseHours());
//		System.out.println("BzWeekTime = " + resVO.getBzWeekTime());
//		
//		System.out.println("selectByResId successful");

//System.out.println(----------getAll----------);			
//		System.out.println("為您查詢所有的商家資料");
//		
//		ResDAO dao = new ResDAOImpl();//產生會員註冊的dao物件,準備對會員表註冊表格進行操作
//		List<ResVO> listResVO = dao.getAll();
//		int count = 0 ;
//		for(ResVO resVO : listResVO) {
//			System.out.println(resVO.toString());
//			count++;
//		}
//		
//		System.out.println("查詢完畢，"+"共"+count++ +"筆資料");		
//		
//
/////System.out.println(----------selectByResName----------);			
//		System.out.println("請輸入查詢的商家名稱(resName)");
//		String resName = sc.next();
//		sc.close();
//		ResDAO dao = new ResDAOImpl();//產生會員註冊的dao物件,準備對會員表註冊表格進行操作
//		List<ResVO> listResVO = dao.selectByResName(resName);
//		ResVO resVO = new ResVO();
//		
//		// 取得DB資料
//		System.out.println("ResId = " + resVO.getResId());
//		System.out.println("ResCategory = " + resVO.getResCategory());
//		System.out.println("ResAccount = " + resVO.getResAccount());
//		System.out.println("UpdateTime = " + resVO.getUpdateTime());
//		System.out.println("ResName = " + resVO.getResName());
//		System.out.println("ResPassword = " + resVO.getResPassword());
//		System.out.println("ResTel = " + resVO.getResTel());
//		System.out.println("getResPic = " + resVO.getResPic());
//		System.out.println("OwnerName = " + resVO.getOwnerName());
//		System.out.println("OwnerTel = " + resVO.getOwnerTel());
//		System.out.println("BzLocation = " + resVO.getBzLocation());
//		System.out.println("ZipCode = " + resVO.getZipCode());
//		System.out.println("BzOpenHours = " + resVO.getBzOpenHours());
//		System.out.println("BzCloseHours = " + resVO.getBzCloseHours());
//		System.out.println("BzWeekTime = " + resVO.getBzWeekTime());
//		
//		System.out.println("selectByResName successful");	
	}
		
		
		
	
	// Pic IO method
	public static byte[] getPicByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

}
