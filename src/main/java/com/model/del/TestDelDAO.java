package com.model.del;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class TestDelDAO {

	public static void main(String[] args) throws ParseException, IOException {
		Scanner sc = new Scanner(System.in);
//insert new del member
//		System.out.println("請輸入delID");
//		Integer delID = sc.nextInt();
//		System.out.println("請輸入delName");
//		String delName = sc.next();
//		System.out.println("請輸入delAccount");
//		String delAccount = sc.next();
//		System.out.println("請輸入delPassword");
//		String delPassword = sc.next();
//		System.out.println("請輸入delTel");
//		String delTel = sc.next();
//		System.out.println("請輸入delBirthday 格式:yyyy-MM-dd");
//		String delB = sc.next();
//		java.util.Date delBUtil = new SimpleDateFormat("yyyy-MM-dd").parse(delB);
//		java.sql.Date delBirthday = new java.sql.Date(delBUtil.getTime());
//		System.out.println("請輸入platenumber");
//		String platenumber = sc.next();
//		System.out.println("請輸入status");
//		Integer status = sc.nextInt();
//		Long now = System.currentTimeMillis();
//        Timestamp updateTime = new Timestamp(now);
//		byte[] delIDPhoto = getPictureByteArray("C:\\TGA102_Workspace\\imgs\\id1.png");
//		byte[] carLicense = getPictureByteArray("C:\\TGA102_Workspace\\imgs\\id1.png");
//		byte[] driverLicense = getPictureByteArray("C:\\TGA102_Workspace\\imgs\\id1.png");
//		byte[] criminalRecord = getPictureByteArray("C:\\TGA102_Workspace\\imgs\\id1.png");
//		byte[] insurance = getPictureByteArray("C:\\TGA102_Workspace\\imgs\\id1.png");
//		System.out.println("請輸入accoutNmae");
//		String delAccountName = sc.next();
//		System.out.println("請輸入delBankname");
//		String delBankname = sc.next();
//		System.out.println("請輸入delBankcode");
//		String delBankcode = sc.next();
//		System.out.println("請輸入delBankaccount");
//		String delBankaccount = sc.next();
//		
//		sc.close();
//		
//		DelVO delVO = new DelVO(delID, delName, delAccount, delPassword, delTel, delBirthday, platenumber, status, updateTime, delIDPhoto, carLicense, driverLicense, criminalRecord, insurance, delAccountName, delBankname, delBankcode, delBankaccount);
//		DelDAO daoInsert = new DelDAOImpl();
//		daoInsert.add(delVO);
//		
//		System.out.println("新增成功");
		
		
// update by id
//		System.out.println("請輸入欲修改之id");
//		Integer delID = sc.nextInt();
//		System.out.println("請輸入delName");
//		String delName = sc.next();
//		System.out.println("請輸入delAccount");
//		String delAccount = sc.next();
//		System.out.println("請輸入delPassword");
//		String delPassword = sc.next();
//		System.out.println("請輸入delTel");
//		String delTel = sc.next();
//		System.out.println("請輸入delBirthday 格式:yyyy-MM-dd");
//		String delB = sc.next();
//		java.util.Date delBUtil = new SimpleDateFormat("yyyy-MM-dd").parse(delB);
//		java.sql.Date delBirthday = new java.sql.Date(delBUtil.getTime());
//		System.out.println("請輸入platenumber");
//		String platenumber = sc.next();
//		System.out.println("請輸入status");
//		Integer status = sc.nextInt();
//		Long now = System.currentTimeMillis();
//        Timestamp updateTime = new Timestamp(now);
//		byte[] delIDPhoto = getPictureByteArray("C:\\TGA102_Workspace\\imgs\\id1.png");
//		byte[] carLicense = getPictureByteArray("C:\\TGA102_Workspace\\imgs\\id1.png");
//		byte[] driverLicense = getPictureByteArray("C:\\TGA102_Workspace\\imgs\\id1.png");
//		byte[] criminalRecord = getPictureByteArray("C:\\TGA102_Workspace\\imgs\\id1.png");
//		byte[] insurance = getPictureByteArray("C:\\TGA102_Workspace\\imgs\\id1.png");
//		System.out.println("請輸入accoutNmae");
//		String delAccountName = sc.next();
//		System.out.println("請輸入delBankname");
//		String delBankname = sc.next();
//		System.out.println("請輸入delBankcode");
//		String delBankcode = sc.next();
//		System.out.println("請輸入delBankaccount");
//		String delBankaccount = sc.next();
//		
//		sc.close();
//		
//		DelVO beanUpdate = new DelVO(delID, delName, delAccount, delPassword, delTel, delBirthday, platenumber, status, updateTime, delIDPhoto, carLicense, driverLicense, criminalRecord, insurance, delAccountName, delBankname, delBankcode, delBankaccount);
//		DelDAO daoUpdate = new DelDAOImpl();
//		daoUpdate.update(beanUpdate);
//		
//		System.out.println("修改成功");
		
		
		
// search by id		
//		System.out.println("請輸入欲查詢之id");
//		Integer searchID = sc.nextInt();
//		DelDAO dao2 = new DelDAOImpl();
//		DelVO bean = dao2.findByDelID(searchID);
//		System.out.println("DelID="+bean.getDelID());
//		System.out.println("DelName="+bean.getDelName());
//		System.out.println("DelAccount="+bean.getDelAccount());
//		System.out.println("DelPassword="+bean.getDelPassword());
//		System.out.println("DelTel="+bean.getDelTel());
//		System.out.println("DelBirthday="+bean.getDelBirthday());
//		System.out.println("Platenumber="+bean.getPlatenumber());
//		System.out.println("Status="+bean.getStatus());
//		System.out.println("UpdateTime="+bean.getUpdateTime());
//		System.out.println("DelIDPhoto="+bean.getDelIDPhoto());
//		System.out.println("CarLicense="+bean.getCarLicense());
//		System.out.println("DriverLicense="+bean.getDriverLicense());
//		System.out.println("CriminalRecord="+bean.getCriminalRecord());
//		System.out.println("DelAccountName="+bean.getDelAccountName());
//		System.out.println("DelBankname="+bean.getDelBankname());
//		System.out.println("DelBankcode="+bean.getDelBankcode());
//		System.out.println("DelBankaccount="+bean.getDelBankaccount());
		
		
		
// search All 
		DelDAO dao = new DelDAOImpl();
		List<DelVO> beans = dao.getAll();
		for(DelVO delVO : beans) {
			System.out.println(delVO.toString());
		}
		
// delete by id
//		DelDAO daoDelete = new DelDAOImpl();
//		System.out.println("請輸入想要刪除的id");
//		Integer delID = sc.nextInt();
//		daoDelete.delete(delID);
		
		
		

	}
	
	public static byte[] getPictureByteArray(String path) throws IOException{
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}
