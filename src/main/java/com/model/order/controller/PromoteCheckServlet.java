package com.model.order.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.model.order.OrderVO;
import com.model.order.service.OrderService;
import com.model.order.service.impl.OrderServiceImpl;
import com.model.promotelist.PromoteListVO;

@WebServlet("/PromoteCheckServlet")
public class PromoteCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();
		
		try {
			// get the json from ajax and send to OrderVO
			PromoteListVO promoteListVO = gson.fromJson(request.getReader(), PromoteListVO.class);
			OrderService service = new OrderServiceImpl();
			
			final PromoteListVO result = service.promoteCheck(promoteListVO);
//			System.out.println(result.getPromotePrice());
			if (result == null) {
				respObj.addProperty("errMsg", "無此優惠代碼");
				response.getWriter().append(gson.toJson(respObj));
				return;
			}
			
			respObj.add("promoteCode", gson.toJsonTree(result));
			
		} catch (Exception e) {
			e.printStackTrace();
			respObj.addProperty("errMsg", "系統錯誤");
		}
		
		// return data
		response.getWriter().append(gson.toJson(respObj));
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
