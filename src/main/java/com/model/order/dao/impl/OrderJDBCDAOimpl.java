package com.model.order.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.order.OrderVO;
import com.model.order.dao.OrderDAO;

public class OrderJDBCDAOimpl implements OrderDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/MonFood?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	private static final String INSERT_STMT = 
		"insert into MonFood.ORDER(USER_ID, RES_ID, NOTE, USER_LOCATION, PRODUCT_KCAL_TOTAL, TOTAL, DEL_COST, USE_CASH, CREDIT_ID, DISCOUNT) "
		+ "values(?,?,?,?,?,?,?,?,?,?)";
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
	public Integer insert(OrderVO orderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		Integer generatedKey;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, orderVO.getUserId());
			pstmt.setInt(2, orderVO.getResId());
			pstmt.setString(3, orderVO.getNote());
			pstmt.setString(4, orderVO.getUserLocation());
			pstmt.setInt(5, orderVO.getProductKcalTotal());
			pstmt.setInt(6, orderVO.getTotal());
			pstmt.setInt(7, orderVO.getDelCost());
			pstmt.setBoolean(8, orderVO.getUseCash());
			pstmt.setString(9, orderVO.getCreditId());
			pstmt.setInt(10, orderVO.getDiscount());
			pstmt.executeUpdate();
			ResultSet rs=pstmt.getGeneratedKeys();
			rs.next();
			generatedKey = rs.getInt(1);

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
		
		return generatedKey;
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
				orderVO.setDiscount(rs.getInt("DISCOUNT"));
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
				orderVO.setDiscount(rs.getInt("DISCOUNT"));
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
		orderVOi.setNote("快");
		orderVOi.setUserLocation("台北市中正區濟南路一段321號");
		orderVOi.setProductKcalTotal(123);
		orderVOi.setTotal(123);
		orderVOi.setDelCost(10);
		orderVOi.setUseCash(false);
		orderVOi.setCreditId("1234444444");
		orderVOi.setDiscount(10);
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
			System.out.print(alist.getDiscount() + ",");
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
		System.out.print(orderVO.getDiscount() + ",");
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
