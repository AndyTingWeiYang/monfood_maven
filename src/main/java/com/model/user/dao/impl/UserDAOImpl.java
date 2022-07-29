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
	private static final String INSERT_STMT = "INSERT INTO MonFood.USER(USER_NAME,USER_ACCOUNT,USER_PASSWORD,USER_TEL,BIRTHDAY,UPDATE_TIME) VALUES (?,?,?,?,?,?)";
	// 使用會員帳號更新密碼(忘記密碼)
	private static final String UPDATEPASSWORD = "UPDATE MonFood.USER SET USER_PASSWORD = ? WHERE USER_ACCOUNT = ?";
	// 使用會員帳號更新帳號是否生效(更新帳號狀態)
	private static final String UPDATEPACCOUNTSTATUS = "UPDATE MonFood.USER SET USER_ACCOUNT_STATUS = 1 WHERE USER_ACCOUNT = ?";
	// 刪除
	private static final String DELETE = "DELETE FROM MonFood.USER WHERE USER_ID = ?";
	// 使用會員編號查詢一筆資料
	private static final String SELECTBYUSERID = "SELECT * FROM MonFood.USER WHERE USER_ID = ? ";
	// 使用會員帳號查詢一筆資料
	private static final String SELECTBYUSERACCOUNT = "SELECT * FROM MonFood.USER WHERE USER_ACCOUNT = ? ";
	// 查詢全部
	private static final String GETALL = "SELECT * FROM MonFood.USER ORDER BY USER_ID";
	// 查詢全部會員編號
	private static final String DETALLUSERID = "SELECT USER_ID FROM MonFood.USER ORDER BY USER_ID";
	// 查詢是否有此會員帳號
	private static final String ISDUPLICATEACCOUNT = "SELECT USER_ACCOUNT FROM MonFood.USER WHERE USER_ACCOUNT = ? ";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public int insert(UserVO userVO) {
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
			pstmt.setDate(5, userVO.getBirthday());
			pstmt.setTimestamp(6, userVO.getUpdateTime());

			System.out.println("in UserDAOImpl im done");
			return pstmt.executeUpdate();
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
	public String updatePassword(UserVO userVO) {
		// get connect
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(UPDATEPASSWORD);

			pstmt.setString(1, userVO.getUserPassword());
			pstmt.setString(2, userVO.getUserAccount());
//			pstmt.setString(1, userVO.getUserName());
//			pstmt.setString(2, userVO.getUserAccount());
//			pstmt.setString(3, userVO.getUserTel());
//			pstmt.setString(4, userVO.getUserProfile());
//			pstmt.setDate(5, userVO.getBirthday());
//			pstmt.setInt(6, userVO.getCalories());
//			pstmt.setInt(7, userVO.getBudget());
//			pstmt.setBytes(8, userVO.getProfilePic());
//			pstmt.setInt(9, userVO.getMonsLevel());
//			pstmt.setString(10, userVO.getMonsName());
//			pstmt.setTimestamp(11, userVO.getUpdateTime());

			int numOfSuccess = pstmt.executeUpdate();
			System.out.println("我在UserDAOImpl的變數numOfSuccess" + numOfSuccess);
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
	public String updateAccountStatus(String userAccount) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(UPDATEPACCOUNTSTATUS);
			
			pstmt.setString(1, userAccount);
			
			int checkAccountStatus = pstmt.executeUpdate();
			System.out.println("我在UserDAOImpl的變數checkAccountStatus：" + checkAccountStatus);
			if (checkAccountStatus < 1) {
				System.out.println("小於1的結果 代表失敗");
				return "CheckFailed";
			} else {
				System.out.println("大於1的結果 代表成功");
				return "CheckCompleted";
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured when you updateAccountStatus. " + se.getMessage());
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

			rs = pstmt.executeQuery(); // 執行 SELECT 語句，但也只能執行查詢語句，執行後返回代表查詢結果的ResultSet

			while (rs.next()) {
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

			while (rs.next()) {
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
//				userVO.setProfilePic(rs.getBytes("PROFILE_PIC"));  //圖片位元資料太大 如果印出來會超出位元輸出上限 所以先註解
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

	@Override
	public List<Integer> getAllUserId() {

		List<Integer> listUserID = new ArrayList<Integer>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(DETALLUSERID);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				listUserID.add(rs.getInt("USER_ID")); // Store the row in the list
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
		return listUserID;
	}

	@Override
	public List<UserVO> isDuplicateAccount(String userAccount) {
		List<UserVO> listUserVO = new ArrayList<UserVO>();
		UserVO userVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(ISDUPLICATEACCOUNT);

			pstmt.setString(1, userAccount);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				userVO = new UserVO();
				userVO.setUserAccount(rs.getString("USER_ACCOUNT"));
				listUserVO.add(userVO);
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
		return listUserVO;
	}

	@Override
	public UserVO selectByUserAccount(String userAccount) {
		UserVO userVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USERID, PASSWORD);
			pstmt = con.prepareStatement(SELECTBYUSERACCOUNT);

			pstmt.setString(1, userAccount);

			rs = pstmt.executeQuery(); // 執行 SELECT 語句，但也只能執行查詢語句，執行後返回代表查詢結果的ResultSet

			while (rs.next()) {
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

}
