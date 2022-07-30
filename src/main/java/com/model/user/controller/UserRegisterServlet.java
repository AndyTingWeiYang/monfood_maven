package com.model.user.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Member;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.model.user.UserVO;
import com.model.user.dao.impl.UserDAOImpl;
import com.model.user.service.UserService;
import com.model.user.serviceImpl.UserServiceImpl;

import netscape.javascript.JSObject;

@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		JsonObject respObj = new JsonObject();

		try {
			UserVO userVO = gson.fromJson(request.getReader(), UserVO.class);
			long now = System.currentTimeMillis();
			Timestamp sqlTimestamp = new Timestamp(now);
			userVO.setUpdateTime(sqlTimestamp);

			UserService service = new UserServiceImpl();
				
			final String registerResult = service.userRegister(userVO);
			System.out.println("in UserServlet - registerResult：" + registerResult);
			if (registerResult == "Register Success") {
				respObj.addProperty("Registration Success", "註冊成功!!");
				response.getWriter().append(gson.toJson(respObj));
			} else {
				respObj.addProperty("errMsg", "註冊失敗，請重新註冊");
				response.getWriter().append(gson.toJson(respObj));
				System.out.println("in UserServlet im done");			
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
