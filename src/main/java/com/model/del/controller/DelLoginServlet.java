package com.model.del.controller;

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
import com.model.del.DelService;
import com.model.del.DelVO;
import com.model.del.service.DelServiceImpl;
import com.model.user.UserVO;
import com.model.user.service.UserService;
import com.model.user.serviceImpl.UserServiceImpl;


@WebServlet("/DelLoginServlet")
public class DelLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
	//接收資料
			Gson gson = new Gson();
			JsonObject respObj = new JsonObject();
			DelVO delLoginVO = gson.fromJson(request.getReader(), DelVO.class);
			String delTel = delLoginVO.getDelTel();
			String password = delLoginVO.getDelPassword();
			
	//驗證資料
			if(delTel==null || delTel.length()==0) {
				respObj.addProperty("delTel", "請輸入手機號碼登入");
			}
			if(password==null || password.length()==0) {
				respObj.addProperty("password", "請輸入密碼登入");
			}
			if(respObj.size()!=0) {
				System.out.println("@servlet: 輸入的帳密有一個是空白所以失敗");
				return;
			}
			
	//呼叫model
			com.model.del.service.DelService service = new DelServiceImpl();
			DelVO loginResultDelVO = service.delLogin(delTel, password);
			
	//驗證資料庫資料
			if(loginResultDelVO.getDelID()==null) {
				respObj.addProperty("loginResult", "登入失敗請重新嘗試");
				response.getWriter().append(gson.toJson(respObj));
				
				System.out.println("查無此帳密或是密碼錯誤");
				HttpSession session = request.getSession();
				session.setAttribute("errors", respObj);
				
			} else {
				respObj.addProperty("loginResult", "登入成功");
				response.getWriter().append(gson.toJson(respObj));
				
				System.out.println("登入成功");
				HttpSession session = request.getSession();
				session.setAttribute("del", loginResultDelVO);
				
			}

	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
