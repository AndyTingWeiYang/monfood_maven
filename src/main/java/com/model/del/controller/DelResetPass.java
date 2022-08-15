package com.model.del.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.model.del.DelService;
import com.model.del.DelVO;
import com.model.del.service.DelServiceImpl;
import com.model.res.ResVO;
import com.model.res.service.ResService;
import com.model.res.service.impl.ResServiceImpl;

@WebServlet("/DelResetPass")
public class DelResetPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();

		try {
			DelVO resetDelVO = gson.fromJson(request.getReader(), DelVO.class);
			com.model.del.service.DelService service = new DelServiceImpl();
			String resetResult = service.resetPassword(resetDelVO.getDelAccount(), resetDelVO.getDelPassword());

			if ("修改密碼成功" == resetResult) {
				respObj.addProperty("Success", "updateSuccess"); 
				response.getWriter().append(gson.toJson(respObj));
			} else if ("此帳號尚未註冊" == resetResult) {
				respObj.addProperty("Failed", "updateFailed");
				response.getWriter().append(gson.toJson(respObj));
			}

		} catch (Exception e) {
			e.printStackTrace();
			respObj.addProperty("errMsg", "系統錯誤");
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
