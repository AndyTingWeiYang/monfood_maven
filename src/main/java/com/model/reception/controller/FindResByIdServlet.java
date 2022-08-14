package com.model.reception.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.model.product.util.IntTypeAdapter;
import com.model.reception.service.ResService;
import com.model.reception.service.impl.ResServiceImpl;
import com.model.res.ResVO;

@WebServlet("/admin-res-reception/FindResByIdServlet")
public class FindResByIdServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	Gson gson;

	private ResService resSrc;

	public FindResByIdServlet() {
		this.gson = new GsonBuilder().registerTypeAdapter(int.class, new IntTypeAdapter())
				.registerTypeAdapter(Integer.class, new IntTypeAdapter()).create();

		this.resSrc = new ResServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 設定網頁 UTF-8
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 取得 Session 店家 resID
		HttpSession session = request.getSession(false);
		Integer resID = (Integer) session.getAttribute("resID");

		// 調用 ResService 取得 ResVO
		ResVO resVO = resSrc.findByPrimaryKey(resID);
		
		// TODO: 待 Table 修正 先寫死測試OK
//		resVO.setBzWeekTime(1);
		
		// 先寫死測試OK 但目前只能12小時制顯示 待解決
//		resVO.setBzOpenHours(java.sql.Time.valueOf("11:11:11"));
//		resVO.setBzCloseHours(java.sql.Time.valueOf("22:22:22"));
		
		// 回傳至頁面
		// 回傳資料結構為 json 物件需要有一個櫃子裝回傳的資料(JsonObject)
		JsonObject jsonObj = new JsonObject();
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		String bzOpenHours = sdf.format(resVO.getBzOpenHours());
//		System.out.println(bzOpenHours);
		
		String bzCloseHours = sdf.format(resVO.getBzCloseHours());
//		System.out.println(bzCloseHours);


		// 第一個參數為 key，第二個參數為 value(json 字串) => 將資料塞進櫃子裏面
//		jsonObj.addProperty("key", "string");
		
		jsonObj.addProperty("bzOpenHours",  bzOpenHours);

		jsonObj.addProperty("bzCloseHours",  bzCloseHours);
		
		jsonObj.add("resVO", gson.toJsonTree(resVO));

		// 將 json 寫回給頁面接收
		PrintWriter out = response.getWriter();
		out.write(gson.toJson(jsonObj));
	}

}
