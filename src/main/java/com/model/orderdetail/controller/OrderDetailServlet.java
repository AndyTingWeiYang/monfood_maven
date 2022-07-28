package com.model.orderdetail.controller;

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
import com.model.orderdetail.OrderDetailVO;
import com.model.orderdetail.service.OrderDetailService;
import com.model.orderdetail.service.impl.OrderDetailServiceimpl;

@WebServlet("/OrderDetailServlet")
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();
		
		try {
			// get the json from ajax and send to OrderDetailVO
			OrderDetailVO orderDetailVO = gson.fromJson(request.getReader(), OrderDetailVO.class);
			OrderDetailService service = new OrderDetailServiceimpl();
			
			// validate result from ajax
			if (orderDetailVO == null) {
				respObj.addProperty("errMsg", "新增訂單失敗");
				response.getWriter().append(gson.toJson(respObj));
				return;
			}
			
			service.createOrderDetail(orderDetailVO);
			
		} catch (Exception e) {
			e.printStackTrace();
			respObj.addProperty("errMsg", "系統錯誤");
			response.getWriter().append(gson.toJson(respObj));
			return;
		}
		
		// return data
		respObj.addProperty("Msg", "success");
		response.getWriter().append(gson.toJson(respObj));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
