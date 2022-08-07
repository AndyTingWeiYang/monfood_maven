package com.model.promotedetail.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.model.promotedetail.PromoteDetailVO;
import com.model.promotedetail.service.PromoteDetailService;
import com.model.promotedetail.service.Impl.PromoteDetailServiceImpl;

@WebServlet("/PromoteDetailOneServlet")
public class PromoteDetailOneServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userID");
		
		try {
			
			PromoteDetailVO promoteDetailVO = gson.fromJson(request.getReader(), PromoteDetailVO.class);
			promoteDetailVO.setUserId(userId);
			
			PromoteDetailService prmotDetailService = new PromoteDetailServiceImpl();
			final Integer result = prmotDetailService.FindPromoteDetailOne(promoteDetailVO);
			
			if (result == 0) {
				respObj.addProperty("msg", "優惠券未使用");
				response.getWriter().append(gson.toJson(respObj));
				return;
			}
			
			// add the result into json format
			respObj.addProperty("msg", result);
			
		} catch (Exception e) {
			e.printStackTrace();
			respObj.addProperty("errMsg", "findOneServlet系統錯誤");
		}
		
		// return data
		response.getWriter().append(gson.toJson(respObj));
	}
}
