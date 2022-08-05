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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.model.del.DelService;
import com.model.del.DelVO;
import com.model.del.service.DelServiceImpl;
import com.model.user.UserVO;
import com.model.user.service.UserService;
import com.model.user.serviceImpl.UserServiceImpl;


@WebServlet("/DelRegiCheckServlet")
public class DelRegiCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	@Override
//	public void init() throws ServletException {
////		customerService = new CustomerService();
//		ServletContext application = this.getServletContext();
//		ApplicationContext context = (ApplicationContext) application.getAttribute(
//				WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
//		this.customerService = context.getBean("customerService", CustomerService.class);
//		this.messageSource = context.getBean("messageSource", MessageSource.class);
//	}
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		
	//接收資料
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			JsonObject respObj = new JsonObject();
			DelVO delRegiVO = gson.fromJson(request.getReader(), DelVO.class);
			System.out.println(delRegiVO+"gson接來自前端的資料");
//	//驗證資料
//			Map<String, String> errors = new HashMap<String, String>();
//			request.setAttribute("errors", errors);
//			if(delTel==null || delTel.length()==0) {
////				respObj.addProperty("delTel", "請輸入手機號碼登入");
//				errors.put("delTel", "請輸入手機號碼登入");
//			}
//			if(password==null || password.length()==0) {
////				respObj.addProperty("password", "請輸入密碼登入");
//				errors.put("password", "請輸入密碼登入");
//
//			}
//			
//			if(errors!=null && !errors.isEmpty()) {
//				request.getRequestDispatcher(
//						"/delLogin.html").forward(request, response);
//				response.getWriter().append(gson.toJson(errors));
//
//				System.out.println("輸入的帳密有一個是空白所以失敗");
//
//				return;
//			}
//			
//呼叫model
			com.model.del.service.DelService service = new DelServiceImpl();
//IsAcctAvailable
			if(delRegiVO.getDelAccount()!=null) {
				String result = service.acctAvailable(delRegiVO.getDelAccount());
				System.out.println(result);
				response.getWriter().append(gson.toJson(result));
			}
//IsTelAvailable
			if(delRegiVO.getDelTel()!=null) {
				String result = service.telAvailable(delRegiVO.getDelTel());
				System.out.println(result);
				response.getWriter().append(gson.toJson(result));
			}
	
	
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
