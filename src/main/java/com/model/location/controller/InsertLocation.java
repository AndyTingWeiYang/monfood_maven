package com.model.location.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.model.location.LocationVO;
import com.model.location.service.LocationService;
import com.model.location.service.impl.LocationServiceImpl;

@WebServlet("/InsertLocation")
public class InsertLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userID");
		
		try {
			
			LocationVO locationVO = gson.fromJson(request.getReader(), LocationVO.class);
			locationVO.setUserId(userId);
			
			LocationService service = new LocationServiceImpl();
			service.insert(locationVO);
			
			respObj.addProperty("msg", "success");
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
