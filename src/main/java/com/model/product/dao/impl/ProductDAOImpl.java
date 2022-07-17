package com.model.product.dao.impl;

import java.io.IOException;
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
	private static final String UPDATE = "update MonFood.PRODUCT set RES_ID =?, PRODUCT_PIC= ?, PRODUCT_STATUS= ?, PRODUCT_PRICE= ?, PRODUCT_KCAL=?, PRODUCT_NAME=? , UPDATE_TIME=? ";
	private static final String DELETE = "delete from MonFood.PRODUCT where PRODUCT_ID=?";

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
	public void insert(ProductVO product) throws IOException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(INSERT);

			pstmt.setInt(1, product.getResID());
			pstmt.setBytes(2, product.getProductPic());
			pstmt.setInt(3, product.getProductStatus());
			pstmt.setInt(4, product.getProductPrice());
			pstmt.setInt(5, product.getProductPrice());
			pstmt.setString(6, product.getProductName());
			// 時間轉換:
			pstmt.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));

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
				productVO.setProductID(1);
				productVO.setResID(rs.getInt(2));
				productVO.setProductPic(rs.getBytes(3));
				productVO.setProductStatus(rs.getInt(4));
				productVO.setProductPrice(rs.getInt(5));
				productVO.setProductKcal(rs.getInt(6));
				productVO.setProductName(rs.getString(7));
				productVO.setUpdateTime(rs.getTimestamp(8));

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
			pstmt.setInt(5, product.getProductPrice());
			pstmt.setString(6, product.getProductName());
			pstmt.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));

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

}
