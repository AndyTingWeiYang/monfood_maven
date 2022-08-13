package com.model.res.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.res.ResVO;
import com.model.res.service.ResService;
import com.model.res.service.impl.ResServiceImpl;

@WebServlet("/resprofile/ResPhotoPreviewServlet")
public class ResPhotoPreviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResService resService;

	public ResPhotoPreviewServlet() {
		this.resService = new ResServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		ServletContext sc = this.getServletContext();
		System.out.println(sc);
		InputStream is = null;
		Integer resID = Integer.parseInt(request.getParameter("resID"));

		ResVO resVo = resService.selectByResId(resID);
		byte[] resPic = resVo.getResPic();
		OutputStream os = response.getOutputStream();

		if (resPic == null || resPic.length == 0) {
			is = sc.getResourceAsStream("/images/user.png");
			int read = 0;
			byte[] image = new byte[2048];
			while ((read = is.read(image)) != -1) {
				os.write(image);
			}
		} else {
			os.write(resPic);
		}

		os.flush();
		os.close();

	}

}
