package com.model.reception.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.reception.service.ResService;
import com.model.reception.service.impl.ResServiceImpl;


@WebServlet("/admin-res-reception/FindByOrderServlet")
public class FindByOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       ResService resService;
   
    public FindByOrderServlet() {
    	resService=new ResServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;chart=UTF-8");
		// 從 session 取得resId, orderId
		HttpSession session = request.getSession(false);
		// 從畫面中取得的參數轉成變數 強制轉型成(String)
		String resId = (String)session.getAttribute("resId");
		String orderId=(String)session.getAttribute("orderId");
		
		// 將變數傳到後端
		resService.findByOrder(resId, orderId);
		// 設定好參數傳送到前端
		request.setAttribute("resId", resId);
		request.setAttribute("orderId", orderId);
		// 設定想要將參數傳遞到哪個畫面顯示
		RequestDispatcher rd=request.getRequestDispatcher("resReception-new-order.jsp");
		// 送出
		rd.forward(request, response);
	}

}
