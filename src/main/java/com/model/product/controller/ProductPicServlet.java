package com.model.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.product.ProductVo;
import com.model.product.service.ProductService;
import com.model.product.service.impl.ProductServiceImpl;

@WebServlet("/resprofile/ProductPicServlet")
public class ProductPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductService productListService;

	public ProductPicServlet() {
		this.productListService = new ProductServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext sc = this.getServletContext();
		InputStream is = null;
		String productId = request.getParameter("productID");
		ProductVo productVO = productListService.findPic(productId);

		// 取出圖片->讀圖片
		byte[] pic = productVO.getProductPic();
		OutputStream out = response.getOutputStream();

		if (pic == null || pic.length == 0) {
			is = sc.getResourceAsStream("/images/food.png");
			int read = 0;
			byte[] image = new byte[2048];
			while ((read = is.read(image)) != -1) {
				out.write(image);
			}
		} else {
			out.write(pic);
		}
		out.flush();
		out.close();
	}

}
