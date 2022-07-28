package com.model.product.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.product.ProductVO;
import com.model.product.service.ProductService;
import com.model.product.service.impl.ProductServiceImpl;

@WebServlet("/resprofile/ProductHistoryServlet")
public class ProductHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductHistoryServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		proccessProductFindAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		proccessProductFindAll(request, response);
	}

	/**
	 * 初始化查詢 Controller
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void proccessProductFindAll(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

//		String orderId = request.getParameter("orderId");
//		String userId = request.getParameter("userId");
		Map<String, Object> dataMap = requestToMap(request);

		// 呼叫 Service 業務邏輯
		ProductService productService = new ProductServiceImpl();
		List<ProductVO> productList = productService.findAll(dataMap);

		// 將資料帶回 jsp 頁面
		// 利用 forward 將資料帶回頁面
		request.setAttribute("productList", productList);
//		RequestDispatcher rd = request.getRequestDispatcher("/resprofile/resprofile-product-history.jsp");
//		rd.forward(request, response);

		//TODO: request.getContextPath() => http://localhost:8080/monfood_maven/
		//TODO: request.getServletPath() => /ProductServlet
	}

	private Map<String, Object> requestToMap(HttpServletRequest request) {
		Enumeration<String> paramerNames = request.getParameterNames();
		//  資料集結在集合內
		Map<String, Object> dataMap = new HashMap<>();
		
		// 迭代整個集合取出欄位名稱
		while (paramerNames.hasMoreElements()) {
			String key = (String) paramerNames.nextElement();
			dataMap.put(key, request.getParameter(key));
		}

		return dataMap;
	}

}
