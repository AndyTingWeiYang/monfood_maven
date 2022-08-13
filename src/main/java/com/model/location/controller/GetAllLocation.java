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

@WebServlet("/GetAllLocation")
public class GetAllLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();
		HttpSession session = request.getSession();
		
		if ("null".equals(session.getAttribute("userID"))) {
			return;
		}
		
		Integer userId = (Integer) session.getAttribute("userID");
		
		if (userId == null) {
			return;
		}
		
		try {
			LocationService service = new LocationServiceImpl();
			List<LocationVO> list = service.getAllById(userId);
			
			respObj.add("locations", gson.toJsonTree(list));
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
