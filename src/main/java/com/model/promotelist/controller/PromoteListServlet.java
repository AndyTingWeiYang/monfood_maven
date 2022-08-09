package com.model.promotelist.controller;

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
import com.model.promotelist.PromoteListVO;
import com.model.promotelist.service.PromoteListService;
import com.model.promotelist.service.Impl.PromoteListServiceImpl;

@WebServlet("/PromoteListServlet")
public class PromoteListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8;");
		request.setCharacterEncoding("UTF-8");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		JsonObject respObj = new JsonObject();
		String result = null;
		PrintWriter out = response.getWriter();

		try {
			PromoteListVO promoteListVO = gson.fromJson(request.getReader(), PromoteListVO.class);
			PromoteListService service = new PromoteListServiceImpl();
			result = service.adminAddPromoteList(promoteListVO);
				out.write(result);

		} catch (Exception e) {
			e.printStackTrace();
			respObj.addProperty("errMsg", "servlet系統錯誤");
		}
		response.getWriter().append(gson.toJson(respObj));
	}

}
