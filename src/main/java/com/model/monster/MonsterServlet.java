package com.model.monster;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/MonsterServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//當數據量大於fileSizeThreshold值時，內容將被寫入磁碟
//上傳過程中無論是單個文件超過maxFileSize值，或者上傳的總量大於maxRequestSize 值都會拋出IllegalStateException 異常
public class MonsterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) {
			
			//Store this set in the request scope, in case we need to
			//send the ErrorPage view.
			//將此集合存儲在請求範圍內，以防我們需要發送錯誤頁面視圖。
			List<String> errorMsgs = new LinkedList<String>();//建立一個錯誤訊息的集合物件
			request.setAttribute("errorMsgs", errorMsgs);//回應接收請求的錯誤訊息
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			
			String str = request.getParameter("monsLevel"); //請求所獲得的參數
			
			//如果輸入的字串是空值 或 去除空格後的字串長度是0
			if(str == null || (str.trim()).length() == 0 ) {
				errorMsgs.add("請輸入正確的小怪獸等級");//系統給予提示,使用者錯誤訊息
			}
			// Send the use back to the form, if there were errors
			// 如果有錯誤，將返回發送表單
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request
						.getRequestDispatcher("搜尋的頁面.jsp");//這裡要換成做搜尋的頁面(目前還沒有)
				failureView.forward(request, response);
				return;//程式中斷
			}
			Integer monsLevel = null;//當monsLevel = 空值
			try {
				monsLevel = Integer.valueOf(str);//嘗試轉型Integer
			} catch (Exception e) {
				errorMsgs.add("小怪獸等級格式不正確");//系統給予提示,使用者錯誤訊息
			}
			// Send the use back to the form, if there were errors 
			//如果有錯誤，將返回發送表單
			if (!errorMsgs.isEmpty()) { //錯誤訊息非空的
				RequestDispatcher failureView = request
						.getRequestDispatcher("搜尋的頁面.jsp");//這裡要換成做搜尋的頁面(目前還沒有)
				failureView.forward(request, response);//回到查看失敗頁面
				return;//程式中斷
			}
			/***************************2.開始查詢資料*****************************************/
			MonsterService monSvc = new MonsterService();
			MonsterVO monsterVO = null;
			try {
				monsterVO = monSvc.getOneMonster(monsLevel);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (monsterVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			//如果有錯誤，將返回發送表單
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request
						.getRequestDispatcher("搜尋的頁面.jsp");//這裡要換成做搜尋的頁面(目前還沒有)
				failureView.forward(request, response);
				return;//程式中斷
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			request.setAttribute("monsterVO", monsterVO); // 資料庫取出的monsterVO物件,存入request
			String url = "搜尋的頁面.jsp";//這裡要換成做搜尋的頁面(目前還沒有)
			RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 搜尋的頁面.html(目前還沒有)
			successView.forward(request, response);
	
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自搜尋的頁面.html的請求(目前還沒有)

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			//將此集合存儲在請求範圍內，以防我們需要發送錯誤頁面視圖。
			request.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數****************************************/
			Integer monsLevel = Integer.valueOf(request.getParameter("monsLevel"));
				
			/***************************2.開始查詢資料****************************************/
			MonsterService monSvc = new MonsterService();
			MonsterVO monsterVO = null;
			try {
				monsterVO = monSvc.getOneMonster(monsLevel);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (monsterVO == null) {
				errorMsgs.add("查無資料");
			}					
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			request.setAttribute("monsterVO", monsterVO);  // 資料庫取出的monsterVO物件,存入request
			String url = "換成update.jsp";//這裡換成update頁面.jsp(目前還沒有)
			RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 update的頁面.html的請求(目前還沒有)
			successView.forward(request, response);
		}
		
		if ("update".equals(action)) { // 來自update頁面.jsp的請求(目前還沒有)
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to 
			// send the ErrorPage view.
			// 將此集合存儲在請求範圍內，以防我們需要發送錯誤頁面視圖。
			request.setAttribute("errorMsgs", errorMsgs);
		
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer monsLevel = Integer.valueOf(request.getParameter("monsLevel").trim());
			
			Integer discount = Integer.valueOf(request.getParameter("discount").trim());
			
			Part monsPic = request.getPart("monsPic");
			InputStream in = monsPic.getInputStream();
			byte[] buf = new byte[in.available()];
			in.read(buf);
			in.close();
			
			MonsterVO monsterVO = new MonsterVO();
			monsterVO.setMonsLevel(monsLevel);
			monsterVO.setDiscount(discount);
			monsterVO.setMonsPic(buf);//原始圖片的檔案位置
			
			// Send the use back to the form, if there were errors
			////如果有錯誤，將返回發送表單
			if (!errorMsgs.isEmpty()) {
				request.setAttribute("monsterVO",monsterVO); // 含有輸入格式錯誤的monsterVO物件,也存入request
				RequestDispatcher failureView = request
						.getRequestDispatcher("update頁面.jsp");//這裡要換成update的頁面(目前還沒有)
				failureView.forward(request, response);
				return; //程式中斷
			}
			
			/***************************2.開始修改資料*****************************************/
			MonsterService monSvc = new MonsterService();
			try {
				monsterVO = monSvc.updateMonster(monsLevel,discount,buf);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			request.setAttribute("monsterVO", monsterVO); // 資料庫update成功後,正確的的monsterVO物件,存入request
			String url = "換成update.jsp";//這裡換成update頁面.jsp(目前還沒有)
			RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交update頁面.html(目前還沒有)
			successView.forward(request, response);
		}
		
		if ("insert".equals(action)) { // 來自addMonster.html的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			//將此集合存儲在請求範圍內，以防我們需要發送錯誤頁面視圖。
			request.setAttribute("errorMsgs", errorMsgs);
		
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer monsLevel = Integer.valueOf(request.getParameter("monsLevel").trim());
			
			Integer discount = Integer.valueOf(request.getParameter("discount").trim());
			
			Part monsPic = request.getPart("monsPic");
			InputStream in = monsPic.getInputStream();
			byte[] buf = new byte[in.available()];
			in.read(buf);
			in.close();
			
			MonsterVO monsterVO = new MonsterVO();
			monsterVO.setMonsLevel(monsLevel);
			monsterVO.setDiscount(discount);
			monsterVO.setMonsPic(buf);//原始圖片的檔案位置
			
			// Send the use back to the form, if there were errors
			////如果有錯誤，將返回發送表單
			if (!errorMsgs.isEmpty()) {
				request.setAttribute("monsterVO",monsterVO); // 含有輸入格式錯誤的monsterVO物件,也存入request
				RequestDispatcher failureView = request
						.getRequestDispatcher("insert頁面.jsp");//這裡要換成insert的頁面(目前還沒有)
				failureView.forward(request, response);
				return; //程式中斷
			}
			
			/***************************2.開始新增資料*****************************************/
			MonsterService monSvc = new MonsterService();
			try {
				monsterVO = monSvc.updateMonster(monsLevel,discount,buf);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			request.setAttribute("monsterVO", monsterVO); // 資料庫insert成功後,正確的的monsterVO物件,存入request
			String url = "換成insert.jsp";//這裡換成insert頁面.html(目前還沒有)
			RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交insert頁面.html(目前還沒有)
			successView.forward(request, response);
		}
		

		if ("delete".equals(action)) { // 來自delete頁面.jsp(目前還沒有)

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			//將此集合存儲在請求範圍內，以防我們需要發送錯誤頁面視圖。
			request.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer monsLevel = Integer.valueOf(request.getParameter("monsLevel"));
				
				/***************************2.開始刪除資料***************************************/
				MonsterService monSvc = new MonsterService();
				try {
					monSvc.deleteMonster(monsLevel);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "delete頁面.jsp";// 來自delete頁面.jsp(目前還沒有)
				RequestDispatcher successView = request.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(request, response);
		}
	
	}

}
