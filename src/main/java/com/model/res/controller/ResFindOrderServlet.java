package com.model.res.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.runtime.ObjectMethods;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.model.order.OrderVO;
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
		this.gson = new Gson();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
//		ajaxFormSubmit(request, response);
		ajaxPayLoad(request, response);

	}

	private void ajaxPayLoad(HttpServletRequest request, HttpServletResponse response)
			throws JsonSyntaxException, JsonIOException, IOException {
		// 創建出 json 物件
		JsonObject jsonObj = new JsonObject();
		// getReader 接取 jsp 傳來的參數 轉為Vo
		OrderVO orderVO = gson.fromJson(request.getReader(), OrderVO.class);
		// 從 vo 取出 orderId 屬性 將參數拋出去 
		List<Map<String, Object>> orderList = resService.resFindOrderService(orderVO.getOrderId());
		// 將 list 放入 JsonTree => 回傳 JsonElement
		jsonObj.add("orderList", gson.toJsonTree(orderList));
		
		String jsonObjStr = gson.toJson(jsonObj);
		PrintWriter out=response.getWriter();
		out.write(jsonObjStr);
		}

	private void ajaxFormSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer orderId = Integer.parseInt(request.getParameter("orderId"));
		List<Map<String, Object>> orderList = resService.resFindOrderService(orderId);
		// 將回傳值設定在 jsonObj => 要呼叫方法
		JsonObject jsonObj = new JsonObject();

		// addProperty (k,v) => 第一個參數放string 第二個參數放string，可以利用gson.toJson 是轉為字串
		jsonObj.addProperty("orderList", gson.toJson(orderList));
		// add(k,v) => 第一個參數放string 第二個參數放 jsonElements,可以利用 gson.toJsonTree 轉為物件接收
		jsonObj.add("orderList2", gson.toJsonTree(orderList));

		String jsonStr = gson.toJson(jsonObj);
		PrintWriter out = response.getWriter();

		out.write(jsonStr);
	}

	private void formSubmitPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer orderId = Integer.parseInt(request.getParameter("orderId"));
		List<Map<String, Object>> orderList = resService.resFindOrderService(orderId);

		request.setAttribute("orderList", orderList);
		RequestDispatcher rd = request.getRequestDispatcher("resprofile-order-history.jsp");
		rd.forward(request, response);

	}

}
