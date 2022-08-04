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

@WebServlet("/UpdateOrderRating")
public class UpdateOrderRating extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();
		
		try {
			// get the json from ajax and send to OrderVO
			OrderVO orderVO = gson.fromJson(request.getReader(), OrderVO.class);
			OrderService service = new OrderServiceImpl();
			
			// send the orderVO to service for validation
			final Integer result = service.updateRating(orderVO);
			
			// validate the result from service
			if (result < 0) {
				respObj.addProperty("errMsg", "新增失敗");
				response.getWriter().append(gson.toJson(respObj));
				return;
			}
			
			// add the result into json format
			respObj.addProperty("msg", "新增成功");
			
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
