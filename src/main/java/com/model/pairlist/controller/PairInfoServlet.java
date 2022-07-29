package com.model.pairlist.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.model.pairlist.service.PairListService;
import com.model.pairlist.service.impl.PairListServiceImpl;

/**
 * Servlet implementation class PairInfoServlet
 */
@WebServlet("/PairInfoServlet")
public class PairInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//useraId待改為從登入session獲取的id參數(先寫死) [to be revised]
		Integer useraId =2;
		java.util.Date date = new java.util.Date();
		java.sql.Date today = new java.sql.Date(date.getTime());
		Gson gson = new Gson();
		response.setContentType("application/json;charset=UTF-8");//回傳json格式並使用UTF-8編碼
		response.addHeader("Access-Control-Allow-Origin", "*");//*為允許可以跨域連線進自己設計的本機連線
		PairListService service = new PairListServiceImpl();
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(service.findPairInfo(useraId, today)));

	}


}
