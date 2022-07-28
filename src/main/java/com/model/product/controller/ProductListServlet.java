package com.model.product.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.HashedMap;

import com.model.product.ProductVO;
import com.model.product.service.ProductService;
import com.model.product.service.impl.ProductServiceImpl;

@WebServlet("/resprofile/ProductListServlet")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductService productListService;
	
	public ProductListServlet() {
		this.productListService = new ProductServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processListFindAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processListFindAll(request, response);
	}

	private void processListFindAll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charcet=UTF-8");

		Map<String, Object> dataMap = requestToMap(request);
		
		List<ProductVO> productList = productListService.findAll(dataMap);

		
		request.setAttribute("productList", productList);
		RequestDispatcher rd = request.getRequestDispatcher("resprofile-product-list.jsp");
		rd.forward(request, response);
	}

	private Map<String, Object> requestToMap(HttpServletRequest request) {
		Enumeration<String> parameterNames = request.getParameterNames();
		Map<String, Object> dataMap = new HashedMap<>();
		while (parameterNames.hasMoreElements()) {
			String key = (String) parameterNames.nextElement();
			dataMap.put(key, request.getParameter(key));
		}

		return dataMap;
	}

}
