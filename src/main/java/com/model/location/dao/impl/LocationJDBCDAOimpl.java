package com.model.location.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.location.LocationVO;
import com.model.location.dao.LocationDAO;

public class LocationJDBCDAOimpl implements LocationDAO{

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/MonFood?serverTimezone=Asia/Taipei";
	String userid = "root";
	String password = "password";
	
	private static final String INSERT_STMT = 
			"INSERT INTO MonFood.LOCATION (USER_ID, LOCATION) VALUES (?, ?)";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM MonFood.LOCATION where USER_ID = ?";

	@Override
	public void insert(LocationVO locationVO) throws SQLException {
		Connection con = null; //連線 = 空值
		PreparedStatement pstmt = null; //參數化查詢 = 空值
				
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);//連線呼叫參數化查詢(新增)

			pstmt.setInt(1, locationVO.getUserId());//第二個欄位
			pstmt.setString(2, locationVO.getLocation());//第四個欄位
			
			pstmt.executeUpdate();//參數化查詢呼叫執行更新

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver.無法加載數據庫驅動程序 "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.發生數據庫錯誤 "
					+ se.getMessage());
		} finally { //一定執行裡面程式碼
			if (pstmt != null) { //參數化查詢 != 空值
				try {
					pstmt.close();//關閉參數化查詢
				} catch (SQLException se) { //接住SQL例外,se = 名稱
					se.printStackTrace(System.err);
				}
			}
			if (con != null) { //連線 != 空值
				try {
					con.close();//關閉連線
				} catch (Exception e) { //接住例外,e = 名稱
					e.printStackTrace(System.err);
				}	
			}
		}		
		
	}

	@Override
	public List<LocationVO> findByPrimaryKey(Integer userId) throws SQLException {
		
		List<LocationVO> list = new ArrayList<LocationVO>();
		LocationVO locationVO = null;
		ResultSet rs = null; //結果請求 = 空值
		Connection con = null; //連線 = 空值
		PreparedStatement pstmt = null; //參數化查詢 = 空值
				
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);//連線呼叫參數化查詢(單一查詢)

			pstmt.setInt(1, userId);//第一個欄位
			
			rs = pstmt.executeQuery();//結果請求 = 參數化查詢呼叫執行查詢
			
			while (rs.next()) {
				// locationVO 也稱為 Domain objects 區域 對象
				locationVO = new LocationVO();
				locationVO.setLocationId(rs.getInt("LOCATION_ID"));;//第一個欄位
				locationVO.setUserId(rs.getInt("USER_ID"));//第二個欄位
				locationVO.setLocation(rs.getString("LOCATION"));//第四個欄位
				list.add(locationVO);
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
		return list;
	}

}
