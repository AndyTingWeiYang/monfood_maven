package com.model.user.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.model.user.UserVO;
import com.model.user.dao.UserDAO;

public class UserDAOImpl implements UserDAO {
	static String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/MonFood?serverTimezone=Asia/Taipei";
	String USERID = "root";
	String PASSWORD = "password";

// SQL指令
	// 新增
	private static final String INSERT_STMT = "INSERT INTO MonFood.USER(USER_NAME,USER_ACCOUNT,USER_PASSWORD,USER_TEL,USER_PROFILE,BIRTHDAY,CALORIES,BUDGET,PROFILE_PIC,MONS_LEVEL,MONS_NAME, UPDATE_TIME) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	// 更新
	private static final String UPDATE = "UPDATE MonFood.USER SET USER_NAME = ?,USER_ACCOUNT = ?,USER_PASSWORD = ?,USER_TEL = ?,USER_PROFILE = ?,BIRTHDAY = ?,CALORIES = ?,BUDGET = ?,PROFILE_PIC = ?,MONS_LEVEL = ?,MONS_NAME = ?,UPDATE_TIME = ? WHERE USER_ID = ?";
	// 刪除
	private static final String DELETE = "DELETE FROM MonFood.USER WHERE USER_ID = ?";
	// 查詢一筆
	private static final String SELECTBYUSERID = "SELECT * FROM MonFood.USER WHERE USER_ID = ? ";
	// 查詢全部
	private static final String GETALL = "SELECT * FROM MonFood.USER ORDER BY USER_ID";
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(UserVO userVO) {
		// get connect
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, userVO.getUserName());
			pstmt.setString(2, userVO.getUserAccount());
			pstmt.setString(3, userVO.getUserPassword());
			pstmt.setString(4, userVO.getUserTel());
			pstmt.setString(5, userVO.getUserProfile());
			pstmt.setDate(6, userVO.getBirthday());
			pstmt.setInt(7, userVO.getCalories());
			pstmt.setInt(8, userVO.getBudget());
			pstmt.setBytes(9, userVO.getProfilePic());
			pstmt.setInt(10, userVO.getMonsLevel());
			pstmt.setString(11, userVO.getMonsName());
			pstmt.setTimestamp(12, userVO.getUpdateTime());

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
	public void update(UserVO userVO) {
		// get connect
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, userVO.getUserName());
			pstmt.setString(2, userVO.getUserAccount());
			pstmt.setString(3, userVO.getUserPassword());
			pstmt.setString(4, userVO.getUserTel());
			pstmt.setString(5, userVO.getUserProfile());
			pstmt.setDate(6, userVO.getBirthday());
			pstmt.setInt(7, userVO.getCalories());
			pstmt.setInt(8, userVO.getBudget());
			pstmt.setBytes(9, userVO.getProfilePic());
			pstmt.setInt(10, userVO.getMonsLevel());
			pstmt.setString(11, userVO.getMonsName());
			pstmt.setTimestamp(12, userVO.getUpdateTime());
			pstmt.setInt(13, userVO.getUserId());

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
	public void delete(Integer userId) {
		// get connect
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, userId);

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
	public UserVO selectByUserId(Integer userId) {

		UserVO userVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(SELECTBYUSERID);
			
			pstmt.setInt(1, userId);
			
			rs = pstmt.executeQuery();  // 執行 SELECT 語句，但也只能執行查詢語句，執行後返回代表查詢結果的ResultSet
			
			while(rs.next()) {
				userVO = new UserVO();
				userVO.setUserId(rs.getInt("USER_ID"));
				userVO.setUserName(rs.getString("USER_NAME"));
				userVO.setUserAccount(rs.getString("USER_ACCOUNT"));
				userVO.setUserPassword(rs.getString("USER_PASSWORD"));
				userVO.setUserTel(rs.getString("USER_TEL"));
				userVO.setUserProfile(rs.getString("USER_PROFILE"));
				userVO.setBirthday(rs.getDate("BIRTHDAY"));
				userVO.setCalories(rs.getInt("CALORIES"));
				userVO.setBudget(rs.getInt("BUDGET"));
				userVO.setProfilePic(rs.getBytes("PROFILE_PIC"));
				userVO.setMonsLevel(rs.getInt("MONS_LEVEL"));
				userVO.setMonsName(rs.getString("MONS_NAME"));
				userVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
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

		return userVO;
	}

	@Override
	public List<UserVO> getAll() {
		List<UserVO> listUserVO = new ArrayList<UserVO>();
		UserVO userVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(GETALL);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				userVO = new UserVO();
				userVO.setUserId(rs.getInt("USER_ID"));
				userVO.setUserName(rs.getString("USER_NAME"));
				userVO.setUserAccount(rs.getString("USER_ACCOUNT"));
				userVO.setUserPassword(rs.getString("USER_PASSWORD"));
				userVO.setUserTel(rs.getString("USER_TEL"));
				userVO.setUserProfile(rs.getString("USER_PROFILE"));
				userVO.setBirthday(rs.getDate("BIRTHDAY"));
				userVO.setCalories(rs.getInt("CALORIES"));
				userVO.setBudget(rs.getInt("BUDGET"));
//				userVO.setProfilePic(rs.getBytes("PROFILE_PIC"));
				userVO.setMonsLevel(rs.getInt("MONS_LEVEL"));
				userVO.setMonsName(rs.getString("MONS_NAME"));
				userVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				
				listUserVO.add(userVO); // Store the row in the list
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
		return listUserVO;
	}

}
