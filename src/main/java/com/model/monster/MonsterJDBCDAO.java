package com.model.monster;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MonsterJDBCDAO implements IMonsterDAO {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/MonFood?serverTimezone=Asia/Taipei";
	String userid = "root";
	String password = "password";
	
	private static final String INSERT_STMT = 
			"INSERT INTO MonFood.MONSTER (MONS_LEVEL,DISCOUNT,MONS_PIC) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT MONS_LEVEL,DISCOUNT,MONS_PIC FROM MONSTER order by MONS_LEVEL";
	private static final String GET_ONE_STMT = 
			"SELECT MONS_LEVEL,DISCOUNT,MONS_PIC FROM MonFood.MONSTER where MONS_LEVEL = ?";
	private static final String DELETE = 
			"DELETE FROM MonFood.MONSTER where MONS_LEVEL = ?";
	private static final String UPDATE = 
			"UPDATE MonFood.MONSTER set DISCOUNT = ?, MONS_PIC = ? where MONS_LEVEL = ?";

	@Override
	public void insert(MonsterVO monsterVO) throws SQLException {

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

			pstmt.setInt(1, monsterVO.getMonsLevel());//第一個欄位
			pstmt.setInt(2, monsterVO.getDiscount());//第二個欄位
			pstmt.setBytes(3, monsterVO.getMonsPic());//第三個欄位圖片byte[]用Bytes
			
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
	public void update(MonsterVO monsterVO) throws SQLException {
		
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

			pstmt.setInt(3, monsterVO.getMonsLevel());//第三個 where ? 
			pstmt.setInt(1, monsterVO.getDiscount());//第一個 ? 
			pstmt.setBytes(2, monsterVO.getMonsPic());//第二個 ? ,圖片byte[]用Bytes
			
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
	public void delete(Integer monsLevel) throws SQLException {
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

			pstmt.setInt(1,monsLevel);//第一個欄位
			
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
	public MonsterVO findByPrimaryKey(Integer monsLevel) throws SQLException {
		
		MonsterVO monsterVO = null;
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

			pstmt.setInt(1, monsLevel);//第一個欄位
			
			rs = pstmt.executeQuery();//結果請求 = 參數化查詢呼叫執行查詢
			
			while (rs.next()) {
				// monsterVO 也稱為 Domain objects 區域 對象
				monsterVO = new MonsterVO();
				monsterVO.setMonsLevel(rs.getInt("MONS_LEVEL"));
				monsterVO.setDiscount(rs.getInt("DISCOUNT"));
				monsterVO.setMonsPic(rs.getBytes("MONS_PIC"));//第三個欄位圖片byte[]用Bytes
				
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
		return monsterVO;		
	}


	@Override
	public List<MonsterVO> getAll() throws SQLException {
		List<MonsterVO> list = new ArrayList<MonsterVO>();
		MonsterVO monsterVO = null;		
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
				monsterVO = new MonsterVO();
				monsterVO.setMonsLevel(rs.getInt("MONS_LEVEL"));
				monsterVO.setDiscount(rs.getInt("DISCOUNT"));
				monsterVO.setMonsPic(rs.getBytes("MONS_PIC"));//第三個欄位圖片byte[]用Bytes
				list.add(monsterVO); // Store the row in the list(將行存儲在列表中)
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
	public static void main(String[] args) throws SQLException, IOException {
		
		MonsterJDBCDAO dao = new MonsterJDBCDAO();
		
		//存入圖片用的方法
		InputStream in = new FileInputStream("C:\\Users\\Tibame\\Desktop\\111.jpg");//圖片的檔案路徑
		byte[] buf = new byte[in.available()];
		in.read(buf);
		
		// 新增OK
//		MonsterVO monsterVO1 = new MonsterVO();
//		monsterVO1.setMonsLevel(10);
//		monsterVO1.setDiscount(66);
//		monsterVO1.setMonsPic(buf);//原始圖片的檔案位置
//		dao.insert(monsterVO1);
//		System.out.println("新增成功");

		// 修改OK
//		MonsterVO monsterVO2 = new MonsterVO();
//		monsterVO2.setMonsLevel(6); //where ? = 要修改的MonsLevel
//		monsterVO2.setDiscount(300);
//		monsterVO2.setMonsPic(buf);//原始圖片的檔案位置
//		dao.update(monsterVO2);
//		System.out.println("修改成功");

		// 刪除OK
//		dao.delete(6);

		// 查詢OK
//		MonsterVO monsterVO3 = dao.findByPrimaryKey(6);
//		System.out.println(monsterVO3.getMonsLevel() + ",");
//		System.out.println(monsterVO3.getDiscount() + ",");
//		System.out.println(monsterVO3.getMonsPic() + ",");
//		System.out.println("---------------------");
		
		//查詢全部OK
//		List<MonsterVO> list = dao.getAll();
//		for (MonsterVO monsterVO4 : list) {
//			System.out.println(monsterVO4.getMonsLevel());
//			System.out.println(monsterVO4.getDiscount());
//			System.out.println(monsterVO4.getMonsPic());
//			System.out.println("---------------------");
//		}
	}
	

}
