package com.model.reception.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.model.order.OrderVO;
import com.model.reception.ResVO;
import com.model.reception.dao.ResDAO;


public class ResJDBCDAOimpl implements ResDAO {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/MonFood?serverTimezone=Asia/Taipei";
	String userid = "root";
	String password = "password";
	
	//修改 針對營業時間 營業星期 狀態
	private static final String UPDATE = "UPDATE MonFood.RES SET BZ_OPEN_HOURS = ? ,BZ_CLOSE_HOURS = ? ,BZ_WEEK_TIME = ?, STATUS = ? WHERE RES_ID = ?";
	//查詢單一
	private static final String GET_ONE_STMT = "SELECT * FROM MonFood.RES WHERE RES_ID = ? ";
	
	private static final String GET_ONE_ORDER = "SELECT * FROM MonFood.ORDER HAVING RES_ID = ? and ORDER_STATUS = ?";
	
	
	@Override
	public void update(ResVO resVO) {
		Connection con = null; //連線 = 空值
		PreparedStatement pstmt = null; //參數化查詢 = 空值
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE);//連線呼叫參數化查詢(修改)
			
			pstmt.setInt(5, resVO.getResId());
			pstmt.setTime(1, resVO.getBzOpenHours());
			pstmt.setTime(2, resVO.getBzCloseHours());
			pstmt.setString(3, resVO.getBzWeekTime());
			pstmt.setInt(4,resVO.getStatus());
			pstmt.executeUpdate();//參數化查詢呼叫執行更新
			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver.無法加載數據庫驅動程序 "
					+ e.getMessage());
			// Handle any SQL errors(處理任何SQL錯誤)
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.發生數據庫錯誤 "
					+ se.getMessage());
			// Clean up JDBC resources(清理 JDBC 資源)
		} finally { //一定執行裡面程式碼
			if (pstmt != null) { //參數化查詢 != 空值
				try {
					pstmt.close();//關閉參數化查詢
				} catch (SQLException se) { //接住SQL例外,se = 名稱
					se.printStackTrace(System.err);
					//printStackTrace()：印出異常信息,並提示程式碼中出錯的位置及原因
				}
			}
			if (con != null) { //連線 != 空值
				try {
					con.close();//關閉連線
				} catch (Exception e) { //接住例外,e = 名稱
					e.printStackTrace(System.err);
				}	//printStackTrace()：印出異常信息,並提示程式碼中出錯的位置及原因
			}
		}		
		
	}


	@Override
	public ResVO findByPrimaryKey(Integer resId) {
		
		ResVO resVO = null;
		ResultSet rs = null; //結果請求 = 空值
		Connection con = null; //連線 = 空值
		PreparedStatement pstmt = null; //參數化查詢 = 空值
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);//連線呼叫參數化查詢(修改)
			
			pstmt.setInt(1, resId);
			rs = pstmt.executeQuery();//結果請求 = 參數化查詢呼叫執行查詢
			
			while (rs.next()) {
				// resVO 也稱為 Domain objects 區域 對象
				resVO = new ResVO();
				resVO.setResId(rs.getInt("RES_ID"));
//				resVO.setResCategory(rs.getInt("RES_CATEGORY"));
//				resVO.setResAccount(rs.getString("RES_ACCOUNT"));
//				resVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
//				resVO.setResName(rs.getString("RES_NAME"));
//				resVO.setResPassword(rs.getString("RES_PASSWORD"));
//				resVO.setResTel(rs.getString("RES_TEL"));
//				resVO.setResPic(rs.getBytes("RES_PIC"));
//				resVO.setOwnerName(rs.getString("OWNER_NAME"));
//				resVO.setOwnerTel(rs.getString("OWNER_TEL"));
//				resVO.setBzLocation(rs.getString("BZ_LOCATION"));
//				resVO.setZipCode(rs.getInt("ZIP_CODE"));
				resVO.setBzOpenHours(rs.getTime("BZ_OPEN_HOURS"));
				resVO.setBzCloseHours(rs.getTime("BZ_CLOSE_HOURS"));
				resVO.setBzWeekTime(rs.getString("BZ_WEEK_TIME"));
				resVO.setStatus(rs.getInt("STATUS"));
			}
		
			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver.無法加載數據庫驅動程序 "
					+ e.getMessage());
			// Handle any SQL errors(處理任何SQL錯誤)
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.發生數據庫錯誤 "
					+ se.getMessage());
			// Clean up JDBC resources(清理 JDBC 資源)
		} finally { //一定執行裡面程式碼
			if (pstmt != null) { //參數化查詢 != 空值
				try {
					pstmt.close();//關閉參數化查詢
				} catch (SQLException se) { //接住SQL例外,se = 名稱
					se.printStackTrace(System.err);
					//printStackTrace()：印出異常信息,並提示程式碼中出錯的位置及原因
				}
			}
			if (con != null) { //連線 != 空值
				try {
					con.close();//關閉連線
				} catch (Exception e) { //接住例外,e = 名稱
					e.printStackTrace(System.err);
				}	//printStackTrace()：印出異常信息,並提示程式碼中出錯的位置及原因
			}
		}
		return resVO;		
	}




	public static void main(String[] args) {
		ResJDBCDAOimpl dao = new ResJDBCDAOimpl();
		
		// 修改OK
		ResVO resVO = new ResVO();
		resVO.setResId(5);
		resVO.setBzOpenHours(java.sql.Time.valueOf("3:33:33"));
		resVO.setBzCloseHours(java.sql.Time.valueOf("22:33:33"));
		resVO.setBzWeekTime("1,2,3,4,5");
		resVO.setStatus(0);
		dao.update(resVO);
		System.out.println("成功喔!");
		
		// 查詢OK
		ResVO resVO1 = dao.findByPrimaryKey(5);
		System.out.println(resVO1.getResId());
		System.out.println(resVO1.getBzOpenHours()+ ",");
		System.out.println(resVO1.getBzCloseHours() + ",");
		System.out.println(resVO1.getBzWeekTime() + ",");
		System.out.println(resVO1.getStatus() + ",");		
		System.out.println("---------------------");
		
		OrderVO orderVO = dao.findByPrimaryKey1(1);
		System.out.println(orderVO.getResId());
		System.out.println(orderVO.getOrderStatus());
		System.out.println("---------------------");
		
	}


	@Override
	public OrderVO findByPrimaryKey1(Integer resId) {
		OrderVO orderVO = null;
		ResultSet rs = null; //結果請求 = 空值
		Connection con = null; //連線 = 空值
		PreparedStatement pstmt = null; //參數化查詢 = 空值
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_ORDER);//連線呼叫參數化查詢
			
			pstmt.setInt(1, resId);

			
			rs = pstmt.executeQuery();//結果請求 = 參數化查詢呼叫執行查詢
			
			while (rs.next()) {
				// resVO 也稱為 Domain objects 區域 對象
				orderVO = new OrderVO();
//				orderVO.setOrderId(rs.getInt("ORDER_ID"));
				orderVO.setResId(rs.getInt("RES_ID"));
				orderVO.setOrderStatus(rs.getInt("ORDER_STATUS"));
//		
			}
		
			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver.無法加載數據庫驅動程序 "
					+ e.getMessage());
			// Handle any SQL errors(處理任何SQL錯誤)
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.發生數據庫錯誤 "
					+ se.getMessage());
			// Clean up JDBC resources(清理 JDBC 資源)
		} finally { //一定執行裡面程式碼
			if (pstmt != null) { //參數化查詢 != 空值
				try {
					pstmt.close();//關閉參數化查詢
				} catch (SQLException se) { //接住SQL例外,se = 名稱
					se.printStackTrace(System.err);
					//printStackTrace()：印出異常信息,並提示程式碼中出錯的位置及原因
				}
			}
			if (con != null) { //連線 != 空值
				try {
					con.close();//關閉連線
				} catch (Exception e) { //接住例外,e = 名稱
					e.printStackTrace(System.err);
				}	//printStackTrace()：印出異常信息,並提示程式碼中出錯的位置及原因
			}
		}
		return orderVO;		
	}




}
