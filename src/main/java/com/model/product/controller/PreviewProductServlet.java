package com.model.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.model.product.ProductVo;
import com.model.product.service.ProductService;
import com.model.product.service.impl.ProductServiceImpl;

@MultipartConfig
@WebServlet("/resprofile/PreviewProductServlet")
public class PreviewProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;

	public PreviewProductServlet() {
		this.productService = new ProductServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 接取前端畫面的參數
		String productID = request.getParameter("productID");
		// 藉由參數去抓取一筆的所有資料 (DAO內入資料)
		Map<String, Object> productMap = productService.findByID(productID);
		// 設定參數，DB 抓取到的資料暫存到下一頁
		request.setAttribute("productMap", productMap);
		// 將請求及回應回傳到畫面上
		RequestDispatcher rd = request.getRequestDispatcher("/resprofile/resprofile-update-product.jsp");
		rd.forward(request, response);

	}


}
