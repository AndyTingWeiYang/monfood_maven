package com.model.order.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.model.order.OrderVO;
import com.model.order.service.OrderService;
import com.model.order.service.impl.OrderServiceImpl;

@WebServlet("/OrderAllUserServlet")
public class OrderAllUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();
		OrderVO orderVO = new OrderVO();
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userID");
		
		try {
			orderVO = gson.fromJson(request.getReader(), OrderVO.class);
			orderVO.setUserId(userId);
			
			OrderService service = new OrderServiceImpl();
			List<OrderVO> list = service.getAllForUser(orderVO.getUserId());
			List<OrderVO> productList = service.getAllProductUser(orderVO.getUserId());
			
			// add the list into json format
			respObj.add("userOrders", gson.toJsonTree(list));
			respObj.add("orderDetail", gson.toJsonTree(productList));
//			for (OrderVO pic : list) {
//				list.getClass();
//			}
//			
//			byte[] pic = result.getMonsPic();
//			String picBase64 = DatatypeConverter.printBase64Binary(pic);
//			respObj.addProperty("monsPic", picBase64);
			
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