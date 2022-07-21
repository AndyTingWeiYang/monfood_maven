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

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();
		
		try {
			OrderVO orderVO = gson.fromJson(request.getReader(), OrderVO.class);
			OrderService service = new OrderServiceImpl();
			final OrderVO result = service.adminFindVO(orderVO);
			
			respObj.addProperty("orderId", result.getOrderId());
			respObj.addProperty("userId", result.getUserId());
			respObj.addProperty("resId", result.getResId());
			respObj.addProperty("delId", result.getDelId());
			respObj.addProperty("orderStatus", result.getOrderStatus());
			respObj.addProperty("note", result.getNote());
			respObj.addProperty("userLocation", result.getUserLocation());
			respObj.addProperty("orderCreate", result.getOrderCreate().toString());
			respObj.addProperty("orderDone", result.getOrderDone().toString());
			respObj.addProperty("productKcalTotal", result.getProductKcalTotal());
			respObj.addProperty("total", result.getTotal());
			respObj.addProperty("delCost", result.getDelCost());
			respObj.addProperty("useCash", result.getUseCash());
			respObj.addProperty("creditId", result.getCreditId());
			respObj.addProperty("bonus", result.getBonus());
			respObj.addProperty("rating", result.getRating());
			respObj.addProperty("resRate", result.getResRate());
			respObj.addProperty("delRate", result.getDelRate());
			respObj.addProperty("resComment", result.getResComment());
			respObj.addProperty("delComment", result.getDelComment());
			respObj.addProperty("promoteId", result.getPromoteId());
			
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
