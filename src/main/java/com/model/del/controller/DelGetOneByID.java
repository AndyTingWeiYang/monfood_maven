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
import com.google.gson.JsonObject;
import com.model.del.DelService;
import com.model.del.DelVO;
import com.model.res.ResVO;
import com.model.res.service.ResService;
import com.model.res.service.impl.ResServiceImpl;

/**
 * Servlet implementation class AdminDelGetAll
 */
@WebServlet("/DelGetOneByID")
public class DelGetOneByID extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
//		JsonObject respObj = new JsonObject();
		DelVO bean = null;
		
		HttpSession session = req.getSession();
		DelVO delVO = (DelVO) session.getAttribute("del");
		

//		System.out.println("before bean");
//		DelVO delVO = gson.fromJson(req.getReader(), DelVO.class);
//		System.out.println("after bean");
//		System.out.println(delVO.getDelID());
		DelService service = new DelService();
		bean = service.getOneDel(delVO.getDelID());
		
//		
//		System.out.println(bean);
		ResService resService = new ResServiceImpl();
		List<ResVO> resVos = resService.adminFindResAll();
		String resNameList = new String();
		for(ResVO resVO : resVos) {
			String resName = String.valueOf(resVO.getResName());
			resNameList += resName + "_";
		}
		bean.setDelName(bean.getDelName() + "_" + resNameList);
		resp.getWriter().append(gson.toJson(bean));
		
//		
		}
		
		

	}


