package com.model.location;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationJDBCDAO implements ILocationDAO{


	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/MonFood?serverTimezone=Asia/Taipei";
	String userid = "root";
	String password = "password";

	
	private static final String INSERT_STMT = 
			"INSERT INTO MonFood.LOCATION (LOCATION_ID,USER_ID,ZIP_CODE,LOCATION,DEFAULT_STATUS) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM LOCATION order by LOCATION_ID";
	private static final String GET_ONE_STMT = 
			"SELECT LOCATION_ID,USER_ID,ZIP_CODE,LOCATION,DEFAULT_STATUS FROM MonFood.LOCATION where LOCATION_ID = ?";
	private static final String DELETE = 
			"DELETE FROM MonFood.LOCATION where LOCATION_ID = ?";
	private static final String UPDATE = 
			"UPDATE MonFood.LOCATION set USER_ID=?, ZIP_CODE=?, LOCATION=?, DEFAULT_STATUS=? where LOCATION_ID = ?";

	@Override
	public void insert(LocationVO locationVO) throws SQLException {
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

			pstmt.setInt(1, locationVO.getLocationId());//第一個欄位
			pstmt.setInt(2, locationVO.getUserId());//第二個欄位
			pstmt.setInt(3, locationVO.getZipCode());//第三個欄位
			pstmt.setString(4, locationVO.getLocation());//第四個欄位
			pstmt.setInt(5, locationVO.getDefaultStatus());//第五個欄位
			
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
	public void update(LocationVO locationVO) throws SQLException {
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

			pstmt.setInt(1, locationVO.getLocationId());//第一個欄位
			pstmt.setInt(2, locationVO.getUserId());//第二個欄位
			pstmt.setInt(3, locationVO.getZipCode());//第三個欄位
			pstmt.setString(4, locationVO.getLocation());//第四個欄位
			pstmt.setInt(5, locationVO.getDefaultStatus());//第五個欄位
			
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
	public void delete(Integer locationId) throws SQLException {
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


			pstmt.setInt(1, locationId);//第一個欄位
			
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
	public LocationVO findByPrimaryKey(Integer locationId) throws SQLException {
		LocationVO locationVO = null;
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

			pstmt.setInt(1, locationId);//第一個欄位
			
			rs = pstmt.executeQuery();//結果請求 = 參數化查詢呼叫執行查詢
			
			while (rs.next()) {
				// locationVO 也稱為 Domain objects 區域 對象
				locationVO = new LocationVO();
				locationVO.setLocationId(rs.getInt("LOCATION_ID"));;//第一個欄位
				locationVO.setUserId(rs.getInt("USER_ID"));//第二個欄位
				locationVO.setZipCode(rs.getInt("ZIP_CODE"));//第三個欄位
				locationVO.setLocation(rs.getString("LOCATION"));//第四個欄位
				locationVO.setDefaultStatus(rs.getInt("DEFAULT_STATUS"));//第五個欄位
				
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
		return locationVO;
	}

	@Override
	public List<LocationVO> getAll() throws SQLException {
		List<LocationVO> list = new ArrayList<LocationVO>();
		LocationVO locationVO = null;		
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
				// monsterVO 也稱為 Domain objects 區域 對象
				locationVO = new LocationVO();
				locationVO.setLocationId(rs.getInt("LOCATION_ID"));;//第一個欄位
				locationVO.setUserId(rs.getInt("USER_ID"));//第二個欄位
				locationVO.setZipCode(rs.getInt("ZIP_CODE"));//第三個欄位
				locationVO.setLocation(rs.getString("LOCATION"));//第四個欄位
				locationVO.setDefaultStatus(rs.getInt("DEFAULT_STATUS"));//第五個欄位
				list.add(locationVO); // Store the row in the list(將行存儲在列表中)
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
		LocationJDBCDAO dao = new LocationJDBCDAO();
		
		// 新增OK
//		LocationVO locationVO1 = new LocationVO();
//		locationVO1.setLocationId(6);
//		locationVO1.setUserId(5);//只能選擇已在資料庫內的使用者
//		locationVO1.setZipCode(105);//只能選擇已在資料庫內的ZipCode
//		locationVO1.setLocation("xxx");
//		locationVO1.setDefaultStatus(1);
//		dao.insert(locationVO1);
//		System.out.println("新增成功");

		// 修改  BUG待處理
//		LocationVO locationVO2 = new LocationVO();
//		locationVO2.setLocationId(7);
//		locationVO2.setUserId(5);
//		locationVO2.setZipCode(100);
//		locationVO2.setLocation("xxxooo");
//		locationVO2.setDefaultStatus(1);
//		dao.update(locationVO2);
//		System.out.println("修改成功");

		// 刪除OK
//		dao.delete(6);
//		System.out.println("刪除成功");

		// 查詢OK
//		LocationVO locationVO3 = dao.findByPrimaryKey(1);
//		System.out.print(locationVO3.getLocationId()+" ");
//		System.out.print(locationVO3.getUserId()+" ");
//		System.out.print(locationVO3.getZipCode()+" ");
//		System.out.print(locationVO3.getLocation()+" ");
//		System.out.println(locationVO3.getDefaultStatus()+" ");
//		System.out.println("---------------------");
		
		//查詢全部OK
//		List<LocationVO> list = dao.getAll();
//		for (LocationVO locationVO4 : list) {
//			System.out.print(locationVO4.getLocationId()+" ");
//			System.out.print(locationVO4.getUserId()+" ");
//			System.out.print(locationVO4.getZipCode()+" ");
//			System.out.print(locationVO4.getLocation()+" ");
//			System.out.println(locationVO4.getDefaultStatus()+" ");
//			System.out.println("---------------------");
//		}
	}
}
