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

@WebServlet("/PromoteListOneServlet")
public class PromoteListOneServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();
		
		try {
			
			PromoteListVO promoteListVO = gson.fromJson(request.getReader(), PromoteListVO.class);
			PromoteListService promoteListservice = new PromoteListServiceImpl();
			
			final PromoteListVO result = promoteListservice.adminFindpromoteListOne(promoteListVO);
			
			if (result == null) {
				respObj.addProperty("errMsg", "無此優惠券");
				response.getWriter().append(gson.toJson(respObj));
				return;
			}
			
			// add the result into json format
			respObj.add("PromoteLists", gson.toJsonTree(result));
			
		} catch (Exception e) {
			e.printStackTrace();
			respObj.addProperty("errMsg", "findOneServlet系統錯誤");
		}
		
		// return data
		response.getWriter().append(gson.toJson(respObj));
	}
	
	
}
