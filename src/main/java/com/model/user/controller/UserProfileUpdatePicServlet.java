package com.model.user.controller;

import java.io.IOException;
import java.util.Base64;

import javax.lang.model.element.NestingKind;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
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
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userID");
		
		try {

			JsonObject data = gson.fromJson(request.getReader(), JsonObject.class);
			String pic64 = data.get("pic").toString();
//			Integer userId = Integer.parseInt(String.valueOf(data.get("userId").toString()));
			byte[] picByte = Base64.getDecoder().decode((String) pic64.subSequence(1, pic64.length()-1));
			UserService service = new UserServiceImpl();
			String result = service.updateProfilePic(picByte, userId);
			
			respObj.addProperty("msg", result);
			
		} catch (Exception e) {
			e.printStackTrace();
			respObj.addProperty("errMsg", "系統錯誤");
		}

		response.getWriter().append(gson.toJson(respObj));
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
