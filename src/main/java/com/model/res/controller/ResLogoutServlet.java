package com.model.res.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/ResLogoutServlet")
public class ResLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		 PrintWriter out = response.getWriter();
	        request.setCharacterEncoding("UTF-8");
	        request.getRequestDispatcher("/resLogin.html").include(request, response);
	        HttpSession session = request.getSession();
	        // 清除資料
	        session.invalidate();
	        out.print("您已成功登出退出系統!");
	        out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
