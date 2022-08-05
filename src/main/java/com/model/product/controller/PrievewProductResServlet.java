package com.model.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.model.product.service.ProductService;
import com.model.product.service.impl.ProductServiceImpl;

@WebServlet("/resprofile/PrievewProductResServlet")
public class PrievewProductResServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;

	public PrievewProductResServlet() {
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
		ajaxSendResInfo(request, response);

	}

	private void formSubmitResInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 抓取畫面參數
		HttpSession session = request.getSession(false);
		Integer resID = (Integer) session.getAttribute("resID");

		// 進入DB撈資料
		Map<String, Object> resMap = productService.findResInfo(resID);
		String resCategoryName = MapUtils.getString(resMap, "resCategoryName");

		// 設定資料
		request.setAttribute("resCategoryName", resCategoryName);
		RequestDispatcher rd = request.getRequestDispatcher("resprofile-new-product.jsp");
		rd.forward(request, response);
	}

	private void ajaxSendResInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();

		HttpSession session = request.getSession(false);

		Integer resID = (Integer) session.getAttribute("resID");
		JsonObject jsonObj = new JsonObject();
		Map<String, Object> resMap = productService.findResInfo(resID);
		String resCategoryName = MapUtils.getString(resMap, "resCategoryName");
		jsonObj.addProperty("resCategoryName", resCategoryName);

		String jsonStr = gson.toJson(jsonObj);

		PrintWriter out = response.getWriter();
		out.write(jsonStr);

	}
}
