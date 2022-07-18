package com.model.promotedetail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.promotedetail.PromoteDetailDAO;
import com.model.promotedetail.PromoteDetailVO;

public class PromoteDetailJDBCDAO implements PromoteDetailDAO{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/MonFood?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	private static final String INSERT_STMT = 
		"insert into MonFood.PROMOTE_DETAIL(USER_ID, USED_STATUS) "
		+ "values(?,?)";
	private static final String GET_ALL_STMT = 
		"select * from PROMOTE_DETAIL order by PROMOTE_ID";
	private static final String GET_ONE_STMT = 
		"select * from PROMOTE_DETAIL where PROMOTE_ID = ?";
	private static final String DELETE = 
		"delete from PROMOTE_DETAIL where PROMOTE_ID = ?";
	private static final String UPDATE = "update PROMOTE_DETAIL "
			+ "set USED_STATUS = ? " 
			+ "where USER_ID = ? and PROMOTE_ID = ?";
	
	@Override
	public void insert(PromoteDetailVO promoteDetailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, promoteDetailVO.getPromoteId());
			pstmt.setInt(2, promoteDetailVO.getUserId());
			pstmt.setInt(3, promoteDetailVO.getUsedStatus());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<PromoteDetailVO> getAll() {
		
		List<PromoteDetailVO> list = new ArrayList<PromoteDetailVO>();
		PromoteDetailVO promoteDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				promoteDetailVO = new PromoteDetailVO();
				promoteDetailVO.setPromoteId(rs.getInt("PROMOTE_ID"));
				promoteDetailVO.setUserId(rs.getInt("USER_ID"));
				promoteDetailVO.setUsedStatus(rs.getInt("USED_STATUS"));
				
				list.add(promoteDetailVO);
				
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return list;
	}
	
	
	@Override
	public PromoteDetailVO findByPrimaryKey(Integer promoteId) {
		
		PromoteDetailVO promoteDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, promoteId);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				promoteDetailVO = new PromoteDetailVO();
				promoteDetailVO.setPromoteId(rs.getInt("PROMOTE_ID"));
				promoteDetailVO.setUserId(rs.getInt("USER_ID"));
				promoteDetailVO.setUsedStatus(rs.getInt("USED_STATUS"));
				
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(Integer promoteId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, promoteId);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
	public static void main(String[] args) {
		
		PromoteDetailDAO dao = new PromoteDetailJDBCDAO();
			
//		insert 應該是用不到???
//		PromoteDetailVO promoteDetailVOi = new PromoteDetailVO();
//		promoteDetailVOi.setPromoteId(1);
//		promoteDetailVOi.setUserId(1);
//		promoteDetailVOi.setUsedStatus(2);

//		dao.insert(promoteDetailVOi);
		
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
		
//		delete
//		dao.delete();
//		System.out.println("刪除成功");
	}
	
}
