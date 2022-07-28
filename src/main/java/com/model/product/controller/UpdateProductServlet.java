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

import com.model.product.ProductVO;
import com.model.product.service.ProductService;
import com.model.product.service.impl.ProductServiceImpl;

@MultipartConfig
@WebServlet("/resprofile/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;

	public UpdateProductServlet() {
		this.productService = new ProductServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;chart=UTF-8");
		// 接取前端畫面的參數
		String productID = request.getParameter("productID");
		// 藉由參數去抓取一筆的所有資料 (補上DAO)
		ProductVO product = productService.findByID(productID);
		request.setAttribute("product", product);

		RequestDispatcher rd = request.getRequestDispatcher("/resprofile/resprofile-update-product.jsp");
		rd.forward(request, response);
		
	}

	private Map<String, Object> requestMap(HttpServletRequest request) {
		// get form colunm names
		Enumeration<String> listNames = request.getParameterNames();
		Map<String, Object> listMap = new HashMap<>();

		// 將表單欄位及內容一個一個取出
		while (listNames.hasMoreElements()) {
			// get key ( = column name) from listNames
			String name = listNames.nextElement();

			// use key to get keyValue (= context), Map type can be anything, so use Object
			// type
			Object nameVal = request.getParameter(name);

			// add column and context put to Map
			listMap.put(name, nameVal);
		}

		return listMap;
	}

}
