package com.model.order.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.model.order.OrderVO;
import com.model.order.dao.OrderDAO;
import com.model.product.ProductVo;
import com.model.reception.OrderStatus;
import com.model.res.ResVO;

public class OrderJDBCDAOimpl implements OrderDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/MonFood?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "insert into MonFood.ORDER(USER_ID, RES_ID, NOTE, USER_LOCATION, PRODUCT_KCAL_TOTAL, TOTAL, DEL_COST, USE_CASH, CREDIT_ID, DISCOUNT, PROMOTE_ID) "
			+ "values(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String INSERT_NO_PROMOTE = "insert into MonFood.ORDER(USER_ID, RES_ID, NOTE, USER_LOCATION, PRODUCT_KCAL_TOTAL, TOTAL, DEL_COST, USE_CASH, CREDIT_ID, DISCOUNT) "
			+ "values(?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "select * from `ORDER` order by ORDER_ID";
	private static final String GET_ONE_STMT = "select * from `ORDER` where ORDER_ID = ?";
	private static final String DELETE = "delete from `ORDER` where ORDER_ID = ?";
	private static final String UPDATE = "update `ORDER` "
			+ "set RES_RATE = ?, DEL_RATE = ?, RES_COMMENT = ?, DEL_COMMENT = ?" + "where ORDER_ID = ?";
	private static final String GET_ORDER_TIMES = "select USER_ID, count(1) as ORDER_TIMES from `ORDER` where USER_ID = ? group by USER_ID";
	private static final String GET_RATING = "SELECT AVG(RES_RATE) FROM `ORDER` where RES_ID = ?";
	private static final String GET_ALL_BY_ID = "SELECT o.ORDER_ID, o.USER_ID, o.RES_ID, o.DEL_ID, o.ORDER_DONE, o.TOTAL, o.RATING, o.RES_RATE, o.DEL_RATE, o.RES_COMMENT, o.DEL_COMMENT, r.RES_NAME, r.RES_PIC FROM `ORDER` o\r\n"
			+ "join RES r\r\n" + "on o.RES_ID = r.RES_ID\r\n" + "join DEL dl\r\n" + "on o.DEL_ID = dl.DEL_ID\r\n"
			+ "where o.USER_ID =?\r\n" + "order by o.ORDER_ID";
	private static final String GET_PRODUCT_BY_ID = "SELECT o.ORDER_ID, p.PRODUCT_NAME, p.PRODUCT_KCAL FROM `ORDER` o\r\n"
			+ "join ORDER_DETAIL d\r\n" + "on d.ORDER_ID = o.ORDER_ID\r\n" + "join PRODUCT p\r\n"
			+ "on p.PRODUCT_ID = d.PRODUCT_ID\r\n" + "where o.USER_ID =?\r\n" + "order by o.ORDER_ID";
	private static final String UPDATE_DELID = "update `ORDER` set DEL_ID = ? where ORDER_ID = ?";
	
	@Override
	public Double getRating(Integer resId) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Double rating = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_RATING);

			pstmt.setInt(1, resId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rating = rs.getDouble("AVG(RES_RATE)");
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

		return rating;
	}

	@Override
	public Integer getOrderTimes(Integer userId) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer orderTimes = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ORDER_TIMES);

			pstmt.setInt(1, userId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderTimes = rs.getInt("ORDER_TIMES");
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
		return orderTimes;
	}

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
			pstmt.setInt(11, orderVO.getPromoteId());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			generatedKey = rs.getInt(1);

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

		return generatedKey;
	}

	@Override
	public Integer insertNoPromote(OrderVO orderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		Integer generatedKey;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_NO_PROMOTE, Statement.RETURN_GENERATED_KEYS);

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
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			generatedKey = rs.getInt(1);

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
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

			pstmt.setDouble(1, orderVO.getResRate());
			pstmt.setDouble(2, orderVO.getDelRate());
			pstmt.setString(3, orderVO.getResComment());
			pstmt.setString(4, orderVO.getDelComment());
			pstmt.setInt(5, orderVO.getOrderId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void updateDelId(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_DELID);

			pstmt.setInt(1, orderVO.getDelId());
			pstmt.setInt(2, orderVO.getOrderId());

			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<OrderVO> getAllById(Integer userId) {

		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;
		ProductVo productVo = null;
		ResVO resVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BY_ID);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderVO = new OrderVO();

				resVO = new ResVO();
				resVO.setResName(rs.getString("RES_NAME"));
				resVO.setResPic(rs.getBytes("RES_PIC"));

				orderVO.setOrderId(rs.getInt("ORDER_ID"));
				orderVO.setUserId(rs.getInt("USER_ID"));
				orderVO.setResId(rs.getInt("RES_ID"));
				orderVO.setDelId(rs.getInt("DEL_ID"));
				orderVO.setOrderDone(rs.getTimestamp("ORDER_DONE"));
				orderVO.setTotal(rs.getInt("TOTAL"));
				orderVO.setRating(rs.getBoolean("RATING"));
				orderVO.setResRate(rs.getDouble("RES_RATE"));
				orderVO.setDelRate(rs.getDouble("DEL_RATE"));
				orderVO.setResComment(rs.getString("RES_COMMENT"));
				orderVO.setDelComment(rs.getString("DEL_COMMENT"));
				orderVO.setResVO(resVO);
				;
				list.add(orderVO);

			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<OrderVO> getAllProductById(Integer userId) {

		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;
		ProductVo productVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_PRODUCT_BY_ID);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderVO = new OrderVO();
				productVo = new ProductVo();
				productVo.setProductName(rs.getString("PRODUCT_NAME"));
				productVo.setProductKcal(rs.getInt("PRODUCT_KCAL"));

				orderVO.setOrderId(rs.getInt("ORDER_ID"));
				orderVO.setProductVo(productVo);
				list.add(orderVO);

			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<Map<String, Object>> resFindOrderService(OrderVO orderVO) {
		String resFindOrder = "SELECT * FROM MonFood.ORDER a " + " join MonFood.RES b " + " on (a.RES_ID = b.RES_ID) "
				+ " join MonFood.ORDER_DETAIL c " + " on (a.ORDER_ID = c.ORDER_ID) " + " join MonFood.PRODUCT d "
				+ " on (c.PRODUCT_ID = d.PRODUCT_ID) " + "where a.RES_ID = ? ";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Object> dataMap = null;
		Map<String, Object> fieldMap = new LinkedHashMap<>();
		List<Map<String, Object>> mapList = new ArrayList<>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// 動態 SQL 查詢
			// and USER_ID = 1 and a.ORDER_ID = 28
			StringBuffer sbf = new StringBuffer();
			sbf.append(resFindOrder);

			String orderIdStr = castTypeToStr(orderVO.getOrderId());
			String userIdStr = castTypeToStr(orderVO.getUserId());
			// 判斷是否有組合好的語句
			if (StringUtils.isNotBlank(orderIdStr)) {
				sbf.append(" and a.ORDER_ID = ? ");
				fieldMap.put("orderId", orderVO.getOrderId());
			}

			if (StringUtils.isNotBlank(userIdStr)) {
				sbf.append(" and USER_ID = ? ");
				fieldMap.put("userId", orderVO.getUserId());
			}
			pstmt = con.prepareStatement(sbf.toString());
			pstmt.setInt(1, orderVO.getResId());

			int fieldIndex = 2;
			for (String key : fieldMap.keySet()) {
				Object value = fieldMap.get(key);
				pstmt.setInt(fieldIndex, (int) value);
				fieldIndex++;
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				dataMap = new HashMap<>();
				dataMap.put("userId", rs.getInt("USER_ID"));
				dataMap.put("orderId", rs.getInt("ORDER_ID"));
				dataMap.put("productName", rs.getString("PRODUCT_NAME"));
				dataMap.put("amount", rs.getInt("AMOUNT"));
				dataMap.put("total", rs.getInt("TOTAL"));
				dataMap.put("orderDone", rs.getTimestamp("ORDER_DONE"));
				dataMap.put("note", rs.getString("NOTE"));
				dataMap.put("resComment", rs.getString("RES_COMMENT"));
				dataMap.put("orderStatus", OrderStatus.getOrderStatus(rs.getInt("ORDER_STATUS")));
				mapList.add(dataMap);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return mapList;
	}

	// 將各類別轉型為字串
	private <T> String castTypeToStr(T value) {
		String data = null;
		if (value != null) {
			data = value.toString();
		}

		return data;
	}
}
