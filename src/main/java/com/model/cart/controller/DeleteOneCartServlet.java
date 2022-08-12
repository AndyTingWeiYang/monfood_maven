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

@WebServlet("/DeleteOneCartServlet")
public class DeleteOneCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static JedisPool pool = JedisPoolUtil.getJedisPool();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		if (session.getAttribute("isuserLogin") == null) {
			return;
		}
		
		Gson gson = new Gson();
		JsonObject req = gson.fromJson(request.getReader(), JsonObject.class);
		// get user id
		final String userId = session.getAttribute("userID").toString();
		Jedis jedis = pool.getResource();
		// delete cart item from Redis
		jedis.lrem(userId, 0, jedis.lindex(userId, Long.parseLong(req.get("index").toString())));
		jedis.close();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}


