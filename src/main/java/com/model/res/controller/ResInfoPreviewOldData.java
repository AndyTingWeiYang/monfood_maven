package com.model.res.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.res.ResVO;
import com.model.res.service.ResService;
import com.model.res.service.impl.ResServiceImpl;

@WebServlet("/resprofile/ResInfoPreviewOldData")
public class ResInfoPreviewOldData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ResService resService;

	public ResInfoPreviewOldData() {
		resService = new ResServiceImpl();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(false);
		Integer resID = (Integer) session.getAttribute("resID");
		ResVO resVO = resService.selectByResId(resID);
		request.setAttribute("resVO", resVO);
		RequestDispatcher rd = request.getRequestDispatcher("resprofile-info.jsp");
		rd.forward(request, response);
	}

}
