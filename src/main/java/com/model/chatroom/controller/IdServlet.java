package com.model.chatroom.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.model.pairlist.dao.PairListDao;
import com.model.pairlist.dao.impl.PairListDaoImpl;
import com.model.user.dao.UserDAO;
import com.model.user.dao.impl.UserDAOImpl;


@WebServlet("/IdServlet")
public class IdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDAO userDao;

	public IdServlet() {
		userDao = new UserDAOImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		response.setContentType("application/json;charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		HttpSession httpSession = request.getSession();
		Integer userId = (Integer) httpSession.getAttribute("userID");
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(userDao.selectByUserId(userId)));
	}
	
	
	
}
