package com.model.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.model.user.UserVO;
import com.model.user.service.UserService;
import com.model.user.serviceImpl.UserServiceImpl;

@WebServlet("/UserProfileServlet")
public class UserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userID");
		
		try {

			UserVO userVO = gson.fromJson(request.getReader(), UserVO.class);
			userVO.setUserId(userId);
			UserService service = new UserServiceImpl();
			final UserVO result = service.getOneByUserId(userVO);

			if (result == null) {
				respObj.addProperty("errMsg", "無此會員");
				response.getWriter().append(gson.toJson(respObj));
				return;
			}
			
			if(result.getProfilePic() == null) {
				respObj.add("userProfile", gson.toJsonTree(result));
				response.getWriter().append(gson.toJson(respObj));
				return;
			}

			respObj.add("userProfile", gson.toJsonTree(result));
			// 將DB圖片編碼成base64
			byte[] pic = result.getProfilePic();
			String picBase64 = DatatypeConverter.printBase64Binary(pic);
			// 回傳給前端
			respObj.addProperty("profilePic", picBase64);
			
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			respObj.addProperty("errMsg", "系統錯誤");
		}

		response.getWriter().append(gson.toJson(respObj));
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
