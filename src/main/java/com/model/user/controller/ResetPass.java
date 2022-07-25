package com.model.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.model.user.UserVO;
import com.model.user.service.UserService;
import com.model.user.serviceImpl.UserServiceImpl;

@WebServlet("/ResetPass")
public class ResetPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();
		
		try {
			UserVO fromUserKeyInVO = gson.fromJson(request.getReader(), UserVO.class);		
			UserService service = new UserServiceImpl();
			
			final String resetResult = service.resetPassword(fromUserKeyInVO);
			
			System.out.println("我在ResetPass Servlet = "+resetResult);
			
			if (resetResult == "ResetSuccessfully") {
				respObj.addProperty("Success", "更新成功");
				response.getWriter().append(gson.toJson(respObj));
			}else if(resetResult == "ResetFailed") {
				respObj.addProperty("Failed", "更新失敗");
				response.getWriter().append(gson.toJson(respObj));
			}
		} catch (Exception e) {
			e.printStackTrace();
			respObj.addProperty("errMsg", "系統錯誤");
		}
			
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
