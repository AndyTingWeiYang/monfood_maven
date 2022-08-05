package com.model.del.servlet;

import java.awt.color.ProfileDataException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.model.del.DelService;
import com.model.del.DelVO;
import com.model.del.service.DelServiceImpl;

/**
 * Servlet implementation class AdminDelGetAll
 */
@WebServlet("/AdminDelEdit")
public class AdminDelEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		JsonObject respObj = new JsonObject();
		DelVO delVO = new DelVO();
		
//JSONObject接前端傳來的Base64圖檔並轉成bytes		
		System.out.println("alive??");
		JsonObject json = gson.fromJson(req.getReader(), JsonObject.class);
		JsonElement pic64 = json.get("delIDPhoto");
		String picString = String.valueOf(pic64);
		String[] picarray = picString.split(",");
		String picfinal = picarray[1];
		picfinal = picfinal.replace("\"", "");
		byte[] picByte = Base64.getDecoder().decode(picfinal);
		
		JsonElement car64 = json.get("carLicense");
		String carString = String.valueOf(car64);
		String[] cararray = carString.split(",");
		String carfinal = cararray[1];
		carfinal = carfinal.replace("\"", "");
		byte[] carByte = Base64.getDecoder().decode(carfinal);
		
		JsonElement drive64 = json.get("driveLicense");
		String driveString = String.valueOf(drive64);
		String[] drivearray = driveString.split(",");
		String drivefinal = drivearray[1];
		drivefinal = drivefinal.replace("\"", "");
		byte[] driveByte = Base64.getDecoder().decode(drivefinal);
		
		JsonElement criminal64 = json.get("criminalRecord");
		String criminalString = String.valueOf(criminal64);
		String[] criminalarray = criminalString.split(",");
		String criminalfinal = criminalarray[1];
		criminalfinal = criminalfinal.replace("\"", "");
		byte[] criminalByte = Base64.getDecoder().decode(criminalfinal);
		
		JsonElement insurance64 = json.get("insurance");
		String insuranceString = String.valueOf(insurance64);
		String[] insurancearray = insuranceString.split(",");
		String insurancefinal = insurancearray[1];
		insurancefinal = insurancefinal.replace("\"", "");
		byte[] insuranceByte = Base64.getDecoder().decode(insurancefinal);
		
//把JSONObject裡的東西一一取出轉成VO相對的格式塞入		
		JsonElement idj = json.get("delID");
		String idString = String.valueOf(idj);
		idString = idString.replace("\"", "");
		Integer delIDInteger = Integer.valueOf(idString);
		
		String nameString = String.valueOf(json.get("delName")).replace("\"", "");
		String accountString = String.valueOf(json.get("delAccount")).replace("\"", "");
		String delPasswordString = String.valueOf(json.get("delPassword")).replace("\"", "");
		String delTelString = String.valueOf(json.get("delTel")).replace("\"", "");
		String delBirthdayString = String.valueOf(json.get("delBirthday")).replace("\"", "");
		java.sql.Date delBitthday = Date.valueOf(delBirthdayString);
		String platenumberString = String.valueOf(json.get("platenumber")).replace("\"", "");
		String statusString = String.valueOf(json.get("status")).replace("\"", "");
		Integer statusInteger = Integer.valueOf(statusString);
		String delAccountNameString = String.valueOf(json.get("delAccountName")).replace("\"", "");
		String delBanknameString = String.valueOf(json.get("delBankname")).replace("\"", "");
		String delBankcodeString = String.valueOf(json.get("delBankcode")).replace("\"", "");
		String delBankaccountsString = String.valueOf(json.get("delBankaccount")).replace("\"", "");
		
		delVO.setDelID(delIDInteger);
		delVO.setDelName(nameString);
		delVO.setDelAccount(accountString);
		delVO.setDelPassword(delPasswordString);
		delVO.setDelTel(delTelString);
		delVO.setDelBirthday(delBitthday);
		delVO.setPlatenumber(platenumberString);
		delVO.setStatus(statusInteger);
		delVO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		delVO.setDelAccountName(delAccountNameString);
		delVO.setDelBankname(delBanknameString);
		delVO.setDelBankcode(delBankcodeString);
		delVO.setDelBankaccount(delBankaccountsString);
		delVO.setDelIDPhoto(picByte);
		delVO.setCarLicense(carByte);
		delVO.setDriverLicense(driveByte);
		delVO.setCriminalRecord(criminalByte);
		delVO.setInsurance(insuranceByte);
		
		System.out.println(delVO);
		
		com.model.del.service.DelService service = new DelServiceImpl();
		DelVO result = service.updatePic(delVO);
		
		//		resp.getWriter().append(gson.toJson(respObj));
//		System.out.println(result);
		

	}

}
