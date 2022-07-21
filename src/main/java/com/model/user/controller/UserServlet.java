package com.model.user.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.model.user.UserVO;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		UserVO userVO = gson.fromJson(request.getReader(), UserVO.class);
		System.out.println(userVO.getUserName());
		
		
//		try (BufferedReader br = request.getReader()){
//			String line ;
//			while ((line = br.readLine()) != null) {
//				System.out.println(line);
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
	
	}

}
