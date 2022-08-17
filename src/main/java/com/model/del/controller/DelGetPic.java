package com.model.del.controller;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.model.del.DelService;
import com.model.del.DelVO;
import com.model.del.service.DelServiceImpl;

/**
 * Servlet implementation class AdminDelGetAll
 */
@WebServlet("/DelGetPic")
public class DelGetPic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
		JsonObject respObj = new JsonObject();
		DelVO editBean = new DelVO();
//登入狀態下
		HttpSession session = req.getSession();
		DelVO loginDelVO = (DelVO) session.getAttribute("del");
		
//去DB拿新圖
		com.model.del.service.DelService delService = new DelServiceImpl();
		DelVO resultBean = delService.getOnebyID(loginDelVO.getDelID());
		
//將證件照取出回傳頁面
		if(null == resultBean.getDelIDPhoto()) {
			resp.getWriter().append(gson.toJson("請盡速上傳文件"));
		} else {

			String IDString = DatatypeConverter.printBase64Binary(resultBean.getDelIDPhoto());
			String carString = DatatypeConverter.printBase64Binary(resultBean.getCarLicense());
			String driveString = DatatypeConverter.printBase64Binary(resultBean.getDriverLicense());
			String criminalString = DatatypeConverter.printBase64Binary(resultBean.getCriminalRecord());
			String insuranceString = DatatypeConverter.printBase64Binary(resultBean.getInsurance());

			String[] pics = new String[5];
		
			pics[0]=IDString;
			pics[1]=carString;
			pics[2]=driveString;
			pics[3]=criminalString;
			pics[4]=insuranceString;
		
			resp.getWriter().append(gson.toJson(pics));
		}
		
		}
	}


