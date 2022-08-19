package com.model.administrator.controller;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.model.administrator.service.AdminService;
import com.model.administrator.service.AdminServiceImpl;
import com.model.del.DelService;
import com.model.del.DelVO;
import com.model.order.OrderVO;

/**
 * Servlet implementation class AdminDelGetAll
 */
@WebServlet("/AdminGetOrder")
public class AdminGetOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();
		OrderVO bean = new OrderVO();
		OrderVO orderVO = gson.fromJson(req.getReader(), OrderVO.class);
		
		AdminService service = new AdminServiceImpl();
		bean = service.getOrderByID(orderVO.getOrderId());
		if(bean.getOrderId()==null) {
			System.out.println("輸入的帳密找不到資料所以失敗");
			respObj.addProperty("loginStatus", "登入失敗請重新嘗試");
//			HttpSession session = req.getSession();
//			session.setAttribute("errors", respObj);
			resp.getWriter().append(gson.toJson(respObj));
		}
		else {
			resp.getWriter().append(gson.toJson(bean));
		}
		
	}
		
		

}


