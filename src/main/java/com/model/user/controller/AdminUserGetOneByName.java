package com.model.user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.model.user.UserVO;
import com.model.user.service.UserService;
import com.model.user.serviceImpl.UserServiceImpl;

@WebServlet("/AdminUserGetOneByName")
public class AdminUserGetOneByName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();

		try {

			UserVO userVO = gson.fromJson(request.getReader(), UserVO.class);
			UserService service = new UserServiceImpl();
			 List<UserVO> result = service.getOneByUserName(userVO.getUserName());
			
			if (result.isEmpty()) {
				respObj.addProperty("errMsg", "無此會員");
				response.getWriter().append(gson.toJson(respObj));
				return;
			}

			respObj.add("userVO", gson.toJsonTree(result));
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			respObj.addProperty("errMsg", "findOneServlet系統錯誤");
		}

		response.getWriter().append(gson.toJson(respObj));
	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
