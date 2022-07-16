package com.orderdetail.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.order.OrderVO;
import com.order.dao.OrderDAO;
import com.order.dao.impl.OrderJDBCDAOimpl;
import com.orderdetail.OrderDetailVO;

public class OrderDetailDAOimpl {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/db01?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "Abc840924";

	private static final String INSERT_STMT = 
		"insert into MonFood.ORDER_DETAIL(PRODUCT_ID, ORDER_ID, NOTE, AMOUNT, ORDERED_PRICE"
		+ "values(?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"select * from ORDER_DETAIL order by PRODUCT_ID";
	private static final String GET_ONE_STMT = 
		"select * from ORDER_DETAIL where PRODUCT_ID = ? and ORDER_ID = ?";
	private static final String DELETE = 
		"delete from `ORDER` where PRODUCT_ID = ? and ORDER_ID = ?";
	private static final String UPDATE = "update ORDER_DETAIL ";
//		+ "set RATING = ?, RES_RATE = ?, DEL_RATE = ?, RES_COMMENT = ?, DEL_COMMENT = ?"
//		+ "where ORDER_ID = ?";

	public void insert(OrderDetailVO orderDetailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, orderDetailVO.getProductId());
			pstmt.setInt(2, orderDetailVO.getOrderId());
			pstmt.setString(3, orderDetailVO.getNote());
			pstmt.setInt(4, orderDetailVO.getAmount());
			pstmt.setInt(5, orderDetailVO.getOrderedPrice());
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
	
	public List<OrderDetailVO> getAll() {
		
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setProductId(rs.getInt("PRODUCT_ID"));
				orderDetailVO.setOrderId(rs.getInt("ORDER_ID"));
				orderDetailVO.setNote(rs.getString("NOTE"));
				orderDetailVO.setAmount(rs.getInt("AMOUNT"));
				orderDetailVO.setOrderedPrice(rs.getInt("ORDERED_PRICE"));
				
				list.add(orderDetailVO);
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
	
	public OrderDetailVO findByPrimaryKey(Integer productId, Integer orderId) {
		
		OrderDetailVO orderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, productId);
			pstmt.setInt(2, orderId);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setProductId(rs.getInt("PRODUCT_ID"));
				orderDetailVO.setOrderId(rs.getInt("ORDER_ID"));
				orderDetailVO.setNote(rs.getString("NOTE"));
				orderDetailVO.setAmount(rs.getInt("AMOUNT"));
				orderDetailVO.setOrderedPrice(rs.getInt("ORDERED_PRICE"));

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
		return orderDetailVO;
	}
	
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
		orderVOi.setNote("快");
		orderVOi.setUserLocation("台北市中正區濟南路一段321號");
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
		orderVOu.setResComment("很難吃");
		orderVOu.setDelComment("速度快");
		orderVOu.setOrderId(6);
		dao.update(orderVOu);
		
		// delete
		dao.delete(6);
	}
}
