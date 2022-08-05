package com.model.del.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import org.hibernate.engine.jdbc.internal.DDLFormatterImpl;
import org.jboss.jandex.Main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.model.del.DelService;
import com.model.del.DelVO;
import com.model.del.service.DelServiceImpl;
import com.model.order.OrderVO;

/**
 * Servlet implementation class AdminDelGetAll
 */
@WebServlet("/DelOrderRecord")
public class DelOrderRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
//取出上傳的時間		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		JsonObject respObj = new JsonObject();
		DelVO editBean = new DelVO();
		
		System.out.println("beforeG");
		JsonObject json = gson.fromJson(req.getReader(), JsonObject.class);
		System.out.println("afterG");
		JsonElement sdElement = json.get("startDate");
		String sdString = String.valueOf(sdElement);
		String sdString2 = sdString.replace("\"", "") + " 00:00:00";
		System.out.println(sdString2);
		Timestamp startDate = Timestamp.valueOf(sdString2);          
		
		JsonElement edElement = json.get("endDate");
		String edString = String.valueOf(edElement);
		String edString2 = edString.replace("\"", "") + " 00:00:00";
		System.out.println(edString2);
		Timestamp endDate = Timestamp.valueOf(edString2);
		
		System.out.println(startDate);
		System.out.println(endDate);
		

//取出登入資訊
		HttpSession session = req.getSession();
		DelVO loginDelVO = (DelVO) session.getAttribute("del");
		
		
		com.model.del.service.DelService service = new DelServiceImpl();
		List result = new ArrayList();
		result = service.getDelRecord(startDate, endDate, loginDelVO.getDelID());
		respObj.add("result", gson.toJsonTree(result));
		
		
//		JsonObject respObj = new JsonObject();
//
//		String startDateString = gson.fromJson(req.getParameter("startDate"), String.class);
////		DelVO delVO = gson.fromJson(req.getReader(), DelVO.class);
		System.out.println(result);
////		DelService service = new DelService();
////		DelVO result = service.updateDel(delVO);
		resp.getWriter().append(gson.toJson(respObj));
		
		
		
		

	}

}
