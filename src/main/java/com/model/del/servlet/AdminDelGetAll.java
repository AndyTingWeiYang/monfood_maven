package com.model.del.servlet;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.model.del.DelService;
import com.model.del.DelVO;

/**
 * Servlet implementation class AdminDelGetAll
 */
@WebServlet("/AdminDelGetAll")
public class AdminDelGetAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();
		List<DelVO> datas = null;

		DelVO delVO = gson.fromJson(req.getReader(), DelVO.class);
		DelService service = new DelService();
		datas = service.getAll();
		
		resp.getWriter().append(gson.toJson(datas));

	}

}
