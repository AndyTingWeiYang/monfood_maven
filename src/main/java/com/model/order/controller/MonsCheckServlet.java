package com.model.order.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.model.monster.MonsterVO;
import com.model.order.OrderVO;
import com.model.order.service.OrderService;
import com.model.order.service.impl.OrderServiceImpl;

@WebServlet("/MonsCheckServlet")
public class MonsCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();
		MonsterVO result = null;
		Integer orderTimes = null;
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userID");
		
		try {
			// get the json from ajax and send to OrderVO
			OrderVO orderVO = gson.fromJson(request.getReader(), OrderVO.class);
			orderVO.setUserId(userId);
			OrderService service = new OrderServiceImpl();
			result = service.monsCheck(orderVO.getUserId());
			orderTimes = service.orderTimes(orderVO.getUserId());
			
			if (result == null) {
				respObj.addProperty("errMsg", "小怪獸查詢失敗");
				response.getWriter().append(gson.toJson(respObj));
				return;
			}
			respObj.add("monsterInfo", gson.toJsonTree(result));
			respObj.addProperty("orderTimes", orderTimes);
			byte[] pic = result.getMonsPic();
			String picBase64 = DatatypeConverter.printBase64Binary(pic);
			respObj.addProperty("monsPic", picBase64);
		} catch (Exception e) {
			e.printStackTrace();
			respObj.addProperty("errMsg", "系統錯誤");
			response.getWriter().append(gson.toJson(respObj));
			return;
		}
		
		// return data
		response.getWriter().append(gson.toJson(respObj));
	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
