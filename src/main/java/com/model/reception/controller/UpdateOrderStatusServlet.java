package com.model.reception.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.model.product.util.IntTypeAdapter;
import com.model.reception.service.ResService;
import com.model.reception.service.impl.ResServiceImpl;

@WebServlet("/admin-res-reception/UpdateOrderStatusServlet")
public class UpdateOrderStatusServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Gson gson;
	private ResService resSrc;

	public UpdateOrderStatusServlet() {
		this.resSrc = new ResServiceImpl();
		this.gson = new GsonBuilder().registerTypeAdapter(int.class, new IntTypeAdapter())
				.registerTypeAdapter(Integer.class, new IntTypeAdapter()).create();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");// 告知[伺服器]用哪種編碼去解析
		response.setContentType("text/html;charset=UTF-8");// 告知[瀏覽器]送出的內容與文字編碼
		PrintWriter out = response.getWriter();

//		OrderVO orderVO = new OrderVO();
		HttpSession session = request.getSession(false);
		Integer resId = (Integer) session.getAttribute("resID");
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("resId", resId);
		dataMap.put("orderId", request.getParameter("orderId"));
		dataMap.put("orderStatus", request.getParameter("orderStatus"));

		System.out.println(dataMap);// 看拿到什麼

		List<Map<String, Object>> orderList = resSrc.updateOrderStatus(dataMap);
		JsonObject jsonObj = new JsonObject();
		// 將物件轉為 JsonElement
		jsonObj.add("orderList", gson.toJsonTree(orderList));

		// 轉為json字串
		out.write(gson.toJson(jsonObj));
	}

}
