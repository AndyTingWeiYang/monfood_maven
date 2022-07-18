package com.model.promotelist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.model.del.MyData;
import com.model.order.OrderVO;
import com.model.promotelist.PromoteListDAO;
import com.model.promotelist.PromoteListVO;

import java.sql.Date;


public class PromoteListJDBCDAO implements PromoteListDAO{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/MonFood?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	private static final String INSERT_STMT = 
			"insert into MonFood.PROMOTE_LIST(PROMOTE_CODE, PROMOTE_PRICE, START_DATE, END_DATE, STATUS) "
			+ "values(?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
			"select * from PROMOTE_LIST order by PROMOTE_ID";
	private static final String GET_ONE_STMT = 
			"select * from PROMOTE_LIST where PROMOTE_ID = ?";
	private static final String DELETE = 
			"delete from PROMOTE_LIST where PROMOTE_ID = ?";
	private static final String UPDATE = "update PROMOTE_LIST "
				+ "set PROMOTE_CODE = ?, PROMOTE_PRICE = ?, START_DATE = ?, END_DATE = ?, STATUS = ? "
				+ "where PROMOTE_ID = ?";
		
	
	@Override
	public void insert(PromoteListVO promoteListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setInt(1, promoteListVO.getPromoteId());
			pstmt.setString(1, promoteListVO.getPromoteCode());
			pstmt.setInt(2, promoteListVO.getPromotePrice());
			pstmt.setDate(3, promoteListVO.getStartDate());
			pstmt.setDate(4, promoteListVO.getEndDate());
			pstmt.setInt(5, promoteListVO.getStatus());
			
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
	public List<PromoteListVO> getAll() {
		
		List<PromoteListVO> list = new ArrayList<PromoteListVO>();
		PromoteListVO promoteListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				promoteListVO = new PromoteListVO();
				promoteListVO.setPromoteId(rs.getInt("PROMOTE_ID"));
				promoteListVO.setPromoteCode(rs.getString("PROMOTE_CODE"));
				promoteListVO.setPromotePrice(rs.getInt("PROMOTE_PRICE"));
				promoteListVO.setStartDate(rs.getDate("START_DATE"));
				promoteListVO.setEndDate(rs.getDate("END_DATE"));
				promoteListVO.setStatus(rs.getInt("STATUS"));
				
				list.add(promoteListVO);
				
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
	public PromoteListVO findByPrimaryKey(Integer promoteId) {
		
		PromoteListVO promoteListVO = null;
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
				
				promoteListVO = new PromoteListVO();
				promoteListVO.setPromoteId(rs.getInt("PROMOTE_ID"));
				promoteListVO.setPromoteCode(rs.getString("PROMOTE_CODE"));
				promoteListVO.setPromotePrice(rs.getInt("PROMOTE_PRICE"));
				promoteListVO.setStartDate(rs.getDate("START_DATE"));
				promoteListVO.setEndDate(rs.getDate("END_DATE"));
				promoteListVO.setStatus(rs.getInt("STATUS"));
				
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
		return promoteListVO;
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
	public void update(PromoteListVO promoteListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, promoteListVO.getPromoteCode());
			pstmt.setInt(2, promoteListVO.getPromotePrice());
			pstmt.setDate(3, promoteListVO.getStartDate());
			pstmt.setDate(4, promoteListVO.getEndDate());
			pstmt.setInt(5, promoteListVO.getStatus());
			pstmt.setInt(6, promoteListVO.getPromoteId());
			
						
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
	
	public static void main(String[] args) throws ParseException {
		
		PromoteListDAO dao = new PromoteListJDBCDAO();
			
//		insert
		
//		PromoteListVO promoteListVOi = new PromoteListVO();
//		
//		promoteListVOi.setPromoteCode("聖誕佳節");
//		promoteListVOi.setPromotePrice(25);
//
//		String d1 = "2022-12-01";
//		java.util.Date formattedDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(d1);
//		java.sql.Date date1 = new Date(formattedDate1.getTime());
//		
//		String d2 = "2022-12-30";
//		java.util.Date formattedDate2 = new SimpleDateFormat("yyyy-MM-dd").parse(d2);
//		java.sql.Date date2 = new Date(formattedDate2.getTime());
//
//		promoteListVOi.setStartDate(date1);
//		promoteListVOi.setEndDate(date2);
//		promoteListVOi.setStatus(3);
//		dao.insert(promoteListVOi);
//		System.out.println("新增成功");
		
		
//		select all
		
//		List<PromoteListVO> list = dao.getAll();
//		for(PromoteListVO alist: list) {
//			System.out.print(alist.getPromoteId() + ",");
//			System.out.print(alist.getPromoteCode() + ",");
//			System.out.print(alist.getPromotePrice() + ",");
//			System.out.print(alist.getStartDate() + ",");
//			System.out.print(alist.getEndDate() + ",");
//			System.out.print(alist.getStatus() + ",");
//
//			System.out.println();
//		}
		
		
//		select one
		
//		PromoteListVO promoteListVO = dao.findByPrimaryKey(2);
//		System.out.print(promoteListVO.getPromoteId() + ",");
//		System.out.print(promoteListVO.getPromoteCode() + ",");
//		System.out.print(promoteListVO.getPromotePrice() + ",");
//		System.out.print(promoteListVO.getStartDate() + ",");
//		System.out.print(promoteListVO.getEndDate() + ",");
//		System.out.print(promoteListVO.getStatus() + ",");

		
//		update
		
//		PromoteListVO promoteListVOu = new PromoteListVO();
//		
//		promoteListVOu.setPromoteCode("端午立蛋");
//		promoteListVOu.setPromotePrice(30);
//		
//		String d3 = "2022-06-01";
//		java.util.Date formattedDate3 = new SimpleDateFormat("yyyy-MM-dd").parse(d3);
//		java.sql.Date date3 = new Date(formattedDate3.getTime());
//		
//		String d4 = "2022-06-30";
//		java.util.Date formattedDate4 = new SimpleDateFormat("yyyy-MM-dd").parse(d4);
//		java.sql.Date date4 = new Date(formattedDate4.getTime());
//		
//		promoteListVOu.setStartDate(date3);
//		promoteListVOu.setEndDate(date4);
//		promoteListVOu.setStatus(2);
//		promoteListVOu.setPromoteId(6);
//		dao.update(promoteListVOu);
//		System.out.println("更新成功");
		
//		delete
//		dao.delete(10);
//		System.out.println("刪除成功");
	}
}
