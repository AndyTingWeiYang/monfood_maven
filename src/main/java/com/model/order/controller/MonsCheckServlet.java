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

@WebServlet("/MonsCheckServlet")
public class MonsCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();
		Integer result = null;
		try {
			// get the json from ajax and send to OrderVO
			OrderVO orderVO = gson.fromJson(request.getReader(), OrderVO.class);
			OrderService service = new OrderServiceImpl();
			service.monsCheck(orderVO.getUserId());

			
			if (result == null) {
				respObj.addProperty("errMsg", "新增訂單失敗");
				response.getWriter().append(gson.toJson(respObj));
				return;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			respObj.addProperty("errMsg", "系統錯誤");
		}
		
		// return data
		respObj.addProperty("OrderId", result);
		response.getWriter().append(gson.toJson(respObj));
		
	
	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
