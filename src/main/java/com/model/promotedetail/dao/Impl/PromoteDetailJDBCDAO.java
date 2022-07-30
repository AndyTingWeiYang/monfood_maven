package com.model.promotedetail.dao.Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.promotedetail.PromoteDetailVO;
import com.model.promotedetail.dao.PromoteDetailDAO;

public class PromoteDetailJDBCDAO implements PromoteDetailDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/MonFood?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "insert into MonFood.PROMOTE_DETAIL(PROMOTE_ID, USER_ID) "
			+ "values(?,?)";
	private static final String GET_ONE_STMT = "select * from PROMOTE_DETAIL where PROMOTE_ID = ? and USER_ID =?";
	private static final String UPDATE = "update PROMOTE_DETAIL " + "set USED_STATUS = ? "
			+ "where USER_ID = ? and PROMOTE_ID = ?";
	private static final String FIND_BY_CODE = "SELECT * FROM PROMOTE_DETAIL WHERE PROMOTE_CODE = ?";

	@Override
	public Integer insert(PromoteDetailVO promoteDetailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, promoteDetailVO.getPromoteId());
			pstmt.setInt(2, promoteDetailVO.getUserId());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return 1;
	}



	@Override
	public PromoteDetailVO findByID(Integer promoteId, Integer userId) {

		PromoteDetailVO promoteDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, promoteId);
			pstmt.setInt(2, userId);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {

				promoteDetailVO = new PromoteDetailVO();
				promoteDetailVO.setPromoteId(rs.getInt("PROMOTE_ID"));
				promoteDetailVO.setUserId(rs.getInt("USER_ID"));
				promoteDetailVO.setUsedStatus(rs.getInt("USED_STATUS"));

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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return promoteDetailVO;
	}

	@Override
	public void update(PromoteDetailVO promoteDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, promoteDetailVO.getUsedStatus());
			pstmt.setInt(2, promoteDetailVO.getUserId());
			pstmt.setInt(3, promoteDetailVO.getPromoteId());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public PromoteDetailVO findByCode(String promoteCode) {

		PromoteDetailVO promoteDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_CODE);

			pstmt.setString(1, promoteCode);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				promoteDetailVO = new PromoteDetailVO();
				promoteDetailVO.setPromoteId(rs.getInt("PROMOTE_ID"));
				promoteDetailVO.setUserId(rs.getInt("USER_ID"));
				promoteDetailVO.setUsedStatus(rs.getInt("USED_STATUS"));

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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return promoteDetailVO;
	}

	public static void main(String[] args) {

		PromoteDetailDAO dao = new PromoteDetailJDBCDAO();

//		select all
//		List<PromoteDetailVO> list = dao.getAll();
//		for(PromoteDetailVO alist: list) {
//			System.out.print(alist.getPromoteId() + ",");
//			System.out.print(alist.getUserId() + ",");
//			System.out.print(alist.getUsedStatus() + ",");
//			System.out.println();
//		}

//		select one
//		PromoteDetailVO promoteDetailVO = dao.findByPrimaryKey(1);
//		System.out.print(promoteDetailVO.getPromoteId() + ",");
//		System.out.print(promoteDetailVO.getUserId() + ",");
//		System.out.print(promoteDetailVO.getUsedStatus() + ",");

//		update
//		PromoteDetailVO promoteDetailVOu = new PromoteDetailVO();
//		promoteDetailVOu.setUsedStatus(2);
//		promoteDetailVOu.setUserId(3);
//		promoteDetailVOu.setPromoteId(2);
//
//		dao.update(promoteDetailVOu);
//		System.out.println("更新成功");

	}


}
