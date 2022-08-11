package com.model.reception.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.order.OrderVO;
import com.model.product.util.IntTypeAdapter;
import com.model.reception.service.ResService;
import com.model.reception.service.impl.ResServiceImpl;


@WebServlet("/admin-res-reception/UpdateOrderStatusServlet")
public class UpdateOrderStatusServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	Gson gson;
	private ResService resSrc;

    public UpdateOrderStatusServlet() {
    	this.resSrc = new ResServiceImpl();
    	this.gson = new GsonBuilder().registerTypeAdapter(int.class, new IntTypeAdapter())
				.registerTypeAdapter(Integer.class, new IntTypeAdapter()).create();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");//告知[伺服器]用哪種編碼去解析
		response.setContentType("text/html;charset=UTF-8");//告知[瀏覽器]送出的內容與文字編碼
		
//		OrderVO orderVO = new OrderVO();
		HttpSession session = request.getSession(false);
		Integer orderID = (Integer) session.getAttribute("orderID");
		Map<String, Object> dataMap = new HashedMap<>();
		
		dataMap.put("orderStatus", request.getParameter("orderStatus"));
		System.out.println(dataMap);//看拿到什麼
		
		resSrc.updateOrderStatus(dataMap);
		
	}

}
