package com.model.orderdetail.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.orderdetail.OrderDetailVO;
import com.model.orderdetail.dao.OrderDetailDAO;

public class OrderDetailDAOimpl implements OrderDetailDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/MonFood?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = 
		"insert into MonFood.ORDER_DETAIL(PRODUCT_ID, ORDER_ID, AMOUNT, ORDERED_PRICE)"
		+ "values(?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"select * from ORDER_DETAIL order by PRODUCT_ID";
	private static final String GET_ONE_STMT = 
		"select * from ORDER_DETAIL where PRODUCT_ID = ? and ORDER_ID = ?";
	private static final String DELETE = 
		"delete from ORDER_DETAIL where PRODUCT_ID = ? and ORDER_ID = ?";
	private static final String UPDATE = "update ORDER_DETAIL set AMOUNT = ?, ORDERED_PRICE = ? where PRODUCT_ID = ? and ORDER_ID = ?";

	@Override
	public void insert(OrderDetailVO orderDetailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, orderDetailVO.getProductId());
			pstmt.setInt(2, orderDetailVO.getOrderId());
			pstmt.setInt(3, orderDetailVO.getAmount());
			pstmt.setInt(4, orderDetailVO.getOrderedPrice());
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
	
	@Override
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
	
	@Override
	public void delete(Integer productId, Integer orderId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, productId);
			pstmt.setInt(2, orderId);

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
	public void update(OrderDetailVO orderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, orderDetailVO.getAmount());
			pstmt.setInt(2, orderDetailVO.getOrderedPrice());
			pstmt.setInt(3, orderDetailVO.getProductId());
			pstmt.setInt(4, orderDetailVO.getOrderId());
			
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
		
		OrderDetailDAO dao = new OrderDetailDAOimpl();
		
		// insert
		OrderDetailVO orderDetailVOi = new OrderDetailVO(); 
		orderDetailVOi.setProductId(2);
		orderDetailVOi.setOrderId(1);
		orderDetailVOi.setAmount(10);
		orderDetailVOi.setOrderedPrice(100);
		dao.insert(orderDetailVOi);
		System.out.println("insert success");
		System.out.println("=============");
		
		// select all
		List<OrderDetailVO> list = dao.getAll();
		System.out.println("select all");
		for(OrderDetailVO alist: list) {
			System.out.print(alist.getProductId() + ",");
			System.out.print(alist.getOrderId() + ",");
			System.out.print(alist.getAmount() + ",");
			System.out.print(alist.getOrderedPrice() + ",");
			System.out.println();
		}
		System.out.println("=============");
		
		// select one
		OrderDetailVO orderDetailVO = dao.findByPrimaryKey(2,1);
		System.out.println("select one");
		System.out.print(orderDetailVO.getProductId() + ",");
		System.out.print(orderDetailVO.getOrderId() + ",");
		System.out.print(orderDetailVO.getAmount() + ",");
		System.out.print(orderDetailVO.getOrderedPrice());
		System.out.println();
		System.out.println("=============");
		
		// update
		OrderDetailVO orderDetailVOu = new OrderDetailVO();
		orderDetailVOu.setAmount(12);
		orderDetailVOu.setOrderedPrice(1000);
		orderDetailVOu.setProductId(2);
		orderDetailVOu.setOrderId(1);
		dao.update(orderDetailVOu);
		System.out.println("update success");
		System.out.println("=============");
		
		// delete
		dao.delete(2,1);
		System.out.println("delete success");
		System.out.println("=============");
	}
}
