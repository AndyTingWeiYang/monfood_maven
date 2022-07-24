package com.model.promotelist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import com.model.promotelist.PromoteListVO;
import com.model.promotelist.service.PromoteListService;
import com.model.promotelist.service.Impl.PromoteListServiceImpl;


@WebServlet("/PromoteListAllServlet")
public class PromoteListAllServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");		
		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();
		
		try {
			PromoteListService promoteListService = new PromoteListServiceImpl();
			List<PromoteListVO> list = promoteListService.adminFindPromoteListAll();
			
			// add the list into json format
			respObj.add("PromoteList", gson.toJsonTree(list));
		} catch (Exception e) {
			e.printStackTrace();
			respObj.addProperty("errMsg", "系統錯誤");
		}
	
	
	
//	  分隔線
	
//	public void doGet(HttpServletRequest req, HttpServletResponse res)
//            throws ServletException, IOException {
//		doPost(req, res);
//	}

	
//		分隔線	
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
//			throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; charset=UTF-8");	
//		Gson gson = new Gson();
//		JsonObject respObj = new JsonObject();
//		List<PromoteListVO> datas = null;
//		
//		PromoteListVO promoteListVO = gson.fromJson(request.getReader(), PromoteListVO.class);
//		PromoteListServiceImpl prmotListServiceImpl = new PromoteListServiceImpl();
//		datas = prmotListServiceImpl.adminFindPromoteListAll();

//		分隔線
		
//		try {
//			PromoteListService service = new PromoteListServiceImpl();
//			List<PromoteListVO> list = service.adminFindPromoteListAll();
//			
//			// add the list into json format
//			respObj.add("PromoteList", gson.toJsonTree(list));
//		} catch (Exception e) {
//			e.printStackTrace();
//			respObj.addProperty("errMsg", "系統錯誤");
//		}
		
		// return data
		response.getWriter().append(gson.toJson(respObj));
	}
	
	
	
}



	

		







