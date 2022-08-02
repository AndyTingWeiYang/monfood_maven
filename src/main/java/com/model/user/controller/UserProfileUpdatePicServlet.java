package com.model.user.controller;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.model.user.service.UserService;
import com.model.user.serviceImpl.UserServiceImpl;

@WebServlet("/UserProfileUpdatePicServlet")
public class UserProfileUpdatePicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();

		try {

			respObj = gson.fromJson(request.getReader(), JsonObject.class);
			String pic = respObj.get("pic").toString();
			System.out.println(pic);
			pic.substring(0, 23);
			pic.substring(pic.length()-1);
			byte[] picb = pic.getBytes();
			System.out.println(picb);
			byte[] decode = Base64.getDecoder().decode(picb);
			System.out.println(decode);
			UserService service = new UserServiceImpl();
			
			
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
