package com.model.del.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.model.del.DelService;
import com.model.del.DelVO;
import com.model.res.ResVO;
import com.model.res.service.ResService;
import com.model.res.service.impl.ResServiceImpl;

/**
 * Servlet implementation class AdminDelGetAll
 */
@WebServlet("/IndexGetByZipcode")
public class IndexGetByZipcode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
//接收前端傳入資料
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		JsonObject jsonObject = gson.fromJson(req.getReader(), JsonObject.class);
		JsonElement zipcodeJsonObject = jsonObject.get("zip_code");
		String zipString = String.valueOf(zipcodeJsonObject);
		String zipcode = zipString.replace("\"", "");
//呼叫service
		List<ResVO> resResult = new ArrayList<ResVO>();
		ResService service = new ResServiceImpl();
		resResult = service.getByZipcode(zipcode);
//		JsonObject respObj = new JsonObject();
//存結果在session
		HttpSession session = req.getSession();
		session.setAttribute("resZipcode", resResult);

//傳結果回前端
		resp.getWriter().append(gson.toJson(resResult));
		
		}
		
		

	}


