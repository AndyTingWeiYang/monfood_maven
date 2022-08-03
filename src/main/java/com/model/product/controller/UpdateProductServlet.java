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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.product.ProductVo;
import com.model.product.service.ProductService;
import com.model.product.service.impl.ProductServiceImpl;
import com.model.product.util.IntTypeAdapter;

@MultipartConfig
@WebServlet("/resprofile/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;
	private Gson gson;

	public UpdateProductServlet() {
		this.productService = new ProductServiceImpl();
		// Gson NumberException handle
		this.gson = new GsonBuilder().registerTypeAdapter(int.class, new IntTypeAdapter())
				.registerTypeAdapter(Integer.class, new IntTypeAdapter()).create();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;chart=UTF-8");

		// 取得預覽後的整筆資料，要修改及更新
		// add update request to map, get key & val
		Map<String, Object> reqMap = requestToMap(request);

		// 取得圖片
		Part productPic = request.getPart("productPic");
		InputStream is = productPic.getInputStream();
		// 將圖片位元組讀轉至 byte[]
		byte[] buffer = new byte[is.available()];
		is.read(buffer);
		reqMap.put("productPic", buffer);
		HttpSession session = request.getSession(false);
		Integer resID = (Integer) session.getAttribute("resID");
		reqMap.put("resID", resID);

		// 將 Map 物件變成 JSON 字串做序列化
		String dataJsonStr = gson.toJson(reqMap);
		// 將字串轉為 VO
		ProductVo productVO = gson.fromJson(dataJsonStr, ProductVo.class);

		// 判斷資料是否傳遞
		boolean result = productService.update(productVO);

		if (result) {
			// 如果有資料就傳資料到 update 頁面
			response.sendRedirect(request.getContextPath() + "/resprofile/resprofile-product-list.jsp");

		} else {
			// 如果沒有資料，就導回原來更新頁面
			request.setAttribute("product", productVO);
			request.setAttribute("errMsg", "error: update fail");
			RequestDispatcher rd = request.getRequestDispatcher("/resprofile/resprofile-update-product.jsp");
			rd.forward(request, response);
		}

	}

	private Map<String, Object> requestToMap(HttpServletRequest request) {
		// to get columns names (keys)
		Enumeration<String> listNames = request.getParameterNames();
		// add 1 Map, Map's value can be any type
		Map<String, Object> listMap = new HashMap<String, Object>();

		while (listNames.hasMoreElements()) {
			// get key from enumeration
			String nameKey = listNames.nextElement();
			// use request key to get value
			Object nameVal = request.getParameter(nameKey);

			// put key & value to Map
			listMap.put(nameKey, nameVal);
		}

		return listMap;
	}

}
