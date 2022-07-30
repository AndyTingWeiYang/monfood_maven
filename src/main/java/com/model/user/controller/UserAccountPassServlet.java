package com.model.user.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.model.user.UserVO;
import com.model.user.service.UserService;
import com.model.user.serviceImpl.UserServiceImpl;

import mailservice.MailService;

@WebServlet("/UserAccountPassServlet")
public class UserAccountPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();
		

		
		try {
			UserVO userVO = gson.fromJson(request.getReader(), UserVO.class);

			UserService service = new UserServiceImpl();

			final String checkAccount = service.isDuplicateAccount(userVO);
			System.out.println("in UserAccountPassServlet - checkAccount：" + checkAccount);
			if (checkAccount == "pass") {
				respObj.addProperty("checkAccountSuccess", "Success");
				response.getWriter().append(gson.toJson(respObj));	
			}else if(checkAccount == "DuplicateAccount") {
				respObj.addProperty("checkAccountFailed", "Failed");
				response.getWriter().append(gson.toJson(respObj));
			}

		} catch (Exception e) {
			e.printStackTrace();
			respObj.addProperty("errMsg", "系統錯誤");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
