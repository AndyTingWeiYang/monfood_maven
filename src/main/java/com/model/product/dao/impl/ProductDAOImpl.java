package com.model.product.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.model.product.ProductVO;
import com.model.product.dao.ProductDAO;

public class ProductDAOImpl implements ProductDAO {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/MonFood?serverTimezone=Asia/Taipei";
	public static final String USER = "root";
	public static final String PASSWORD = "password";

//	private static DataSource ds = null;

	private static final String SELECT = "select * from MonFood.PRODUCT";
	private static final String INSERT = "insert into MonFood.PRODUCT (RES_ID, PRODUCT_PIC, PRODUCT_STATUS, PRODUCT_PRICE, PRODUCT_KCAL, PRODUCT_NAME, UPDATE_TIME)values (?,  ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "update MonFood.PRODUCT set RES_ID =?, PRODUCT_PIC= ?, PRODUCT_STATUS= ?, PRODUCT_PRICE= ?, PRODUCT_KCAL=?, PRODUCT_NAME=? , UPDATE_TIME=? where PRODUCT_ID=?";
	private static final String DELETE = "delete from MonFood.PRODUCT where PRODUCT_ID=?";
	private static final String SELECT_BY_ID = "select * from MonFood.PRODUCT where PRODUCT_ID = ?";

	static {
//			Context context = new InitialContext();
//			ds = (DataSource) context.lookup("java:comp/env/jdbc/MonFood"); // JNDI 還沒取名，待修改
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ProductVO> findAll(ProductVO product) {
		List<ProductVO> productList = new ArrayList<>();
		ProductVO productVO = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(SELECT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductID(rs.getInt("PRODUCT_ID"));
				productVO.setResID(rs.getInt("RES_ID"));
				productVO.setProductStatus(rs.getInt("PRODUCT_STATUS"));
				productVO.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				productVO.setProductKcal(rs.getInt("PRODUCT_KCAL"));
				productVO.setProductName(rs.getString("PRODUCT_NAME"));
				productVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				productVO.setStock(rs.getInt("STOCK"));

				productList.add(productVO);
			}

		} catch (SQLException e) {

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
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return productList;

	}

	@Override
	public void update(ProductVO product) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(UPDATE);

			pstmt.setInt(1, product.getResID());
			pstmt.setBytes(2, product.getProductPic());
			pstmt.setInt(3, product.getProductStatus());
			pstmt.setInt(4, product.getProductPrice());
			pstmt.setInt(5, product.getProductKcal());
			pstmt.setString(6, product.getProductName());
			pstmt.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
			pstmt.setInt(8, product.getProductID());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void delete(ProductVO product) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, product.getProductID());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public ProductVO findPic(String productId) {
		ProductVO productVO = new ProductVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, Integer.parseInt(productId));

			rs = pstmt.executeQuery();
			while (rs.next()) {
				productVO.setProductID(rs.getInt("PRODUCT_ID"));
				productVO.setResID(rs.getInt("RES_ID"));
				productVO.setProductPic(rs.getBytes("PRODUCT_PIC"));
				productVO.setProductStatus(rs.getInt("PRODUCT_STATUS"));
				productVO.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				productVO.setProductKcal(rs.getInt("PRODUCT_KCAL"));
				productVO.setProductName(rs.getString("PRODUCT_NAME"));
				productVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				productVO.setStock(rs.getInt("STOCK"));
			}

		} catch (SQLException e) {
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
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return productVO;
	}

	@Override
	public boolean insertResult(ProductVO product) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(INSERT);

			// 動態 sql 指令
			StringBuffer sbf = new StringBuffer();
			sbf.append(
					"select resID, producrPic, productPrice, productKcal, updatTime,stock  from MonFood.PRODUCT where '1=1' ");

			
			pstmt.setInt(1, product.getResID());// FK
			pstmt.setBytes(2, product.getProductPic());
			pstmt.setInt(3, product.getProductStatus());
			pstmt.setInt(4, product.getProductPrice());
			pstmt.setInt(5, product.getProductKcal());
			pstmt.setString(6, product.getProductName());
			// 時間轉換:抓當下時間
			pstmt.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));

			int count = pstmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

}
