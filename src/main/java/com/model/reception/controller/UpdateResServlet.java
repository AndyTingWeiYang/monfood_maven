package com.model.reception.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.model.product.util.IntTypeAdapter;
import com.model.reception.dao.ResDAO;
import com.model.reception.service.ResService;
import com.model.reception.service.impl.ResServiceImpl;
import com.model.res.ResVO;


@WebServlet("/admin-res-reception/UpdateResReceptionServlet")
public class UpdateResServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	Gson gson;
	private ResService resSrc;
	
	public UpdateResServlet() {
		this.gson = new GsonBuilder().registerTypeAdapter(int.class, new IntTypeAdapter())
				.registerTypeAdapter(Integer.class, new IntTypeAdapter()).create();
		this.resSrc = new ResServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//告知[伺服器]用哪種編碼去解析
		response.setContentType("text/html;charset=UTF-8");//告知[瀏覽器]送出的內容與文字編碼

		
		
		
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/

			ResVO resVO = new ResVO();
			// 取出商家登入的編號
			HttpSession session = request.getSession(false);
			Integer resID = (Integer) session.getAttribute("resID");
			
			// Servlet 接收畫面上的[所有]參數並且用 Map 收集起來
			//創建一個HasMap物件 名稱:dataMap 類別為Map
			Map<String, Object> dataMap = new HashMap<>();
			// put(K,V) = 新增key及value(值),如果已存在值,就會直接取代舊值
			//            (key , value)
			dataMap.put("resID", resID);
//			System.out.println(dataMap);//看拿到什麼
			
			//營業星期    因為這裡是String[] ,用getParameterValues = 取得陣列內所有value值
			dataMap.put("bzWeekTime", request.getParameterValues("bzWeekTime"));			
//			System.out.println(dataMap);//看拿到什麼
			Object values = dataMap.put("bzWeekTime", request.getParameterValues("bzWeekTime"));
			if( values == null) {
				request.setAttribute("errMsg", "error: 請選擇營業週期!請至少選擇一天");
//				String url = "/admin-res-reception/resReception-index.jsp";//這裡換成update頁面
//				response.sendRedirect(request.getContextPath() + url);
				RequestDispatcher rd = request.getRequestDispatcher("/admin-res-reception/resReception-index.jsp");
				rd.forward(request, response);
			}
			
			//開店時間
			dataMap.put("bzOpenHours", request.getParameter("bzOpenHours"));
//			System.out.println(dataMap);//看拿到什麼
			//關店時間
			dataMap.put("bzCloseHours", request.getParameter("bzCloseHours"));
//			System.out.println(dataMap);//看拿到什麼
			//營業模式
//			dataMap.put("shop_status", request.getParameter("shop_status"));
			
				
			//http://blog.tonycube.com/2012/03/gsonjavajson.html 解說來源網站
			//JSON:JavaScript Object Notation -> 用途是資料交換
			//XML 跟 JSON 都是做資料交換，可是當資料量很多時，XML 檔會很肥大，
			//而 JSON 是輕量級的資料交換格式，著重在檔案小速度快。
			//XML 仍是比 JSON 對於資料的描述較為嚴謹，但如果僅是做為資料傳遞的用途，
			//以 JSON 格式來儲存，檔案會比較小，解析速度也比 XML 快。
			
			//先copy測試 待說明理解
			//======= Object to JSON =======
//			String dataMapStr = gson.toJson(dataMap);//將JSON(dataMap)資料轉成字串
//			//======== JSON to Object ========
//			ResDAO resDao = gson.fromJson(dataMapStr, ResDAO.class);//將dataMapStr的資料轉成物件
//			
//			boolean result = resSrc.update(ResVO);//將resSvc轉成一個布林結果
//			
//			if (result) {
//				// 如果有資料就傳資料到 update 頁面
//				response.sendRedirect(request.getContextPath() + "/admin-res-reception/resReception-index.jsp");
//
//			} else {
//				// 如果沒有資料，就導回原來更新頁面
//				request.setAttribute("reception", resVO);
//				request.setAttribute("errMsg", "error: update fail");
//				RequestDispatcher rd = request.getRequestDispatcher("/admin-res-reception/resReception-index.jsp");
//				rd.forward(request, response);
//			}

			
			
			
			
//			//key是一個字串；value則可以是字串、數值、物件、布林值、有序列表(Array)，或null。			
//			if (result) { //如果有資料 result = true
//				JsonObject resp = new JsonObject();//建立一個JsonObject物件 名稱:resp
//				resp.addProperty("result", result);//resp加入屬性(Key,value)(true or false)
//				resp.addProperty("resID", resID);//resp加入屬性(Key,value)
//				PrintWriter out = response.getWriter();//回應.getWriter()方法向瀏覽器輸出資料
//				out.write(gson.toJson(resp));//將(resp物件轉回成JSON格式)在傳回頁面上
					
			// Servlet call Service 執行商業邏輯
			resSrc.update(dataMap);

//			resVO.setResId(resID);
//			resVO.setBzOpenHours(bzOpenHours);
//			resVO.setBzCloseHours(bzCloseHours);
//			resVO.setBzWeekTime(bzWeekTime);
//			resVO.setStatus(status);

//			}


			/***************************2.開始修改資料*****************************************/
//			ResService1 resSvc = new ResService1();
//			resVO = resSvc.update(bzOpenHours, bzCloseHours, bzWeekTime, status, resId);
			
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			// 因無須帶參數至頁面，執行成功重導至設定頁面 /monfood_maven/admin-res-reception/resReception-index.jsp
			String url = "/admin-res-reception/resReception-index.jsp";//這裡換成update頁面
			response.sendRedirect(request.getContextPath() + url);
	}




}
