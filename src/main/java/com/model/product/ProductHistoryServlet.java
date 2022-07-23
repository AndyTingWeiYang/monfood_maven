package com.model.product;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.product.service.ProductHistoryService;
import com.model.product.service.impl.ProductHistoryServiceImpl;

@WebServlet("/ProductHistoryServlet")
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
		Map<String, String> dataMap = requestToMap(request);

		// 呼叫 Service 業務邏輯
		ProductHistoryService productService = new ProductHistoryServiceImpl();
		List<ProductVO> productList = productService.findAll(dataMap);

		// 將資料帶回 jsp 頁面
		// 利用 forward 將資料帶回頁面
		request.setAttribute("productList", productList);
//		RequestDispatcher rd = request.getRequestDispatcher("/store/resprofile-product-list.jsp");
//		rd.forward(request, response);

		//TODO: request.getContextPath() => http://localhost:8080/monfood_maven/
		//TODO: request.getServletPath() => /ProductServlet
	}

	private Map<String, String> requestToMap(HttpServletRequest request) {
		Enumeration<String> paramerNames = request.getParameterNames();
		//  資料集結在集合內
		Map<String, String> dataMap = new HashMap<>();
		
		// 迭代整個集合取出欄位名稱
		while (paramerNames.hasMoreElements()) {
			String key = (String) paramerNames.nextElement();
			dataMap.put(key, request.getParameter(key));
		}

		return dataMap;
	}

}
