package com.model.creditcard.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.creditcard.CreditcardVO;
import com.model.creditcard.dao.CreditcardDAO;

public class CreditcardJDBCDAOimpl implements CreditcardDAO{


	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/MonFood?serverTimezone=Asia/Taipei";
	String userid = "root";
	String password = "password";
	
	private static final String INSERT_STMT = 
			"INSERT INTO MonFood.CREDITCARD (CREDITCARD_ID,USER_ID,CARD_NUM,SECURITY_CODE,EX_DATE,DEFAULT_STATUS) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM CREDITCARD order by CREDITCARD_ID";
	private static final String GET_ONE_STMT = 
			"SELECT CREDITCARD_ID,USER_ID,CARD_NUM,SECURITY_CODE,EX_DATE,DEFAULT_STATUS FROM MonFood.CREDITCARD where CREDITCARD_ID = ?";
	private static final String DELETE = 
			"DELETE FROM MonFood.CREDITCARD where CREDITCARD_ID = ?";
	private static final String UPDATE = 
			"UPDATE MonFood.CREDITCARD set USER_ID = ?, CARD_NUM=?, SECURITY_CODE=?, EX_DATE=?, DEFAULT_STATUS=? where CREDITCARD_ID = ?";

	@Override
	public void insert(CreditcardVO creditcardVO) throws SQLException {
		Connection con = null; //連線 = 空值
		PreparedStatement pstmt = null; //參數化查詢 = 空值
		//PreparedStatement:
		//是指在設計與資料庫連結並存取資料時，在需要填入數值或資料的地方，使用參數（parameter）來給值，
		//這個方法目前已被視為最有效可預防SQL注入攻擊的攻擊手法的防禦方式。
		//參數化的查詢往往有效能優勢。因為參數化的查詢能讓不同的資料通過參數到達資料庫，從而公用同一條SQL語句。
		//PreparedStatement與java.sql.Connection對像是關聯的，一旦你關閉了connection，PreparedStatement也沒法使用了
				
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);//連線呼叫參數化查詢(新增)

			pstmt.setInt(1, creditcardVO.getCreditcardId());//第一個欄位
			pstmt.setInt(2, creditcardVO.getUserId());//第二個欄位
			pstmt.setString(3, creditcardVO.getCardNum());//第三個欄位
			pstmt.setString(4, creditcardVO.getSecurityCode());//第四個欄位
			pstmt.setDate(5, creditcardVO.getExDate());//第五個欄位
			pstmt.setInt(6, creditcardVO.getDefaultStatus());//第六個欄位
			
			pstmt.executeUpdate();//參數化查詢呼叫執行更新

			// Handle any driver errors(處理任何驅動程序錯誤)
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
	public void update(CreditcardVO creditcardVO) throws SQLException {
		Connection con = null; //連線 = 空值
		PreparedStatement pstmt = null; //參數化查詢 = 空值
		//PreparedStatement:
		//是指在設計與資料庫連結並存取資料時，在需要填入數值或資料的地方，使用參數（parameter）來給值，
		//這個方法目前已被視為最有效可預防SQL注入攻擊的攻擊手法的防禦方式。
		//參數化的查詢往往有效能優勢。因為參數化的查詢能讓不同的資料通過參數到達資料庫，從而公用同一條SQL語句。
		//PreparedStatement與java.sql.Connection對像是關聯的，一旦你關閉了connection，PreparedStatement也沒法使用了
				
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE);//連線呼叫參數化查詢(修改)

			pstmt.setInt(6, creditcardVO.getCreditcardId());//第六個 where ?
			pstmt.setInt(1, creditcardVO.getUserId());//第一個 ?
			pstmt.setString(2, creditcardVO.getCardNum());//第二個 ? 
			pstmt.setString(3, creditcardVO.getSecurityCode());//第三個 ? 
			pstmt.setDate(4, creditcardVO.getExDate());//第四個 ?
			pstmt.setInt(5, creditcardVO.getDefaultStatus());//第五個 ?
			
			pstmt.executeUpdate();//參數化查詢呼叫執行更新

			// Handle any driver errors(處理任何驅動程序錯誤)
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
	public void delete(Integer creditcardId) throws SQLException {
		Connection con = null; //連線 = 空值
		PreparedStatement pstmt = null; //參數化查詢 = 空值
		//PreparedStatement:
		//是指在設計與資料庫連結並存取資料時，在需要填入數值或資料的地方，使用參數（parameter）來給值，
		//這個方法目前已被視為最有效可預防SQL注入攻擊的攻擊手法的防禦方式。
		//參數化的查詢往往有效能優勢。因為參數化的查詢能讓不同的資料通過參數到達資料庫，從而公用同一條SQL語句。
		//PreparedStatement與java.sql.Connection對像是關聯的，一旦你關閉了connection，PreparedStatement也沒法使用了
				
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(DELETE);//連線呼叫參數化查詢(刪除)


			pstmt.setInt(1, creditcardId);//第一個欄位
			
			pstmt.executeUpdate();//參數化查詢呼叫執行更新

			// Handle any driver errors(處理任何驅動程序錯誤)
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
	public CreditcardVO findByPrimaryKey(Integer creditcardId) throws SQLException {
		CreditcardVO creditcardVO = null;
		ResultSet rs = null; //結果請求 = 空值
		Connection con = null; //連線 = 空值
		PreparedStatement pstmt = null; //參數化查詢 = 空值
		//PreparedStatement:
		//是指在設計與資料庫連結並存取資料時，在需要填入數值或資料的地方，使用參數（parameter）來給值，
		//這個方法目前已被視為最有效可預防SQL注入攻擊的攻擊手法的防禦方式。
		//參數化的查詢往往有效能優勢。因為參數化的查詢能讓不同的資料通過參數到達資料庫，從而公用同一條SQL語句。
		//PreparedStatement與java.sql.Connection對像是關聯的，一旦你關閉了connection，PreparedStatement也沒法使用了
				
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);//連線呼叫參數化查詢(單一查詢)

			pstmt.setInt(1, creditcardId);//第一個欄位
			
			rs = pstmt.executeQuery();//結果請求 = 參數化查詢呼叫執行查詢
			
			while (rs.next()) {
				// creditcardVO 也稱為 Domain objects 區域 對象
				creditcardVO = new CreditcardVO();
				creditcardVO.setCreditcardId(rs.getInt("CREDITCARD_ID"));//第一個欄位
				creditcardVO.setUserId(rs.getInt("USER_ID"));//第二個欄位
				creditcardVO.setCardNum(rs.getString("CARD_NUM"));;//第三個欄位
				creditcardVO.setSecurityCode(rs.getString("SECURITY_CODE"));//第四個欄位
				creditcardVO.setExDate(rs.getDate("EX_DATE"));//第五個欄位
				creditcardVO.setDefaultStatus(rs.getInt("DEFAULT_STATUS"));//第六個欄位
				
			}

			// Handle any driver errors(處理任何驅動程序錯誤)
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
		return creditcardVO;
	}

	@Override
	public List<CreditcardVO> getAll() throws SQLException {
		List<CreditcardVO> list = new ArrayList<CreditcardVO>();
		CreditcardVO creditcardVO = null;		
		Connection con = null; //連線 = 空值
		PreparedStatement pstmt = null; //參數化查詢 = 空值
		//PreparedStatement:
		//是指在設計與資料庫連結並存取資料時，在需要填入數值或資料的地方，使用參數（parameter）來給值，
		//這個方法目前已被視為最有效可預防SQL注入攻擊的攻擊手法的防禦方式。
		//參數化的查詢往往有效能優勢。因為參數化的查詢能讓不同的資料通過參數到達資料庫，從而公用同一條SQL語句。
		//PreparedStatement與java.sql.Connection對像是關聯的，一旦你關閉了connection，PreparedStatement也沒法使用了
		ResultSet rs = null; //結果請求 = 空值
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// creditcardVO 也稱為 Domain objects 區域 對象
				creditcardVO = new CreditcardVO();
				creditcardVO.setCreditcardId(rs.getInt("CREDITCARD_ID"));//第一個欄位
				creditcardVO.setUserId(rs.getInt("USER_ID"));//第二個欄位
				creditcardVO.setCardNum(rs.getString("CARD_NUM"));;//第三個欄位
				creditcardVO.setSecurityCode(rs.getString("SECURITY_CODE"));//第四個欄位
				creditcardVO.setExDate(rs.getDate("EX_DATE"));//第五個欄位
				creditcardVO.setDefaultStatus(rs.getInt("DEFAULT_STATUS"));//第六個欄位
				list.add(creditcardVO); // Store the row in the list(將行存儲在列表中)
			}
			
			// Handle any driver errors(處理任何驅動程序錯誤)
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
	
	//測試
	public static void main(String[] args) throws SQLException {
		CreditcardJDBCDAOimpl dao = new CreditcardJDBCDAOimpl();
		// 新增OK
//		CreditcardVO creditcardVO1 = new CreditcardVO();
//		creditcardVO1.setCreditcardId(6);
//		creditcardVO1.setUserId(5); //只能選擇已在資料庫內的使用者
//		creditcardVO1.setCardNum("33333333");
//		creditcardVO1.setSecurityCode("5555555");
//		creditcardVO1.setExDate(java.sql.Date.valueOf("2022-12-12"));
//		creditcardVO1.setDefaultStatus(0);
//		dao.insert(creditcardVO1);
//		System.out.println("新增成功");

		// 修改 OK
//		CreditcardVO creditcardVO2 = new CreditcardVO();
//		creditcardVO2.setCreditcardId(6); //只能選擇已在資料庫內的CreditcardId
//		creditcardVO2.setUserId(5); //只能選擇已在資料庫內的UserId
//		creditcardVO2.setCardNum("0987654321");
//		creditcardVO2.setSecurityCode("1qaz2wsx3ed");
//		creditcardVO2.setExDate(java.sql.Date.valueOf("2022-11-11"));
//		creditcardVO2.setDefaultStatus(1);
//		dao.update(creditcardVO2);
//		System.out.println("修改成功");

		// 刪除OK
//		dao.delete(7);//選擇要刪除的卡號ID
//		System.out.println("刪除成功");
		
		// 查詢OK
//		CreditcardVO creditcardVO3 = dao.findByPrimaryKey(3);
//		System.out.println(creditcardVO3.getCreditcardId() + ",");
//		System.out.println(creditcardVO3.getUserId() + ",");
//		System.out.println(creditcardVO3.getCardNum() + ",");
//		System.out.println(creditcardVO3.getSecurityCode() + ",");
//		System.out.println(creditcardVO3.getExDate() + ",");
//		System.out.println(creditcardVO3.getDefaultStatus() + ",");
//		System.out.println("---------------------");
		
		//查詢全部OK
//		List<CreditcardVO> list = dao.getAll();
//		for (CreditcardVO creditcardVO4 : list) {
//		System.out.println(creditcardVO4.getCreditcardId());
//		System.out.println(creditcardVO4.getUserId());
//		System.out.println(creditcardVO4.getCardNum());
//		System.out.println(creditcardVO4.getSecurityCode());
//		System.out.println(creditcardVO4.getExDate());
//		System.out.println(creditcardVO4.getDefaultStatus());
//		System.out.println("---------------------");
//		}

	}
}
