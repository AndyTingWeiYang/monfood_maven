package com.model.pairlist.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession httpSession = request.getSession();
		Integer useraId = (Integer) httpSession.getAttribute("userID");
		java.util.Date date = new java.util.Date();
		java.sql.Date today = new java.sql.Date(date.getTime());
		Gson gson = new Gson();
		response.setContentType("application/json;charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		PairListService service = new PairListServiceImpl();
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(service.findPairInfo(useraId, today)));

	}


}
