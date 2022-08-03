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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.model.product.service.ProductService;
import com.model.product.service.impl.ProductServiceImpl;

@MultipartConfig
@WebServlet("/resprofile/NewProductServlet")
public class NewProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductService productService;
	
	public NewProductServlet() {
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
		
		Map<String, Object> reqMap = requestToMap(request);
		Part productPic = request.getPart("productPic");
		
		// 將圖片位元組讀取至 byte[]
		InputStream is = productPic.getInputStream();
		byte[] buffer = new byte[is.available()];
		is.read(buffer);
		reqMap.put("productPic", buffer);
		
		// 取得會員 ID
		HttpSession session = request.getSession(false);
		Integer resID=(Integer) session.getAttribute("resID");
		reqMap.put("resID", resID);
		System.out.println(reqMap);
		
		boolean result = productService.insert(reqMap);
		if(result) {
			
			response.sendRedirect(request.getContextPath() + "/resprofile/resprofile-new-product.jsp");
		
		} else {
			System.out.println("新增產品失敗");
			RequestDispatcher rd = request.getRequestDispatcher("/resprofile/resprofile-new-product.jsp");
			rd.forward(request, response);
		}
	}

	private Map<String, Object> requestToMap(HttpServletRequest request) {
		Enumeration<String> listNames = request.getParameterNames();
		Map<String, Object> listMap = new HashMap<>();
		while (listNames.hasMoreElements()) {
			String name = listNames.nextElement();
			Object nameVal = request.getParameter(name);
			listMap.put(name, nameVal);
		}
		return listMap;
	}

}
