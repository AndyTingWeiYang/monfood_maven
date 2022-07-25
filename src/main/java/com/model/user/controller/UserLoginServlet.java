package com.model.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.model.user.UserVO;
import com.model.user.service.UserService;
import com.model.user.serviceImpl.UserServiceImpl;

@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();
		
		try {
			UserVO fromUserKeyInVO = gson.fromJson(request.getReader(), UserVO.class);
			
			UserService service = new UserServiceImpl();
			final UserVO loginResult = service.userLogin(fromUserKeyInVO);
			
			System.out.println(loginResult);
			
			String aaa = fromUserKeyInVO.getUserAccount();
			String bbb = fromUserKeyInVO.getUserPassword();

			if(loginResult == null) {
				respObj.addProperty("None", "查無帳號");
				response.getWriter().append(gson.toJson(respObj));
				System.out.println("查無帳號");
				return;
			}else if (loginResult.getUserAccount().equals(aaa) && loginResult.getUserPassword().equals(bbb)) {
				respObj.addProperty("LoginSuceesfully", "登入成功");
				response.getWriter().append(gson.toJson(respObj));
				System.out.println("登入成功!!");
				
			}else if (loginResult.getUserAccount().equals(aaa) && !(loginResult.getUserPassword().equals(bbb))) {
				respObj.addProperty("ErrorPassword", "密碼有誤，請重新輸入");
				response.getWriter().append(gson.toJson(respObj));
				System.out.println("密碼有誤，請重新輸入!!");
				return;
			}
						
			System.out.println("資料庫 = " +loginResult.getUserAccount());
			System.out.println("使用者輸入帳號 = " +aaa);
			System.out.println("=================================");
			System.out.println("資料庫 = " +loginResult.getUserPassword());
			System.out.println("使用者輸入密碼 = " +bbb);
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
			respObj.addProperty("errMsg", "系統錯誤");
			
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
