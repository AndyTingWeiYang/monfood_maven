package com.model.del.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.internal.DDLFormatterImpl;
import org.jboss.jandex.Main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.model.del.DelService;
import com.model.del.DelVO;
import com.model.del.service.DelServiceImpl;
import com.model.order.OrderVO;

/**
 * Servlet implementation class AdminDelGetAll
 */
@WebServlet("/Delorderdetail")
public class DelOrderDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
//		
		String sd = req.getParameter("startDate");
		String ed = req.getParameter("endDate");
		Integer delID = Integer.valueOf(req.getParameter("delID"));
		
		Timestamp sd1 = Timestamp.valueOf(sd);
		Timestamp ed1 = Timestamp.valueOf(ed);
		
		System.out.println(sd);
		System.out.println(ed);
		System.out.println(sd1);
		System.out.println(ed1);
		
//		com.model.del.service.DelService service = new DelServiceImpl();
//		OrderVO orderVO = service.getDelRecord(sd1, ed1, delID);
//		System.out.println(orderVO);
//		System.out.println(orderVO.getDelCost());
//		System.out.println(orderVO.getDelComment());
		
//		String result = String.valueOf(orderVO.getDelCost()) +"/" + orderVO.getDelComment();
//		
//		PrintWriter out = resp.getWriter();
//		out.print(result);
//		out.flush();
//		out.close();
		
		
//		Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
//		JsonObject respObj = new JsonObject();
//
//		String startDateString = gson.fromJson(req.getParameter("startDate"), String.class);
////		DelVO delVO = gson.fromJson(req.getReader(), DelVO.class);
//		System.out.println(startDateString);
////		DelService service = new DelService();
////		DelVO result = service.updateDel(delVO);
//		resp.getWriter().append(gson.toJson(respObj));
		
		
		
		

	}

}
