package com.model.promotelist.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.model.promotelist.PromoteListVO;
import com.model.promotelist.service.PromoteListService;
import com.model.promotelist.service.Impl.PromoteListServiceImpl;

@WebServlet("/PromoteListServlet")
public class PromoteListServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();
		
		try {
			PromoteListVO promoteListVO = gson.fromJson(request.getReader(), PromoteListVO.class);
			PromoteListService service = new PromoteListServiceImpl();
			final PromoteListVO result = service.adminFindVO(promoteListVO);
			
			respObj.addProperty("promoteId", result.getPromoteId());
			respObj.addProperty("promoteCode", result.getPromoteCode());
			respObj.addProperty("promotePrice", result.getPromotePrice());
//			respObj.addProperty("startDate", result.getStartDate());
//			respObj.addProperty("endDate", result.getEndDate());
			respObj.addProperty("status", result.getStatus());
	

			
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
