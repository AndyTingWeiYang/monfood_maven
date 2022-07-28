package com.model.user.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.model.user.service.UserService;
import com.model.user.serviceImpl.UserServiceImpl;

@WebServlet("/AccountStatusServlet/*")
public class AccountStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();

		try {
			// 取得網址中的使用者帳號
			String URL = request.getRequestURI().toString();

			String[] params = URL.split("/");
			String userAccount = null;

			for (int i = 0; i < 1; i++) {
				userAccount = params[3];
				System.err.println(userAccount);
			}

			// 呼叫service生效帳號
			UserService service = new UserServiceImpl();
			final String resetResult = service.resetAccountStatus(userAccount);

			if (resetResult == "ResetSuccessfully") {
				respObj.addProperty("Success", "updateSuccess");
				response.getWriter().append(gson.toJson(respObj));
			} else if (resetResult == "ResetFailed") {
				respObj.addProperty("Failed", "updateFailed");
				response.getWriter().append(gson.toJson(respObj));
			}
		} catch (IOException e) {
			e.printStackTrace();
			respObj.addProperty("errMsg", "系統錯誤");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
