package com.model.res.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.model.res.ResVO;
import com.model.res.service.ResService;
import com.model.res.service.impl.ResServiceImpl;

@WebServlet("/ResResetPass")
public class ResResetPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();

		try {
			ResVO fromResKeyInVO = gson.fromJson(request.getReader(), ResVO.class);
			ResService service = new ResServiceImpl();
//			UserVO userVO = new UserVO();
			System.out.println("我是fromResKeyInVO = "+fromResKeyInVO);
			final String resetResult = service.resetPassword(fromResKeyInVO);

			System.out.println("我在ResetPass Servlet = " + resetResult);

			if (resetResult == "ResetSuccessfully") {
				respObj.addProperty("Success", "updateSuccess"); 
				response.getWriter().append(gson.toJson(respObj));
			} else if (resetResult == "ResetFailed") {
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
