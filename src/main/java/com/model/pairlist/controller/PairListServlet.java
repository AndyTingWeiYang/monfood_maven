package com.model.pairlist.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.model.order.OrderVO;
import com.model.pairlist.PairListVo;
import com.model.pairlist.dao.PairListDao;
import com.model.pairlist.dao.impl.PairListDaoImpl;
import com.model.pairlist.service.PairListService;
import com.model.pairlist.service.impl.PairListServiceImpl;
import com.model.user.UserVO;


@WebServlet("/PairListServlet")
public class PairListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		Integer useraId = (Integer) httpSession.getAttribute("userID");
		System.out.println(useraId);
		Gson gson = new Gson();
		resp.setContentType("application/json;charset=UTF-8");
		resp.addHeader("Access-Control-Allow-Origin", "*");
		PairListService service = new PairListServiceImpl();
		PrintWriter out = resp.getWriter();

		out.print(gson.toJson(service.findFriends(useraId)));

}
	
}
