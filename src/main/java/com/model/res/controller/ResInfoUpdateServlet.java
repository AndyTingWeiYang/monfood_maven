package com.model.res.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.model.product.util.ErrorMsgException;
import com.model.product.util.IntTypeAdapter;
import com.model.res.ResDto;
import com.model.res.ResVO;
import com.model.res.service.ResService;
import com.model.res.service.impl.ResServiceImpl;

@MultipartConfig
@WebServlet("/resprofile/ResInfoUpdateServlet")
public class ResInfoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson;
	ResService resService;

	public ResInfoUpdateServlet() {
		this.gson = new GsonBuilder().registerTypeAdapter(int.class, new IntTypeAdapter())
				.registerTypeAdapter(Integer.class, new IntTypeAdapter()).create();
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
		JsonObject resp = new JsonObject();
		PrintWriter out = response.getWriter();
		try {
			Map<String, Object> dataMap = resToMap(request);
			Part resFile = request.getPart("resFile");
			// 取得 session 物件中的會員編號
			HttpSession session = request.getSession(false);
			Integer resID = (Integer) session.getAttribute("resID");

			byte[] buffer = new byte[0];
			if (StringUtils.isNotBlank(resFile.getSubmittedFileName())) {
				InputStream is = resFile.getInputStream();
				buffer = new byte[is.available()];
				is.read(buffer);

				dataMap.put("resFile", buffer);
			} else {
				ResVO res = resService.selectByResId(resID);
				dataMap.put("resFile", res.getResPic());
			}

			dataMap.put("resID", resID);
			System.out.println(dataMap);
			String dataMapStr = gson.toJson(dataMap);
			ResDto resDto = gson.fromJson(dataMapStr, ResDto.class);
			Map<String, Object> resMap;
			resMap = resService.updateResInfo(resDto);

			resp.add("resMap", gson.toJsonTree(resMap));
			resp.addProperty("resID", resID);

			out.write(gson.toJson(resp));

		} catch (ErrorMsgException eme) {
			Map<String, String> errorMsg = eme.getErrorMsg();
			resp.add("errorMsg", gson.toJsonTree(errorMsg));
			out.write(gson.toJson(resp));

			eme.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Map<String, Object> resToMap(HttpServletRequest request) {
		Enumeration<String> formList = request.getParameterNames();
		Map<String, Object> dataMap = new HashMap<>();

		while (formList.hasMoreElements()) {
			String nameKey = formList.nextElement();
			String nameVal = request.getParameter(nameKey);
			dataMap.put(nameKey, nameVal);
		}

		return dataMap;
	}

}
