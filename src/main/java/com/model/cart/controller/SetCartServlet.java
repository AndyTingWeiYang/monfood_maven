package com.model.cart.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.model.cart.dao.JedisPoolUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@WebServlet("/SetCartServlet")
public class SetCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static JedisPool pool = JedisPoolUtil.getJedisPool();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		if (session.getAttribute("isuserLogin") == null) {
			return;
		}
		
		// get user id
		final String userId = session.getAttribute("userID").toString();
		Gson gson = new Gson();
		// get cart item from user
		JsonObject req = gson.fromJson(request.getReader(), JsonObject.class);
		JsonObject respObj = new JsonObject();
		Jedis jedis = pool.getResource();
		
		// set cart item in Redis
		jedis.rpush(userId, req.toString());
		
		response.getWriter().append(gson.toJson(respObj));
		jedis.close();
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}


