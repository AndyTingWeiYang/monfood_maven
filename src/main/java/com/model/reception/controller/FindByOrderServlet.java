package com.model.reception.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.model.reception.service.ResService;
import com.model.reception.service.impl.ResServiceImpl;

@WebServlet("/admin-res-reception/FindByOrderServlet")
public class FindByOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ResService resService;
	Gson gson;

	public FindByOrderServlet() {
		resService = new ResServiceImpl();
		gson = new Gson();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		// 從 session 取得resId, orderId
		HttpSession session = request.getSession(false);
		// 從畫面中取得的參數轉成變數 強制轉型成(String)
		Integer resId = (Integer) session.getAttribute("resID");
		String orderStatus = request.getParameter("orderStatus");

		// 將變數傳到後端
		List<Map<String, Object>> orderList = resService.findByOrder(resId, orderStatus);

		JsonObject jsonObj = new JsonObject();
		// 將物件轉為 JsonElement
		jsonObj.add("orderList", gson.toJsonTree(orderList));

		// 轉為json字串
		out.write(gson.toJson(jsonObj));
	}

//	private void formSubmit(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;chart=UTF-8");
//		// 從 session 取得resId, orderId
//		HttpSession session = request.getSession(false);
//		// 從畫面中取得的參數轉成變數 強制轉型成(String)
//		Integer resId = (Integer) session.getAttribute("resID");
//		String orderStatus = request.getParameter("orderStatus");
//
//		// 將變數傳到後端
//		resService.findByOrder(resId, orderStatus);
//		// 設定好參數傳送到前端
//		request.setAttribute("resId", resId);
//		request.setAttribute("orderId", orderStatus);
//		// 設定想要將參數傳遞到哪個畫面顯示
//		RequestDispatcher rd = request.getRequestDispatcher("resReception-new-order.jsp");
//		// 送出
//		rd.forward(request, response);
//	}

}
