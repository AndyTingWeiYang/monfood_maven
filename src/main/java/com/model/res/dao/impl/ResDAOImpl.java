package com.model.res.dao.impl;

import java.util.*;
import java.sql.*;

import com.model.res.ResVO;
import com.model.res.dao.ResDAO;

public class ResDAOImpl implements ResDAO {
	static String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/MonFood?serverTimezone=Asia/Taipei";
	String USERID = "root";
	String PASSWORD = "password";

// SQL指令
	// 新增
	private static final String INSERT_STMT = "INSERT INTO MonFood.RES(RES_CATEGORY,RES_ACCOUNT,ACCOUNT_NAME,BANK_CODE,BANK_ACCOUNT,BANK_NAME,UPDATE_TIME,RES_NAME,RES_PASSWORD,RES_TEL,RES_MAIL,BZ_LICENCE,RES_PIC,OWNER_NAME,OWNER_TEL,OWNER_ID,BZ_LOCATION,ZIP_CODE,BZ_OPEN_HOURS,BZ_CLOSE_HOURS,BZ_WEEK_TIME,RES_STATUS) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	// 更新
	private static final String UPDATE = "UPDATE MonFood.RES SET RES_CATEGORY = ?,RES_ACCOUNT = ?,ACCOUNT_NAME = ?,BANK_CODE = ?,BANK_ACCOUNT = ?,BANK_NAME = ?,UPDATE_TIME = ?,RES_NAME = ?,RES_PASSWORD = ?,RES_TEL = ?,RES_MAIL = ?,BZ_LICENCE = ? ,RES_PIC = ? ,OWNER_NAME = ? ,OWNER_TEL = ? ,OWNER_ID = ? ,BZ_LOCATION = ? ,ZIP_CODE = ? ,BZ_OPEN_HOURS = ? ,BZ_CLOSE_HOURS = ? ,BZ_WEEK_TIME = ? ,RES_STATUS = ? WHERE RES_ID = ?";
	// 刪除
	private static final String DELETE = "DELETE FROM MonFood.RES WHERE RES_ID = ?";
	// 查詢一筆
	private static final String SELECTBYRESID = "SELECT * FROM MonFood.RES WHERE RES_ID = ? ";
	// 查詢全部
	private static final String GETALL = "SELECT * FROM MonFood.RES ORDER BY RES_ID";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(ResVO resVO) {
		// get connect
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setInt(1, resVO.getResCategory());
//			pstmt.setString(2, resVO.getResAccount());
//			pstmt.setString(3, resVO.getAccountName());
//			pstmt.setString(4, resVO.getBankCode());
//			pstmt.setString(5, resVO.getBankAccount());
//			pstmt.setString(6, resVO.getBankName());
//			pstmt.setTimestamp(7, resVO.getUpdateTime());
//			pstmt.setString(8, resVO.getResName());
//			pstmt.setString(9, resVO.getResPassword());
//			pstmt.setString(10, resVO.getResTel());
//			pstmt.setString(11, resVO.getResMail());
//			pstmt.setBytes(12, resVO.getBzLicence());
//			pstmt.setBytes(13, resVO.getResPic());
//			pstmt.setString(14, resVO.getOwnerName());
//			pstmt.setString(15, resVO.getOwnerTel());
//			pstmt.setBytes(16, resVO.getOwnerId());
//			pstmt.setInt(17, resVO.getZipCode());
//			pstmt.setTime(18, resVO.getBzOpenHours());
//			pstmt.setTime(19, resVO.getBzCloseHours());
//			pstmt.setInt(20, resVO.getBzWeekTime());
//			pstmt.setInt(21, resVO.getResStatus());

			pstmt.setInt(1, resVO.getResCategory());
			pstmt.setString(2, resVO.getResAccount());
			pstmt.setString(3, resVO.getAccountName());
			pstmt.setString(4, resVO.getBankCode());
			pstmt.setString(5, resVO.getBankAccount());
			pstmt.setString(6, resVO.getBankName());
			pstmt.setTimestamp(7, resVO.getUpdateTime());
			pstmt.setString(8, resVO.getResName());
			pstmt.setString(9, resVO.getResPassword());
			pstmt.setString(10, resVO.getResTel());
			pstmt.setString(11, resVO.getResMail());
			pstmt.setBytes(12, resVO.getBzLicence());
			pstmt.setBytes(13, resVO.getResPic());
			pstmt.setString(14, resVO.getOwnerName());
			pstmt.setString(15, resVO.getOwnerTel());
			pstmt.setBytes(16, resVO.getOwnerId());
			pstmt.setString(17, resVO.getBzLocation());
			pstmt.setInt(18, resVO.getZipCode());
			pstmt.setTime(19, resVO.getBzOpenHours());
			pstmt.setTime(20, resVO.getBzCloseHours());
			pstmt.setInt(21, resVO.getBzWeekTime());
			pstmt.setInt(22, resVO.getResStatus());
			pstmt.executeUpdate();
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured when you insert. " + se.getMessage());
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
	public void update(ResVO resVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, resVO.getResCategory());
			pstmt.setString(2, resVO.getResAccount());
			pstmt.setString(3, resVO.getAccountName());
			pstmt.setString(4, resVO.getBankCode());
			pstmt.setString(5, resVO.getBankAccount());
			pstmt.setString(6, resVO.getBankName());
			pstmt.setTimestamp(7, resVO.getUpdateTime());
			pstmt.setString(8, resVO.getResName());
			pstmt.setString(9, resVO.getResPassword());
			pstmt.setString(10, resVO.getResTel());
			pstmt.setString(11, resVO.getResMail());
			pstmt.setBytes(12, resVO.getBzLicence());
			pstmt.setBytes(13, resVO.getResPic());
			pstmt.setString(14, resVO.getOwnerName());
			pstmt.setString(15, resVO.getOwnerTel());
			pstmt.setBytes(16, resVO.getOwnerId());
			pstmt.setString(17, resVO.getBzLocation());
			pstmt.setInt(18, resVO.getZipCode());
			pstmt.setTime(19, resVO.getBzOpenHours());
			pstmt.setTime(20, resVO.getBzCloseHours());
			pstmt.setInt(21, resVO.getBzWeekTime());
			pstmt.setInt(22, resVO.getResStatus());
			pstmt.setInt(23, resVO.getResId());

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
				resVO.setAccountName(rs.getString("ACCOUNT_NAME"));
				resVO.setBankCode(rs.getString("BANK_CODE"));
				resVO.setBankAccount(rs.getString("BANK_CODE"));
				resVO.setBankName(rs.getString("BANK_NAME"));
				resVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				resVO.setResName(rs.getString("RES_NAME"));
				resVO.setResPassword(rs.getString("RES_PASSWORD"));
				resVO.setResTel(rs.getString("RES_TEL"));
				resVO.setResMail(rs.getString("RES_MAIL"));
				resVO.setBzLicence(rs.getBytes("BZ_LICENCE"));
				resVO.setResPic(rs.getBytes("RES_PIC"));
				resVO.setOwnerName(rs.getString("OWNER_NAME"));
				resVO.setOwnerTel(rs.getString("OWNER_TEL"));
				resVO.setOwnerId(rs.getBytes("OWNER_ID"));
				resVO.setBzLocation(rs.getString("BZ_LOCATION"));
				resVO.setZipCode(rs.getInt("ZIP_CODE"));
				resVO.setBzOpenHours(rs.getTime("BZ_OPEN_HOURS"));
				resVO.setBzCloseHours(rs.getTime("BZ_CLOSE_HOURS"));
				resVO.setBzWeekTime(rs.getInt("BZ_WEEK_TIME"));
				resVO.setResStatus(rs.getInt("RES_STATUS"));
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
				resVO.setAccountName(rs.getString("ACCOUNT_NAME"));
				resVO.setBankCode(rs.getString("BANK_CODE"));
				resVO.setBankAccount(rs.getString("BANK_CODE"));
				resVO.setBankName(rs.getString("BANK_NAME"));
				resVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				resVO.setResName(rs.getString("RES_NAME"));
				resVO.setResPassword(rs.getString("RES_PASSWORD"));
				resVO.setResTel(rs.getString("RES_TEL"));
				resVO.setResMail(rs.getString("RES_MAIL"));
//				resVO.setBzLicence(rs.getBytes("BZ_LICENCE"));
//				resVO.setResPic(rs.getBytes("RES_PIC"));
				resVO.setOwnerName(rs.getString("OWNER_NAME"));
				resVO.setOwnerTel(rs.getString("OWNER_TEL"));
//				resVO.setOwnerId(rs.getBytes("OWNER_ID"));
				resVO.setBzLocation(rs.getString("BZ_LOCATION"));
				resVO.setZipCode(rs.getInt("ZIP_CODE"));
				resVO.setBzOpenHours(rs.getTime("BZ_OPEN_HOURS"));
				resVO.setBzCloseHours(rs.getTime("BZ_CLOSE_HOURS"));
				resVO.setBzWeekTime(rs.getInt("BZ_WEEK_TIME"));
				resVO.setResStatus(rs.getInt("RES_STATUS"));

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

}
