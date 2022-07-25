package com.model.promotedetail.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.model.promotedetail.PromoteDetailVO;
import com.model.promotedetail.service.PromoteDetailService;
import com.model.promotedetail.service.Impl.PromoteDetailServiceImpl;


@WebServlet("/PromoteDetailServlet")
public class PromoteDetailServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
		JsonObject respObj = new JsonObject();
		String result = null;
		PrintWriter out = response.getWriter();
		
		try {
			PromoteDetailVO promoteDetailVO = gson.fromJson(request.getReader(), PromoteDetailVO.class);
			PromoteDetailService service = new PromoteDetailServiceImpl();
			result = service.AddPromoteDetail(promoteDetailVO);

		} catch (Exception e) {
			e.printStackTrace();
			respObj.addProperty("errMsg", "servlet系統錯誤");
		}
		response.getWriter().append(gson.toJson(respObj));
	}


}
