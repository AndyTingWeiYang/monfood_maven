package com.model.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.model.user.service.UserService;
import com.model.user.serviceImpl.UserServiceImpl;

@WebServlet("/UserProfileUpdateServlet")
public class UserProfileUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userID");
		
		try {

			respObj = gson.fromJson(request.getReader(), JsonObject.class);
			String data = respObj.get("data").toString();
			String msg = respObj.get("msg").toString();
			
			UserService service = new UserServiceImpl();
			String result = service.updateProfile(userId, data, msg);
			
			if ("updateFailed".equals(result)) {
				respObj.addProperty("errMsg", "*更新失敗, 請重試");
				response.getWriter().append(gson.toJson(respObj));
				return;
			}
			
			respObj.addProperty("msg", "*更新成功");
			
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			respObj.addProperty("errMsg", "系統錯誤");
		}

		response.getWriter().append(gson.toJson(respObj));
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
