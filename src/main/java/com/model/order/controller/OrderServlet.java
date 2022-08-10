package com.model.order.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.model.order.OrderVO;
import com.model.order.service.OrderService;
import com.model.order.service.impl.OrderServiceImpl;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();
		Integer orderId = null;
		String result = null;
		
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userID");
		
		try {
			// get the json from ajax and send to OrderVO
			JsonObject req = gson.fromJson(request.getReader(), JsonObject.class);
			
			// OrderVO
			OrderVO orderVO = gson.fromJson(req.get("order"), OrderVO.class);
			orderVO.setUserId(userId);
			
			// OrderDetailVO
			JsonObject orderDetailObj = gson.fromJson(req.get("orderDetail"), JsonObject.class);
			JsonArray orderDetailArray = orderDetailObj.getAsJsonArray("orderDetailVO");
			List<JsonElement> list = new ArrayList<JsonElement>();
			for (JsonElement orderDetail : orderDetailArray) {
				list.add(orderDetail);
			}
			
			// ProductName
			JsonArray name = gson.fromJson(req.get("productName"), JsonArray.class);
			List<String> nameList = new ArrayList<String>();
			for (int i = 0; i < name.size(); i++) {
				nameList.add(name.get(i).getAsJsonObject().get("productName").toString().substring(1, name.get(i).getAsJsonObject().get("productName").toString().length()-1));
			}
			
			// create order&orderdetail
			OrderService service = new OrderServiceImpl();
			orderId = service.createOrder(list, orderVO);
			respObj.addProperty("OrderId", orderId);
			respObj.addProperty("userId", userId);
			
			// validate the orderId get from service
			if (orderId < 1) {
				respObj.addProperty("errMsg", "新增訂單失敗");
				response.getWriter().append(gson.toJson(respObj));
				return;
			}
			
			
			// send the orderId/ OrderVO/ nameList for ecpay
			if (orderVO.getUseCash() == false) {
				
				result = service.ecpayValidation(nameList, orderId, orderVO);
				if(result == null) {
					respObj.addProperty("errMsg", "綠界支付失敗");
					response.getWriter().append(gson.toJson(respObj));
					return;
				}
				respObj.addProperty("result", result);
			}
			
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
