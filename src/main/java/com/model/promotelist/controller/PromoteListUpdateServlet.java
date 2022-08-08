package com.model.promotelist.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.model.product.util.IntTypeAdapter;
import com.model.promotelist.PromoteListVO;
import com.model.promotelist.service.PromoteListService;
import com.model.promotelist.service.Impl.PromoteListServiceImpl;
import com.model.res.service.ResService;
import com.model.res.service.impl.ResServiceImpl;
			
@WebServlet("/PromoteListUpdateServlet")
public class PromoteListUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();

		try {
			PromoteListVO promoteListVO= gson.fromJson(request.getReader(), PromoteListVO.class);
			
			PromoteListService service = new PromoteListServiceImpl();
			Integer status = service.adminUpdatePromoteList(promoteListVO);
			
			// add the list into json format
			respObj.addProperty("status", status);
		} catch (Exception e) {
			e.printStackTrace();
			respObj.addProperty("errMsg", "系統錯誤");
		}

		// return data
		response.getWriter().append(gson.toJson(respObj));
		System.out.println("清單:"+gson.toJson(respObj));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	

}
