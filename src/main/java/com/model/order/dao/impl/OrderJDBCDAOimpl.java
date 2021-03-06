package com.model.order.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.order.OrderVO;
import com.model.order.dao.OrderDAO;

public class OrderJDBCDAOimpl implements OrderDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/MonFood?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "Abc840924";
	
	private static final String INSERT_STMT = 
		"insert into MonFood.ORDER(USER_ID, RES_ID, DEL_ID, ORDER_STATUS, NOTE, USER_LOCATION, PRODUCT_KCAL_TOTAL, TOTAL, DEL_COST, USE_CASH, CREDIT_ID, BONUS, PROMOTE_ID) "
		+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"select * from `ORDER` order by ORDER_ID";
	private static final String GET_ONE_STMT = 
		"select * from `ORDER` where ORDER_ID = ?";
	private static final String DELETE = 
		"delete from `ORDER` where ORDER_ID = ?";
	private static final String UPDATE = "update `ORDER` "
			+ "set RATING = ?, RES_RATE = ?, DEL_RATE = ?, RES_COMMENT = ?, DEL_COMMENT = ?"
			+ "where ORDER_ID = ?";
	
	@Override
	public void insert(OrderVO orderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, orderVO.getUserId());
			pstmt.setInt(2, orderVO.getResId());
			pstmt.setInt(3, orderVO.getDelId());
			pstmt.setInt(4, orderVO.getOrderStatus());
			pstmt.setString(5, orderVO.getNote());
			pstmt.setString(6, orderVO.getUserLocation());
			pstmt.setInt(7, orderVO.getProductKcalTotal());
			pstmt.setInt(8, orderVO.getTotal());
			pstmt.setInt(9, orderVO.getDelCost());
			pstmt.setBoolean(10, orderVO.getUseCash());
			pstmt.setString(11, orderVO.getCreditId());
			pstmt.setInt(12, orderVO.getBonus());
			pstmt.setInt(13, orderVO.getPromoteId());
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
	public List<OrderVO> getAll() {
		
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setOrderId(rs.getInt("ORDER_ID"));
				orderVO.setUserId(rs.getInt("USER_ID"));
				orderVO.setResId(rs.getInt("RES_ID"));
				orderVO.setDelId(rs.getInt("DEL_ID"));
				orderVO.setOrderStatus(rs.getInt("ORDER_STATUS"));
				orderVO.setNote(rs.getString("NOTE"));
				orderVO.setUserLocation(rs.getString("USER_LOCATION"));
				orderVO.setOrderCreate(rs.getTimestamp("ORDER_CREATE"));
				orderVO.setOrderDone(rs.getTimestamp("ORDER_DONE"));
				orderVO.setProductKcalTotal(rs.getInt("PRODUCT_KCAL_TOTAL"));
				orderVO.setTotal(rs.getInt("TOTAL"));
				orderVO.setDelCost(rs.getInt("DEL_COST"));
				orderVO.setUseCash(rs.getBoolean("USE_CASH"));
				orderVO.setCreditId(rs.getString("CREDIT_ID"));
				orderVO.setBonus(rs.getInt("BONUS"));
				orderVO.setRating(rs.getBoolean("RATING"));
				orderVO.setResRate(rs.getDouble("RES_RATE"));
				orderVO.setDelRate(rs.getDouble("DEL_RATE"));
				orderVO.setResComment(rs.getString("RES_COMMENT"));
				orderVO.setDelComment(rs.getString("DEL_COMMENT"));
				orderVO.setPromoteId(rs.getInt("PROMOTE_ID"));
				
				list.add(orderVO);
				
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
	public OrderVO findByPrimaryKey(Integer orderId) {
		
		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, orderId);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				orderVO = new OrderVO();
				orderVO.setOrderId(rs.getInt("ORDER_ID"));
				orderVO.setUserId(rs.getInt("USER_ID"));
				orderVO.setResId(rs.getInt("RES_ID"));
				orderVO.setDelId(rs.getInt("DEL_ID"));
				orderVO.setOrderStatus(rs.getInt("ORDER_STATUS"));
				orderVO.setNote(rs.getString("NOTE"));
				orderVO.setUserLocation(rs.getString("USER_LOCATION"));
				orderVO.setOrderCreate(rs.getTimestamp("ORDER_CREATE"));
				orderVO.setOrderDone(rs.getTimestamp("ORDER_DONE"));
				orderVO.setProductKcalTotal(rs.getInt("PRODUCT_KCAL_TOTAL"));
				orderVO.setTotal(rs.getInt("TOTAL"));
				orderVO.setDelCost(rs.getInt("DEL_COST"));
				orderVO.setUseCash(rs.getBoolean("USE_CASH"));
				orderVO.setCreditId(rs.getString("CREDIT_ID"));
				orderVO.setBonus(rs.getInt("BONUS"));
				orderVO.setRating(rs.getBoolean("RATING"));
				orderVO.setResRate(rs.getDouble("RES_RATE"));
				orderVO.setDelRate(rs.getDouble("DEL_RATE"));
				orderVO.setResComment(rs.getString("RES_COMMENT"));
				orderVO.setDelComment(rs.getString("DEL_COMMENT"));
				orderVO.setPromoteId(rs.getInt("PROMOTE_ID"));

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
		return orderVO;
	}
	
	@Override
	public void delete(Integer orderId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, orderId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setBoolean(1, orderVO.getRating());
			pstmt.setDouble(2, orderVO.getResRate());
			pstmt.setDouble(3, orderVO.getDelRate());
			pstmt.setString(4, orderVO.getResComment());
			pstmt.setString(5, orderVO.getDelComment());
			pstmt.setInt(6, orderVO.getOrderId());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
	public static void main(String[] args) {
		
		OrderDAO dao = new OrderJDBCDAOimpl();
		
		// insert
		OrderVO orderVOi = new OrderVO();
		orderVOi.setUserId(1);
		orderVOi.setResId(1);
		orderVOi.setDelId(2);
		orderVOi.setOrderStatus(1);
		orderVOi.setNote("???");
		orderVOi.setUserLocation("?????????????????????????????????321???");
		orderVOi.setProductKcalTotal(123);
		orderVOi.setTotal(123);
		orderVOi.setDelCost(10);
		orderVOi.setUseCash(false);
		orderVOi.setCreditId("1234444444");
		orderVOi.setBonus(10);
		orderVOi.setPromoteId(1);
		dao.insert(orderVOi);
		
		// select all
		List<OrderVO> list = dao.getAll();
		for(OrderVO alist: list) {
			System.out.print(alist.getOrderId() + ",");
			System.out.print(alist.getUserId() + ",");
			System.out.print(alist.getResId() + ",");
			System.out.print(alist.getDelId() + ",");
			System.out.print(alist.getOrderStatus() + ",");
			System.out.print(alist.getNote() + ",");
			System.out.print(alist.getUserLocation() + ",");
			System.out.print(alist.getOrderCreate() + ",");
			System.out.print(alist.getOrderDone() + ",");
			System.out.print(alist.getProductKcalTotal() + ",");
			System.out.print(alist.getTotal() + ",");
			System.out.print(alist.getDelCost() + ",");
			System.out.print(alist.getUseCash() + ",");
			System.out.print(alist.getCreditId() + ",");
			System.out.print(alist.getBonus() + ",");
			System.out.print(alist.getRating() + ",");
			System.out.print(alist.getResRate() + ",");
			System.out.print(alist.getDelRate() + ",");
			System.out.print(alist.getResComment() + ",");
			System.out.print(alist.getDelComment() + ",");
			System.out.print(alist.getPromoteId());
			System.out.println();
		}
		
		// select one
		OrderVO orderVO = dao.findByPrimaryKey(1);
		System.out.print(orderVO.getOrderId() + ",");
		System.out.print(orderVO.getUserId() + ",");
		System.out.print(orderVO.getResId() + ",");
		System.out.print(orderVO.getDelId() + ",");
		System.out.print(orderVO.getOrderStatus() + ",");
		System.out.print(orderVO.getNote() + ",");
		System.out.print(orderVO.getUserLocation() + ",");
		System.out.print(orderVO.getOrderCreate() + ",");
		System.out.print(orderVO.getOrderDone() + ",");
		System.out.print(orderVO.getProductKcalTotal() + ",");
		System.out.print(orderVO.getTotal() + ",");
		System.out.print(orderVO.getDelCost() + ",");
		System.out.print(orderVO.getUseCash() + ",");
		System.out.print(orderVO.getCreditId() + ",");
		System.out.print(orderVO.getBonus() + ",");
		System.out.print(orderVO.getRating() + ",");
		System.out.print(orderVO.getResRate() + ",");
		System.out.print(orderVO.getDelRate() + ",");
		System.out.print(orderVO.getResComment() + ",");
		System.out.print(orderVO.getDelComment() + ",");
		System.out.print(orderVO.getPromoteId());
		
		// update
		OrderVO orderVOu = new OrderVO();
		orderVOu.setRating(true);
		orderVOu.setResRate(4.0);
		orderVOu.setDelRate(3.0);
		orderVOu.setResComment("?????????");
		orderVOu.setDelComment("?????????");
		orderVOu.setOrderId(6);
		dao.update(orderVOu);
		
		// delete
		dao.delete(6);
	}
}
