package com.model.product.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;

import com.model.product.ProductVo;
import com.model.product.service.ProductService;
import com.model.product.service.impl.ProductServiceImpl;
import com.model.product.util.ErrorMsgException;

@WebServlet("/resprofile/ProductListServlet")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductService productService;

	public ProductListServlet() {
		this.productService = new ProductServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processListFindAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processListFindAll(request, response);
	}

	private void processListFindAll(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charcet=UTF-8");

		try {
			HttpSession session = request.getSession(false);
			Integer resID = (Integer) session.getAttribute("resID");
			Map<String, Object> dataMap = requestToMap(request);
			dataMap.put("resID", resID);

			List<Map<String, Object>> productList = productService.findAll(dataMap);

			request.setAttribute("productList", productList);
			RequestDispatcher rd = request.getRequestDispatcher("resprofile-product-list.jsp");
			rd.forward(request, response);
		} catch (ErrorMsgException eme) {
			Map<String, String> errorMsg = eme.getErrorMsg();

			request.setAttribute("errorMsg", errorMsg);
			RequestDispatcher rd = request.getRequestDispatcher("resprofile-product-list.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

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
