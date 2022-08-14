package com.model.res.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.runtime.ObjectMethods;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.model.order.OrderVO;
import com.model.product.util.IntTypeAdapter;
import com.model.res.ResVO;
import com.model.res.service.ResService;
import com.model.res.service.impl.ResServiceImpl;

@WebServlet("/resprofile/ResFindOrderServlet")
public class ResFindOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResService resService;
	private Gson gson;

	public ResFindOrderServlet() {
		this.resService = new ResServiceImpl();
		this.gson = new GsonBuilder().registerTypeAdapter(int.class, new IntTypeAdapter())
				.registerTypeAdapter(Integer.class, new IntTypeAdapter()).create();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		ajaxPayLoad(request, response);

	}

	private void ajaxPayLoad(HttpServletRequest request, HttpServletResponse response)
			throws JsonSyntaxException, JsonIOException, IOException {
		// 創建出 json 物件
		JsonObject jsonObj = new JsonObject();
		HttpSession session = request.getSession(false);
		Integer resID = (Integer) session.getAttribute("resID");
		// getReader 接取 jsp 傳來的參數 轉為Vo
		OrderVO orderVO = gson.fromJson(request.getReader(), OrderVO.class);
		orderVO.setResId(resID);
		// 從 vo 取出 orderId 屬性 將參數拋出去
		List<Map<String, Object>> orderList = resService.resFindOrderService(orderVO);

		// 將 list 放入 JsonTree => 回傳 JsonElement
		jsonObj.add("orderList", gson.toJsonTree(orderList));

		String jsonObjStr = gson.toJson(jsonObj);
		PrintWriter out = response.getWriter();
		out.write(jsonObjStr);
	}

}
