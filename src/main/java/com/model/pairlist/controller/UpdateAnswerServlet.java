 package com.model.pairlist.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.model.pairlist.PairListVo;
import com.model.pairlist.service.PairListService;
import com.model.pairlist.service.impl.PairListServiceImpl;

@WebServlet("/UpdateAnswerServlet")
public class UpdateAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//回傳json格式並使用UTF-8編碼
		resp.setContentType("application/json;charset=UTF-8");
		// *為允許可以跨域連線進自己設計的本機連線
		resp.addHeader("Access-Control-Allow-Origin", "*");
		Gson gson = new Gson();
		// get the json from ajax and send to PairListVo
		PairListVo pairListVo = new PairListVo();
		pairListVo = gson.fromJson(req.getReader(), PairListVo.class);
		java.util.Date date = new java.util.Date();
		java.sql.Date today = new java.sql.Date(date.getTime());
		pairListVo.setPairedDate(today);
		HttpSession httpSession = req.getSession();
		Integer useraId = (Integer) httpSession.getAttribute("userID");
		pairListVo.setUseraId(useraId);
		pairListVo.setUserbId(useraId);
		PairListService service = new PairListServiceImpl();
		service.updateAnswerAndStatus(pairListVo);
	}
	

}
