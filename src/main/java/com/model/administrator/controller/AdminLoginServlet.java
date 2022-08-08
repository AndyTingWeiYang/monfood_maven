package com.model.administrator.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.model.administrator.AdministratorVO;
import com.model.administrator.service.AdminService;
import com.model.administrator.service.AdminServiceImpl;
import com.model.del.DelService;
import com.model.del.DelVO;
import com.model.del.service.DelServiceImpl;
import com.model.user.UserVO;
import com.model.user.service.UserService;
import com.model.user.serviceImpl.UserServiceImpl;


@WebServlet("/AdminLogin")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
			Gson gson = new Gson();
			JsonObject respObj = new JsonObject();
			AdministratorVO administratorVO = gson.fromJson(request.getReader(), AdministratorVO.class);
			Integer adminID = administratorVO.getAdminID();
			String adminPassword = administratorVO.getAdminPassword();
			System.out.println(adminID);
			System.out.println(adminPassword);

	//驗證資料
//			if(adminID==null) {
//				respObj.addProperty("adminID", "請輸入管理員ID");
//			}
//			if(adminPassword==null || adminPassword.length()==0) {
//				respObj.addProperty("password", "請輸入密碼登入");
//			}
//			
//			if(respObj != null) {
//				response.getWriter().append(gson.toJson(respObj));
//				System.out.println("輸入的帳密有一個是空白所以失敗");
//				return;
//			}
			
	//呼叫model
			AdminService service = new AdminServiceImpl();
			AdministratorVO login = service.adminLogin(adminID, adminPassword);
			
			System.out.println(login);
	//根據model執行結果，導向view
	//這邊回傳的結果裡面有一個delVO裡面的所有屬性為null，所以在get屬性之前不知道為什麼兩邊都進去了......
			if(login.getAdminID()==null) {
				System.out.println("輸入的帳密找不到資料所以失敗");
				respObj.addProperty("loginStatus", "登入失敗請重新嘗試");
				HttpSession session = request.getSession();
				session.setAttribute("errors", respObj);
				response.getWriter().append(gson.toJson(respObj));
				
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("admin", login);
				response.getWriter().append(gson.toJson(login));
				System.out.println("登入成功");
			}
			
		
		
		
		
		
		
		
		
		
		
		
//		response.setCharacterEncoding("UTF-8");
//		Gson gson = new Gson();
//		JsonObject respObj = new JsonObject();
//		try {
//			DelVO delloginVO = gson.fromJson(request.getReader(), DelVO.class);
//
//			DelService service = new DelService();
//			final DelVO loginResult = service.userLogin(delloginVO);
//
//			String fukiAccount = fromUserKeyInVO.getUserAccount();
//			String fukiPassword = fromUserKeyInVO.getUserPassword();
//
//			if (loginResult == null) {
//				respObj.addProperty("None", "查無帳號");
//				response.getWriter().append(gson.toJson(respObj));
//				System.out.println("查無帳號");
//				return;
//			} else if (loginResult.getUserAccount().equals(fukiAccount)
//					&& loginResult.getUserPassword().equals(fukiPassword)) {
//				respObj.addProperty("LoginSuceesfully", "登入成功");
//				response.getWriter().append(gson.toJson(respObj));
//
//				// HttpSession介面
//				HttpSession session = request.getSession();
//				session.setAttribute("isuserLogin", "true"); // true代表使用者已登入過，延續登入狀態只看這Attr
//				session.setAttribute("userID", loginResult.getUserId());
//				session.setAttribute("userName", loginResult.getUserName());
//				session.setAttribute("userAccount", loginResult.getUserAccount());
//				session.setAttribute("monsLevel", loginResult.getMonsLevel());
//
//				// cookie
//				Cookie LoginCookie = new Cookie("SessionID", session.getId());// 新建Cookie
//				LoginCookie.setMaxAge(Integer.MAX_VALUE);// 設定生命週期為MAX_VALUE
//				response.addCookie(LoginCookie); // 輸出到客戶端
//
//				// 已經將會員資料交給session了。如果要延續登入狀態只需要在下一頁確認cookie裡面的session有沒有一個key是isuserLogin,而值是true即可
//
//				System.out.println("登入成功!!");
//
//			} else if (loginResult.getUserAccount().equals(fukiAccount)
//					&& !(loginResult.getUserPassword().equals(fukiPassword))) {
//				respObj.addProperty("ErrorPassword", "密碼有誤，請重新輸入");
//				response.getWriter().append(gson.toJson(respObj));
//				System.out.println("密碼有誤，請重新輸入!!");
//				return;
//			}
//
//			System.out.println("資料庫 = " + loginResult.getUserAccount());
//			System.out.println("使用者輸入帳號 = " + fukiAccount);
//			System.out.println("=================================");
//			System.out.println("資料庫 = " + loginResult.getUserPassword());
//			System.out.println("使用者輸入密碼 = " + fukiPassword);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			respObj.addProperty("errMsg", "系統錯誤");
//
//	
		
	
	
	
	
	
	
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
