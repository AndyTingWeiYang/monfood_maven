package com.model.res.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	private static final String GETALL = "SELECT * FROM MonFood.RES ORDER BY RES_ID";
	// 查詢是否有此會員帳號
	private static final String ISDUPLICATEACCOUNT = "SELECT RES_ACCOUNT FROM MonFood.RES WHERE RES_ACCOUNT = ? ";
	// 使用餐廳分類查詢一筆多筆資料
	private static final String SELECTBYCATEGORY = "SELECT * FROM RES WHERE RES_CATEGORY = ? ";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
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
	public List<ResVO> selectByCategory(Integer resCategory) {
		List<ResVO> list = new ArrayList<>();
		ResVO resVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(SELECTBYCATEGORY);

			pstmt.setInt(1, resCategory);

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

		return list;
	}

	@Override
	public boolean updateResInfo(ResDto resDto) {
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
				return true;
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

		return false;
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
}
