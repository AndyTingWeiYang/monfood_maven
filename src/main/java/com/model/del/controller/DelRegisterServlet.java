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


@WebServlet("/DelRegisterServlet")
public class DelRegisterServlet extends HttpServlet {
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
//			String delTel = delLoginVO.getDelTel();
//			String password = delLoginVO.getDelPassword();
//			String delTel = request.getParameter("delTel");
//			String password = request.getParameter("delPassword");
//
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
			String result = service.delRegister(delRegiVO);
			String[] answer = result.split(",");
			System.out.println(answer[0]);
			delRegiVO.setDelID(Integer.valueOf(answer[1]));
			System.out.println(answer[1]);
			System.out.println(delRegiVO.getDelID());

			if("新增成功".equals(answer[0]) ) {
				HttpSession session = request.getSession();
				session.setAttribute("del", delRegiVO);
				System.out.println(session.getAttribute("del")+"session裡的資料"); 
				response.getWriter().append(gson.toJson(answer[0].trim()));
				
			}
////			CustomerBean bean = customerService.login(username, password);
//			com.model.del.service.DelService service = new DelServiceImpl();
//			DelVO loginResultDelVO = service.delLogin(delTel, password);
//			
//	//根據model執行結果，導向view
//	//這邊回傳的結果裡面有一個delVO裡面的所有屬性為null，所以在get屬性之前不知道為什麼兩邊都進去了......
//			if(loginResultDelVO.getDelID()==null) {
////				respObj.addProperty("password", "登入失敗請重新嘗試");
//				errors.put("password", "登入失敗請重新嘗試");
//				
//
//				
//				System.out.println("輸入的帳密找不到資料所以失敗");
//				HttpSession session = request.getSession();
//				session.setAttribute("errors", errors);
//				
//				
//				request.getRequestDispatcher(
//						"/delLogin.html").forward(request, response);
//			} else {
//				HttpSession session = request.getSession();
//				session.setAttribute("del", loginResultDelVO);
//				
//				response.sendRedirect("del/del_index.html");
//				System.out.println("登入成功");
//			}
			
		
		
		
		
		
		
	
	
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
