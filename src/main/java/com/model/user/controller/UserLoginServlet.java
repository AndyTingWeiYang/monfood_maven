package com.model.user.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.model.user.UserVO;
import com.model.user.service.UserService;
import com.model.user.serviceImpl.UserServiceImpl;

@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Gson gson = new Gson();
		JsonObject respObj = new JsonObject();

		try {
			UserVO fromUserKeyInVO = gson.fromJson(request.getReader(), UserVO.class);

			UserService service = new UserServiceImpl();
			final UserVO loginResult = service.userLogin(fromUserKeyInVO);

			String fukiAccount = fromUserKeyInVO.getUserAccount();
			String fukiPassword = fromUserKeyInVO.getUserPassword();

			if (loginResult == null) {
				respObj.addProperty("None", "查無帳號");
				response.getWriter().append(gson.toJson(respObj));
				return;
			} else if (loginResult.getUserAccount().equals(fukiAccount)
					&& loginResult.getUserPassword().equals(fukiPassword)) {
				respObj.addProperty("LoginSuceesfully", "登入成功");
				response.getWriter().append(gson.toJson(respObj));

				// HttpSession介面
				HttpSession session = request.getSession();
				session.setAttribute("isuserLogin", "true"); // true代表使用者已登入過，延續登入狀態只看這Attr
				session.setAttribute("userID", loginResult.getUserId());
				session.setAttribute("userName", loginResult.getUserName());
				session.setAttribute("userAccount", loginResult.getUserAccount());
				session.setAttribute("monsLevel", loginResult.getMonsLevel());

				// cookie
				Cookie LoginCookie = new Cookie("SessionID", session.getId());// 新建Cookie
				LoginCookie.setMaxAge(Integer.MAX_VALUE);// 設定生命週期為MAX_VALUE
				response.addCookie(LoginCookie); // 輸出到客戶端

				// 已經將會員資料交給session了。如果要延續登入狀態只需要在下一頁確認cookie裡面的session有沒有一個key是isuserLogin,而值是true即可

				System.out.println("登入成功!!");

			} else if (loginResult.getUserAccount().equals(fukiAccount)
					&& !(loginResult.getUserPassword().equals(fukiPassword))) {
				respObj.addProperty("ErrorPassword", "密碼有誤，請重新輸入");
				response.getWriter().append(gson.toJson(respObj));
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
			respObj.addProperty("errMsg", "系統錯誤");

		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
