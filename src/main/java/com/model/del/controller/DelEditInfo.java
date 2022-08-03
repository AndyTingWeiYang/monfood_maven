package com.model.del.controller;

import java.awt.color.ProfileDataException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Base64;
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
import com.model.del.service.DelServiceImpl;

/**
 * Servlet implementation class AdminDelGetAll
 */
@WebServlet("/DelEditInfo")
public class DelEditInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		JsonObject respObj = new JsonObject();
		DelVO delVO = new DelVO();
//承接傳入資料
		DelVO editDelVO = gson.fromJson(req.getReader(), DelVO.class); 
		
//登入狀態下取出登入者資料
		HttpSession session = req.getSession();
		DelVO loginDelVO = (DelVO) session.getAttribute("del");
		
		
		delVO.setDelID(loginDelVO.getDelID());
		delVO.setDelName(loginDelVO.getDelName());
		delVO.setDelAccount(editDelVO.getDelAccount());
		delVO.setDelPassword(loginDelVO.getDelPassword());
		delVO.setDelTel(editDelVO.getDelTel());
		delVO.setDelBirthday(loginDelVO.getDelBirthday());
		delVO.setPlatenumber(loginDelVO.getPlatenumber());
		delVO.setStatus(loginDelVO.getStatus());
		delVO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		delVO.setDelAccountName(loginDelVO.getDelAccountName());
		delVO.setDelBankname(loginDelVO.getDelBankname());
		delVO.setDelBankcode(loginDelVO.getDelBankcode());
		delVO.setDelBankaccount(loginDelVO.getDelBankaccount());
		
		
		com.model.del.service.DelService service = new DelServiceImpl();
		DelVO result = service.updateNoPic(delVO);
		
		resp.getWriter().append(gson.toJson(result));
//		System.out.println(result);
		

	}

}
