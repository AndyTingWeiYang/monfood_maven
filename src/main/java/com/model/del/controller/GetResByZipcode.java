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
@WebServlet("/GetResByZipcode")
public class GetResByZipcode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
//取結果from session
		Gson gson = new Gson();
		HttpSession session = req.getSession();
		List<ResVO> resList = (List<ResVO>) session.getAttribute("resZipcode");

//傳結果回前端
		resp.getWriter().append(gson.toJson(resList));
		
		}
		
		

	}


