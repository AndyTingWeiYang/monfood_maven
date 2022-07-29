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

@WebServlet("/ResAccountPassServlet")
public class ResAccountPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();
	
		try {
			ResVO resVO = gson.fromJson(request.getReader(), ResVO.class);
			System.out.println("我在ResAccountPassServlet我是resVO = "+ resVO);
			ResService service = new ResServiceImpl();

			final String checkAccount = service.isDuplicateAccount(resVO);
			System.out.println("in ResAccountPassServlet - checkAccount：" + checkAccount);
			if (checkAccount == "pass") {
				respObj.addProperty("checkAccountSuccess", "Success");
				response.getWriter().append(gson.toJson(respObj));	
			}else if(checkAccount == "DuplicateAccount") {
				respObj.addProperty("checkAccountFailed", "Failed");
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
