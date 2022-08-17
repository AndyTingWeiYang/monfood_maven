package com.model.res.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.res.ResDto;
import com.model.res.ResVO;
import com.model.res.dao.ResDAO;

public class ResDAOImpl implements ResDAO {
	static String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/MonFood?serverTimezone=Asia/Taipei";
	String USERID = "root";
	String PASSWORD = "password";

// SQL指令
	// 新增
	private static final String INSERT_STMT = "INSERT INTO MonFood.RES(RES_ACCOUNT,RES_NAME,RES_PASSWORD,OWNER_TEL) VALUES (?,?,?,?)";
	// 更新
	private static final String UPDATE = "UPDATE MonFood.RES SET RES_CATEGORY = ?,RES_ACCOUNT = ?,UPDATE_TIME = ?,RES_NAME = ?,RES_PASSWORD = ?,RES_TEL = ?,RES_PIC = ? ,OWNER_NAME = ? ,OWNER_TEL = ? ,BZ_LOCATION = ? ,ZIP_CODE = ? ,BZ_OPEN_HOURS = ? ,BZ_CLOSE_HOURS = ? ,BZ_WEEK_TIME = ? WHERE RES_ID = ?";
	// 使用會員統編更新密碼(忘記密碼)
	private static final String UPDATEPASSWORD = "UPDATE MonFood.RES SET RES_PASSWORD = ? WHERE RES_ACCOUNT = ?";
	// 刪除
	private static final String DELETE = "DELETE FROM MonFood.RES WHERE RES_ID = ?";
	// 查詢一筆
	private static final String SELECTBYRESID = "SELECT * FROM MonFood.RES WHERE RES_ID = ? ";
	// 使用商家帳號查詢一筆資料 for Login
	private static final String SELECTBYRESACCOUNT = "SELECT * FROM MonFood.RES WHERE RES_ACCOUNT = ? ";
	// 使用商家名稱查詢資料
	private static final String SELECTBYRESNAME = "SELECT * FROM MonFood.RES WHERE RES_NAME = ? ";
	// 查詢全部
	private static final String GETALL = "SELECT * FROM MonFood.RES ORDER BY RES_ID ";
	// 查詢是否有此會員帳號
	private static final String ISDUPLICATEACCOUNT = "SELECT RES_ACCOUNT FROM MonFood.RES WHERE RES_ACCOUNT = ? ";
	// 使用餐廳分類查詢一筆多筆資料
	private static final String SELECTBYCATEGORY = "SELECT ROUND(AVG(RES_RATE),1) , RES.RES_ID, RES.RES_NAME, RES.RES_CATEGORY FROM RES LEFT JOIN `ORDER` on RES.RES_ID = ORDER.RES_ID WHERE RES_CATEGORY = ? GROUP by RES.RES_ID ";
	// 使用
	private static final String GETRATE = "SELECT ROUND(AVG(RES_RATE),1) , RES.ZIP_CODE, RES.RES_ID, RES.RES_NAME, RES.RES_CATEGORY FROM MonFood.RES LEFT JOIN `ORDER` on RES.RES_ID = `ORDER`.RES_ID GROUP by RES.RES_ID ";
//	//搜尋框模糊查詢

//	private static final String GETRATE = "SELECT AVG(RES_RATE) , RES.RES_ID, RES.RES_NAME, RES.RES_CATEGORY, RES.BZ_LOCATION, RES.BZ_OPEN_HOURS, RES.BZ_CLOSE_HOURS FROM MonFood.RES LEFT JOIN `ORDER` on RES.RES_ID = `ORDER`.RES_ID GROUP by RES.RES_ID ";
	// 首頁搜尋行政區內餐廳
	private static final String GETRESFORINDEX = "SELECT ROUND(AVG(RES_RATE),1) , RES.RES_ID, RES.RES_NAME, RES.RES_CATEGORY, ZIP_CODE FROM MonFood.RES LEFT JOIN `ORDER` on RES.RES_ID = `ORDER`.RES_ID GROUP by RES.RES_ID ";

	private static final String SEARCHPRODUCT = "SELECT ROUND(AVG(RES_RATE),1) , RES.*, PRODUCT.* "
			+ "FROM MonFood.RES LEFT JOIN `ORDER` ON RES.RES_ID = `ORDER`.RES_ID "
			+ "INNER JOIN PRODUCT ON RES.RES_ID = PRODUCT.RES_ID " + "WHERE PRODUCT.PRODUCT_NAME like ? "
			+ "GROUP BY RES.RES_ID, PRODUCT.PRODUCT_ID";
	// 使用ResId搜尋該商家客戶回饋
	private static final String GET_COMMENT = "select RES_COMMENT, RES_ID from `ORDER` where RES_ID = ? ";
	// 進入餐廳頁面
	private static final String GET_TO_RESPAGE = "SELECT RES_ID, RES_NAME, RES_CATEGORY, BZ_LOCATION, BZ_OPEN_HOURS, BZ_CLOSE_HOURS "
			+ "FROM MonFood.RES " + "where RES_ID = ? ";

	private static final String SELECTRESINFO = "SELECT * FROM MonFood.RES left join RES_CATEGORY on RES.RES_CATEGORY = RES_CATEGORY.RES_CATEGORY_ID where RES_ID = ? ";


	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> getToResPage(Integer resId) {
		Map<String, Object> resPage = new HashMap<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = conn.prepareStatement(GET_TO_RESPAGE);
			pstmt.setInt(1, resId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				resPage.put("resId", rs.getInt("RES_ID"));
				resPage.put("resName", rs.getString("RES_NAME"));
				resPage.put("resCategory", rs.getInt("RES_CATEGORY"));
				resPage.put("bzLocation", rs.getString("BZ_LOCATION"));
				resPage.put("bzOpenHours", rs.getTime("BZ_OPEN_HOURS"));
				resPage.put("bzCloseHours", rs.getTime("BZ_CLOSE_HOURS"));
				
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return resPage;
	}

	@Override
	public List<Map<String, Object>> getResComment(Integer resId) {
		List<Map<String, Object>> commentList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = conn.prepareStatement(GET_COMMENT);

			pstmt.setInt(1, resId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("resId", rs.getInt("RES_ID"));
				map.put("resComment", rs.getString("RES_COMMENT"));

				commentList.add(map);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return commentList;
	}

	@Override
	public List<Map<String, Object>> searchProduct(String searchPdt) {
		List<Map<String, Object>> resList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = conn.prepareStatement(SEARCHPRODUCT);

			pstmt.setString(1, "%" + searchPdt + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("rate", rs.getDouble("ROUND(AVG(RES_RATE),1)"));
				map.put("resId", rs.getInt("RES_ID"));
				map.put("resCategory", rs.getInt("RES_CATEGORY"));
				map.put("resName", rs.getString("RES_NAME"));

				resList.add(map);
			}

		} catch (SQLException se) {
			se.printStackTrace();
//			throw new RuntimeException("A database error occured when you selectByUserId. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return resList;
	}

	@Override
	public List<Map<String, Object>> selectByCategory(Integer resCategory) {
		List<Map<String, Object>> rateList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = conn.prepareStatement(SELECTBYCATEGORY);
			pstmt.setInt(1, resCategory);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("rate", rs.getDouble("ROUND(AVG(RES_RATE),1)"));
				map.put("resId", rs.getInt("RES_ID"));
				map.put("resCategory", rs.getInt("RES_CATEGORY"));
				map.put("resName", rs.getString("RES_NAME"));

				rateList.add(map);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured when you selectByUserId. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return rateList;
	}

	@Override
	public List<Map<String, Object>> getRate() {
		List<Map<String, Object>> rateList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = conn.prepareStatement(GETRATE);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("rate", rs.getDouble("ROUND(AVG(RES_RATE),1)"));
				map.put("resId", rs.getInt("RES_ID"));
				map.put("resCategory", rs.getInt("RES_CATEGORY"));
				map.put("resName", rs.getString("RES_NAME"));
				map.put("zipCode",rs.getInt("ZIP_CODE"));
//			

				rateList.add(map);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return rateList;
	}

	@Override
	public int insert(ResVO resVO) {
		// get connect
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, resVO.getResAccount());
			pstmt.setString(2, resVO.getResName());
			pstmt.setString(3, resVO.getResPassword());
			pstmt.setString(4, resVO.getOwnerTel());

			System.out.println("in ResDAOImpl im done");
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured when you insert. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(ResVO resVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, resVO.getResCategory());
			pstmt.setString(2, resVO.getResAccount());
			pstmt.setTimestamp(3, resVO.getUpdateTime());
			pstmt.setString(4, resVO.getResName());
			pstmt.setString(5, resVO.getResPassword());
			pstmt.setString(6, resVO.getResTel());
			pstmt.setBytes(7, resVO.getResPic());
			pstmt.setString(8, resVO.getOwnerName());
			pstmt.setString(9, resVO.getOwnerTel());
			pstmt.setString(10, resVO.getBzLocation());
			pstmt.setInt(11, resVO.getZipCode());
			pstmt.setTime(12, resVO.getBzOpenHours());
			pstmt.setTime(13, resVO.getBzCloseHours());
			pstmt.setInt(14, resVO.getBzWeekTime());
			pstmt.setInt(15, resVO.getResId());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured when you update. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer resId) {
		// get connect
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, resId);

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured when you delete. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public ResVO selectByResId(Integer resId) {
		ResVO resVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(SELECTBYRESID);

			pstmt.setInt(1, resId);

			rs = pstmt.executeQuery(); // 執行 SELECT 語句，但也只能執行查詢語句，執行後返回代表查詢結果的ResultSet

			while (rs.next()) {
				resVO = new ResVO();

				resVO.setResCategory(rs.getInt("RES_CATEGORY"));
				resVO.setResAccount(rs.getString("RES_ACCOUNT"));
				resVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				resVO.setResName(rs.getString("RES_NAME"));
				resVO.setResPassword(rs.getString("RES_PASSWORD"));
				resVO.setResTel(rs.getString("RES_TEL"));
				resVO.setResPic(rs.getBytes("RES_PIC"));
				resVO.setOwnerName(rs.getString("OWNER_NAME"));
				resVO.setOwnerTel(rs.getString("OWNER_TEL"));
				resVO.setBzLocation(rs.getString("BZ_LOCATION"));
				resVO.setZipCode(rs.getInt("ZIP_CODE"));
				resVO.setBzOpenHours(rs.getTime("BZ_OPEN_HOURS"));
				resVO.setBzCloseHours(rs.getTime("BZ_CLOSE_HOURS"));
				resVO.setBzWeekTime(rs.getInt("BZ_WEEK_TIME"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured when you selectByUserId. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return resVO;
	}

	@Override
	public List<ResVO> getAll() {
		List<ResVO> listResVO = new ArrayList<ResVO>();
		ResVO resVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(GETALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				resVO = new ResVO();

				resVO.setResId(rs.getInt("RES_ID"));
				resVO.setResCategory(rs.getInt("RES_CATEGORY"));
				resVO.setResAccount(rs.getString("RES_ACCOUNT"));
				resVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				resVO.setResName(rs.getString("RES_NAME"));
				resVO.setResPassword(rs.getString("RES_PASSWORD"));
				resVO.setResTel(rs.getString("RES_TEL"));
				resVO.setResPic(rs.getBytes("RES_PIC"));
				resVO.setOwnerName(rs.getString("OWNER_NAME"));
				resVO.setOwnerTel(rs.getString("OWNER_TEL"));
				resVO.setBzLocation(rs.getString("BZ_LOCATION"));
				resVO.setZipCode(rs.getInt("ZIP_CODE"));
				resVO.setBzOpenHours(rs.getTime("BZ_OPEN_HOURS"));
				resVO.setBzCloseHours(rs.getTime("BZ_CLOSE_HOURS"));
				resVO.setBzWeekTime(rs.getInt("BZ_WEEK_TIME"));

				listResVO.add(resVO); // Store the row in the list
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured when you selectByResId. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return listResVO;
	}

	@Override
	public ResVO selectByResAccount(String resAccount) {
		ResVO resVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(SELECTBYRESACCOUNT);

			pstmt.setString(1, resAccount);

			rs = pstmt.executeQuery(); // 執行 SELECT 語句，但也只能執行查詢語句，執行後返回代表查詢結果的ResultSet

			while (rs.next()) {
				resVO = new ResVO();
				resVO.setResId(rs.getInt("RES_ID"));
				resVO.setResCategory(rs.getInt("RES_CATEGORY"));
				resVO.setResAccount(rs.getString("RES_ACCOUNT"));
				resVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				resVO.setResName(rs.getString("RES_NAME"));
				resVO.setResPassword(rs.getString("RES_PASSWORD"));
				resVO.setResTel(rs.getString("RES_TEL"));
				resVO.setResPic(rs.getBytes("RES_PIC"));
				resVO.setOwnerName(rs.getString("OWNER_NAME"));
				resVO.setOwnerTel(rs.getString("OWNER_TEL"));
				resVO.setBzLocation(rs.getString("BZ_LOCATION"));
				resVO.setZipCode(rs.getInt("ZIP_CODE"));
				resVO.setBzOpenHours(rs.getTime("BZ_OPEN_HOURS"));
				resVO.setBzCloseHours(rs.getTime("BZ_CLOSE_HOURS"));
				resVO.setBzWeekTime(rs.getInt("BZ_WEEK_TIME"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured when you selectByUserId. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return resVO;
	}

	@Override
	public List<ResVO> isDuplicateAccount(String resAccount) {
		List<ResVO> listResVO = new ArrayList<ResVO>();
		ResVO resVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(ISDUPLICATEACCOUNT);

			pstmt.setString(1, resAccount);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				resVO = new ResVO();
				resVO.setResAccount(rs.getString("RES_ACCOUNT"));
				listResVO.add(resVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException(
					"A database error occured when you check isDuplicateAccount. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return listResVO;
	}

	@Override
	public String updatePassword(ResVO resVO) {
		// get connect
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(UPDATEPASSWORD);

			pstmt.setString(1, resVO.getResPassword());
			pstmt.setString(2, resVO.getResAccount());

			int numOfSuccess = pstmt.executeUpdate();
			System.out.println("我在ResDAOImpl的變數numOfSuccess" + numOfSuccess);
			if (numOfSuccess < 1) {
				System.out.println("小於1的結果 代表失敗");
				return "UpdateFailed";
			} else {
				System.out.println("大於1的結果 代表成功");
				return "UpdateCompleted";
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured when you updatePassword. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public Map<String, Object> updateResInfo(ResDto resDto) {
		String updateResInfo = "UPDATE MonFood.RES SET RES_CATEGORY = ?,RES_TEL = ?,RES_PIC = ? ,OWNER_NAME = ? ,BZ_LOCATION = ? ,ZIP_CODE = ?  WHERE RES_ID = ?";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(updateResInfo);

			pstmt.setInt(1, resDto.getResCategory());
			pstmt.setString(2, resDto.getResPhone());
			pstmt.setBytes(3, resDto.getResFile());
			pstmt.setString(4, resDto.getOwnerName());
			pstmt.setString(5, resDto.getCountry() + resDto.getDistrict() + resDto.getBzAdd());
			pstmt.setInt(6, resDto.getZipcode());
			pstmt.setInt(7, resDto.getResID());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				// 查詢剛剛新增成功的那筆資料

				return selectResInfo(resDto.getResID());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	@Override
	public List<ResVO> selectByResName(String resName) {
		List<ResVO> listResVO = new ArrayList<ResVO>();
		ResVO resVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(SELECTBYRESNAME);
			System.out.println("daoImpl = " + resName);
			pstmt.setString(1, resName);
			rs = pstmt.executeQuery(); // 執行 SELECT 語句，但也只能執行查詢語句，執行後返回代表查詢結果的ResultSet

			while (rs.next()) {
				resVO = new ResVO();
				resVO.setResId(rs.getInt("RES_ID"));
				resVO.setResCategory(rs.getInt("RES_CATEGORY"));
				resVO.setResAccount(rs.getString("RES_ACCOUNT"));
				resVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				resVO.setResName(rs.getString("RES_NAME"));
				resVO.setResPassword(rs.getString("RES_PASSWORD"));
				resVO.setResTel(rs.getString("RES_TEL"));
				resVO.setResPic(rs.getBytes("RES_PIC"));
				resVO.setOwnerName(rs.getString("OWNER_NAME"));
				resVO.setOwnerTel(rs.getString("OWNER_TEL"));
				resVO.setBzLocation(rs.getString("BZ_LOCATION"));
				resVO.setZipCode(rs.getInt("ZIP_CODE"));
				resVO.setBzOpenHours(rs.getTime("BZ_OPEN_HOURS"));
				resVO.setBzCloseHours(rs.getTime("BZ_CLOSE_HOURS"));
				resVO.setBzWeekTime(rs.getInt("BZ_WEEK_TIME"));
				listResVO.add(resVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured when you selectByUserId. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return listResVO;
	}

	@Override
	public List<ResVO> getResForZipcode() {
		List<ResVO> reslist = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = conn.prepareStatement(GETRESFORINDEX);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ResVO resVO = new ResVO();
				String rate = String.valueOf(rs.getDouble("ROUND(AVG(RES_RATE),1)"));
				String name = rs.getString("RES_NAME");
				String resName = rate + "_" + name;
				resVO.setResId(rs.getInt("RES_ID"));
				resVO.setResName(resName);
				resVO.setZipCode(rs.getInt("ZIP_CODE"));
				resVO.setResCategory(rs.getInt("RES_CATEGORY"));
				reslist.add(resVO);
				System.out.println("dao in while" + reslist);
//				Map<String, Object> map = new HashMap<>();
//				map.put("rate", rs.getDouble("AVG(RES_RATE)"));
//				map.put("resId",rs.getInt("RES_ID"));
//				map.put("resCategory",rs.getInt("RES_CATEGORY"));
//				map.put("resName",rs.getString("RES_NAME"));
//				map.put("bzLocation",rs.getString("BZ_LOCATION"));
//				map.put("bzOpenHours",rs.getString("BZ_OPEN_HOURS"));
//				map.put("bzCloseHours", rs.getString("BZ_CLOSE_HOURS"));
//				map.put("bzWeekTime", rs.getInt("BZ_WEEKTIME"));
//				rateList.add(map);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		System.out.println("DAO:" + reslist);
		return reslist;
	}

	public Map<String, Object> selectResInfo(Integer resID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Object> resMap = new HashMap<>();

		try {
			conn = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = conn.prepareStatement(SELECTRESINFO);
			pstmt.setInt(1, resID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				resMap.put("resID", rs.getInt("RES_ID"));
				resMap.put("resCategory", rs.getInt("RES_CATEGORY"));
				resMap.put("resAccount", rs.getString("RES_ACCOUNT"));
				resMap.put("resName", rs.getString("RES_NAME"));
				resMap.put("resTel", rs.getString("RES_TEL"));
				resMap.put("ownerName", rs.getString("OWNER_NAME"));
				resMap.put("ownerTel", rs.getString("OWNER_TEL"));
				resMap.put("bzLocation", rs.getString("BZ_LOCATION"));
				resMap.put("resCategoryName", rs.getString("RES_CATEGORY_NAME"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return resMap;
	}

}
