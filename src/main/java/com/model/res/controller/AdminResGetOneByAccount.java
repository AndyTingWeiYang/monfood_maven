package com.model.res.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.model.res.ResVO;
import com.model.res.service.ResService;
import com.model.res.service.impl.ResServiceImpl;
import com.model.user.UserVO;
import com.model.user.service.UserService;
import com.model.user.serviceImpl.UserServiceImpl;

@WebServlet("/AdminResGetOneByAccount")
public class AdminResGetOneByAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();
	
		try {

			ResVO resVO = gson.fromJson(request.getReader(), ResVO.class);
			ResService service = new ResServiceImpl();
			final ResVO result = service.getOneByResAccount(resVO.getResAccount());

			if (result == null) {
				respObj.addProperty("errMsg", "無此會員");
				response.getWriter().append(gson.toJson(respObj));
				return;
			}

			respObj.add("resVO", gson.toJsonTree(result));
			// 將DB圖片編碼成base64
			byte[] pic = result.getResPic();
			String picBase64 = DatatypeConverter.printBase64Binary(pic);
			// 回傳給前端
			respObj.addProperty("resPic", picBase64);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			respObj.addProperty("errMsg", "系統錯誤");
		}

		response.getWriter().append(gson.toJson(respObj));
	
	
	
	
	
	
	
	
	
	
	
	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
