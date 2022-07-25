package com.model.pairlist.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		//useraId待改為從登入session獲取的id參數(先寫死) [to be revised]
		Integer useraId =1;
		Gson gson = new Gson();
//		PairListVo pairVO = gson.fromJson(req.getReader(), PairListVo.class);
		resp.setContentType("application/json;charset=UTF-8");//回傳json格式並使用UTF-8編碼
		resp.addHeader("Access-Control-Allow-Origin", "*");//*為允許可以跨域連線進自己設計的本機連線
		PairListService service = new PairListServiceImpl();
		PrintWriter out = resp.getWriter();
		out.print(gson.toJson(service.findFriends(useraId)));

}
	
}
