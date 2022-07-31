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
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;

import com.model.product.ProductVo;
import com.model.product.dao.ProductDao;

public class ProductDAOImpl implements ProductDao {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/MonFood?serverTimezone=Asia/Taipei";
	public static final String USER = "root";
	public static final String PASSWORD = "password";

//	private static DataSource ds = null;

	private static final String SELECT = "select * from MonFood.PRODUCT where 1 = 1 ";
	private static final String INSERT = "insert into MonFood.PRODUCT (RES_ID, PRODUCT_PIC, PRODUCT_STATUS, PRODUCT_PRICE, PRODUCT_KCAL, PRODUCT_NAME, UPDATE_TIME,STOCK)values (?,  ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "update MonFood.PRODUCT set  PRODUCT_PIC = ?, PRODUCT_STATUS = ?, PRODUCT_PRICE = ?, PRODUCT_KCAL = ?, PRODUCT_NAME = ? , STOCK = ? where PRODUCT_ID=?";
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
	public List<ProductVo> findAll() {
		List<ProductVo> productList = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = conn.prepareStatement(SELECT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 叫出DB的資料
				ProductVo productVO = new ProductVo();
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
	public List<ProductVo> findAll(ProductVo product) {
		List<ProductVo> productList = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			// 動態 sql 指令
			StringBuffer sbf = new StringBuffer();
			sbf.append(SELECT);

			// 取得產品 ID
			// 將Integer->String判斷是否為空
			String productIdStr = castTypeToStr(product.getProductID());
			Map<String, Object> fieldMap = new HashedMap<>();
			if (StringUtils.isNotBlank(productIdStr)) {
				sbf.append(" and PRODUCT_ID = ? ");
				fieldMap.put("productId", productIdStr);
			}

			// 取得產品名
			String productName = product.getProductName();
			if (StringUtils.isNotBlank(productName)) {
				sbf.append(" and PRODUCT_NAME = ? ");
				fieldMap.put("productName", productName);
			}

			// 取得最大/小金額
			// 將數字轉型
			String minPriceStr = castTypeToStr(product.getMinPrice());
			String maxPriceStr = castTypeToStr(product.getMaxPrice());
			if (StringUtils.isNotBlank(minPriceStr) && StringUtils.isNotBlank(maxPriceStr)) {
				sbf.append(" and PRODUCT_PRICE between ? and ? ");
				fieldMap.put("minPrice", product.getMinPrice());
				fieldMap.put("maxPrice", product.getMaxPrice());
			}

			// 將組好的 sql 指令放進 pstmt
			pstmt = conn.prepareStatement(sbf.toString());

			int fieldIndex = 1;
			for (String key : fieldMap.keySet()) {
				Object value = fieldMap.get(key);
				if (value instanceof Integer) {
					pstmt.setInt(fieldIndex, (int) value);
				} else if (value instanceof String) {
					pstmt.setString(fieldIndex, value.toString());
				}
				fieldIndex++;
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 叫出DB的資料
				ProductVo productVO = new ProductVo();
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
	public boolean update(ProductVo product) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(UPDATE);

			// 將VO 設定好的資料->DB
			pstmt.setBytes(1, product.getProductPic());
			pstmt.setInt(2, product.getProductStatus());
			pstmt.setInt(3, product.getProductPrice());
			pstmt.setInt(4, product.getProductKcal());
			pstmt.setString(5, product.getProductName());
			pstmt.setInt(6, product.getStock());
			pstmt.setInt(7, product.getProductID());
			int count = pstmt.executeUpdate();

			if (count > 0) {
				return true;
			}

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
		return false;
	}

	@Override
	public boolean updateDynanicPic(ProductVo product) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			StringBuffer sbf = new StringBuffer();
			sbf.append(
					"update MonFood.PRODUCT set  PRODUCT_STATUS = ?, PRODUCT_PRICE = ?, PRODUCT_KCAL = ?, PRODUCT_NAME = ? , STOCK = ? ");
			if ((product.getProductPic().length) != 0) {
				sbf.append(", PRODUCT_PIC = ?  where PRODUCT_ID = ? ");
				pstmt = conn.prepareStatement(sbf.toString());
				// 將VO 設定好的資料->DB
				pstmt.setInt(1, product.getProductStatus());
				pstmt.setInt(2, product.getProductPrice());
				pstmt.setInt(3, product.getProductKcal());
				pstmt.setString(4, product.getProductName());
				pstmt.setInt(5, product.getStock());
				pstmt.setBytes(6, product.getProductPic());
				pstmt.setInt(7, product.getProductID());

			} else {
				sbf.append(" where PRODUCT_ID = ? ");
				pstmt = conn.prepareStatement(sbf.toString());
				// 將VO 設定好的資料->DB
				pstmt.setInt(1, product.getProductStatus());
				pstmt.setInt(2, product.getProductPrice());
				pstmt.setInt(3, product.getProductKcal());
				pstmt.setString(4, product.getProductName());
				pstmt.setInt(5, product.getStock());
				pstmt.setInt(6, product.getProductID());
			}

			int count = pstmt.executeUpdate();

			if (count > 0) {
				return true;
			}

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

		return false;
	}

	@Override
	public void delete(ProductVo product) {
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
	public ProductVo findPic(String productId) {
		ProductVo productVO = new ProductVo();
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
	public boolean insert(ProductVo product) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(INSERT);
			// 從VO設定好的資料 ->放入DB內
			pstmt.setInt(1, product.getResID());// FK
			pstmt.setBytes(2, product.getProductPic());
			pstmt.setInt(3, product.getProductStatus());
			pstmt.setInt(4, product.getProductPrice());
			pstmt.setInt(5, product.getProductKcal());
			pstmt.setString(6, product.getProductName());
			// 時間轉換:抓當下時間
			pstmt.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
			pstmt.setInt(8, product.getStock());

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

	@Override
	public ProductVo findByID(String productID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVo product = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(SELECT_BY_ID);
			// 接收從畫面來的參數
			pstmt.setInt(1, Integer.parseInt(productID));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product = new ProductVo();
				product.setProductID(rs.getInt("PRODUCT_ID"));
				product.setResID(rs.getInt("RES_ID"));
				product.setProductPic(rs.getBytes("PRODUCT_PIC"));
				product.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				product.setProductStatus(rs.getInt("PRODUCT_STATUS"));
				product.setProductKcal(rs.getInt("PRODUCT_KCAL"));
				product.setProductName(rs.getString("PRODUCT_NAME"));
				product.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				product.setStock(rs.getInt("STOCK"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return product;
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
