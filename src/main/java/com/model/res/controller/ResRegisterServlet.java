package com.model.res.controller;

import java.io.IOException;
import java.sql.Timestamp;

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

@WebServlet("/ResRegisterServlet")
public class ResRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();

		try {
			ResVO resVO = gson.fromJson(request.getReader(), ResVO.class);
			long now = System.currentTimeMillis();
			Timestamp sqlTimestamp = new Timestamp(now);
			resVO.setUpdateTime(sqlTimestamp);

			ResService service = new ResServiceImpl();

			final String registerResult = service.resRegister(resVO);
			System.out.println("in ResServlet - registerResult：" + registerResult);
			if (registerResult == "Register Success") {
				respObj.addProperty("Registration Success", "註冊成功!!");
				response.getWriter().append(gson.toJson(respObj));
			} else {
				respObj.addProperty("errMsg", "註冊失敗，請重新註冊");
				response.getWriter().append(gson.toJson(respObj));
				System.out.println("in ResServlet im done");
			}

		} catch (Exception e) {
			e.printStackTrace();
			respObj.addProperty("errMsg", "系統錯誤");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
