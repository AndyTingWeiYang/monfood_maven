package com.model.product.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.model.product.service.ProductService;
import com.model.product.service.impl.ProductServiceImpl;


@WebServlet("/GetAllPdtServlet")
public class GetAllPdtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();

		try {
			ProductService service = new ProductServiceImpl();
			List<Map<String, Object>> pdtList = service.getAllPdt(Integer.parseInt(request.getParameter("resId")));

			// add the list into json format
			respObj.add("pdtList", gson.toJsonTree(pdtList));
		} catch (Exception e) {
			e.printStackTrace();
			respObj.addProperty("errMsg", "系統錯誤");
		}

		// return data
		response.getWriter().append(gson.toJson(respObj));
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
}
