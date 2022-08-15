package com.model.product.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;

import com.model.product.ProductVo;
import com.model.product.dao.ProductDao;
import com.model.product.util.ProductStatus;

public class ProductDAOImpl implements ProductDao {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/MonFood?serverTimezone=Asia/Taipei";
	public static final String USER = "root";
	public static final String PASSWORD = "password";

//	private static DataSource ds = null;

	private static final String GET_ALL_PDT = "SELECT PRODUCT_ID, RES_ID, PRODUCT_PRICE, PRODUCT_NAME, PRODUCT_KCAL "
			+ "FROM PRODUCT " + "WHERE RES_ID = ?";

	private static final String SELECT = "select * from MonFood.PRODUCT where 1 = 1 ";
	private static final String INSERT = "insert into MonFood.PRODUCT (RES_ID, PRODUCT_PIC, PRODUCT_STATUS, PRODUCT_PRICE, PRODUCT_KCAL, PRODUCT_NAME, UPDATE_TIME,STOCK)values (?,  ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "update MonFood.PRODUCT set  PRODUCT_PIC = ?, PRODUCT_STATUS = ?, PRODUCT_PRICE = ?, PRODUCT_KCAL = ?, PRODUCT_NAME = ? , STOCK = ? where PRODUCT_ID= ?";
	private static final String DELETE = "delete from MonFood.PRODUCT where PRODUCT_ID=?";
	private static final String SELECT_BY_ID = "select * from MonFood.PRODUCT where PRODUCT_ID = ?";
	private static final String SELECT_PRODUCT_RESCATE_BY_ID = "SELECT * FROM MonFood.RES "
			+ "inner join PRODUCT on PRODUCT.RES_ID =RES.RES_ID "
			+ "inner join RES_CATEGORY on RES_CATEGORY.RES_CATEGORY_ID = RES.RES_CATEGORY "
			+ "where PRODUCT.PRODUCT_ID = ?";
	private static final String GET_ALL = "select * from MonFood.PRODUCT order by PRODUCT_ID";
	private static final String FIND_RES_INFO = "select * from RES inner join RES_CATEGORY "
			+ " on RES.RES_CATEGORY = RES_CATEGORY.RES_CATEGORY_ID" + " where RES_ID = ? ";

	private static final String UPDATE_STOCK = "update MonFood.PRODUCT set STOCK = ? where PRODUCT_ID= ?";
	private static final String UPDATE_STATUS = "update MonFood.PRODUCT set PRODUCT_STATUS = ? where PRODUCT_ID= ?";

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
	public List<Map<String, Object>> findAll(ProductVo product) {
		List<Map<String, Object>> productList = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			// 動態 sql 指令
			StringBuffer sbf = new StringBuffer();
			sbf.append(SELECT);

			// 取得產品 ID
			String productIdStr = castTypeToStr(product.getProductID());
			Map<String, Object> fieldMap = new LinkedHashMap<>();

			sbf.append(" and RES_ID = ? ");
			fieldMap.put("resID", product.getResID());

			// 將Integer->String判斷是否為空
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
				// 叫出DB的資料，放入Map
				Map<String, Object> productMap = new HashMap<>();
				productMap.put("productID", rs.getInt("PRODUCT_ID"));
				productMap.put("resID", rs.getInt("RES_ID"));
				productMap.put("productStatus", ProductStatus.getProductStatus(rs.getInt("PRODUCT_STATUS")));
				productMap.put("productPrice", rs.getInt("PRODUCT_PRICE"));
				productMap.put("productKcal", rs.getInt("PRODUCT_KCAL"));
				productMap.put("productName", rs.getString("PRODUCT_NAME"));
				productMap.put("updateTime", rs.getTimestamp("UPDATE_TIME"));
				productMap.put("stock", rs.getInt("STOCK"));
				productMap.put("resID", rs.getInt("RES_ID"));
				productList.add(productMap);
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
	public boolean insert(Map<String, Object> dataMap) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(INSERT);
			// 從VO設定好的資料 ->放入DB內
			pstmt.setInt(1, MapUtils.getInteger(dataMap, "resID"));// FK
			pstmt.setBytes(2, (byte[]) MapUtils.getObject(dataMap, "productPic"));
			pstmt.setInt(3, MapUtils.getInteger(dataMap, "productStatus"));
			pstmt.setInt(4, MapUtils.getInteger(dataMap, "productPrice"));
			pstmt.setInt(5, MapUtils.getInteger(dataMap, "productKcal"));
			pstmt.setString(6, MapUtils.getString(dataMap, "productName"));
			// 時間轉換:抓當下時間
			pstmt.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
			pstmt.setInt(8, MapUtils.getInteger(dataMap, "stock"));

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
	public Map<String, Object> findByID(String productID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Object> productMap = new HashedMap<>();
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(SELECT_PRODUCT_RESCATE_BY_ID);
			// 接收從畫面來的參數
			pstmt.setInt(1, Integer.parseInt(productID));
			rs = pstmt.executeQuery();

			while (rs.next()) {

				productMap.put("productID", rs.getInt("PRODUCT_ID"));
				productMap.put("resID", rs.getInt("RES_ID"));
				productMap.put("productPic", rs.getBytes("PRODUCT_PIC"));
				productMap.put("productPrice", rs.getInt("PRODUCT_PRICE"));
				productMap.put("productStatus", rs.getInt("PRODUCT_STATUS"));
				productMap.put("productKcal", rs.getInt("PRODUCT_KCAL"));
				productMap.put("productName", rs.getString("PRODUCT_NAME"));
				productMap.put("stock", rs.getInt("STOCK"));
				productMap.put("resCategory", rs.getString("RES_CATEGORY_NAME"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return productMap;
	}

	// 將各類別轉型為字串
	private <T> String castTypeToStr(T value) {
		String data = null;
		if (value != null) {
			data = value.toString();
		}

		return data;
	}

	@Override
	public List<ProductVo> getAll() {

		List<ProductVo> list = new ArrayList<>();
		ProductVo productVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductVo productVO = new ProductVo();
				productVO.setProductID(rs.getInt("PRODUCT_ID"));
				productVO.setResID(rs.getInt("RES_ID"));
				productVO.setProductStatus(rs.getInt("PRODUCT_STATUS"));
				productVO.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				productVO.setProductKcal(rs.getInt("PRODUCT_KCAL"));
				productVO.setProductName(rs.getString("PRODUCT_NAME"));
				productVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				productVO.setStock(rs.getInt("STOCK"));

				list.add(productVO);

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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public Map<String, Object> findResInfo(Integer resID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Object> resInfoMap = new HashedMap<>();

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(FIND_RES_INFO);
			pstmt.setInt(1, resID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				resInfoMap.put("resID", rs.getInt("RES_ID"));
				resInfoMap.put("resName", rs.getString("RES_NAME"));
				resInfoMap.put("resCategoryName", rs.getString("RES_CATEGORY_NAME"));
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
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return resInfoMap;
	}

	@Override
	public List<Map<String, Object>> getAllPdt(Integer resId) {
		List<Map<String, Object>> pdtList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(GET_ALL_PDT);
			pstmt.setInt(1, resId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("productID", rs.getInt("PRODUCT_ID"));
				map.put("resId", rs.getInt("RES_ID"));
				map.put("productPrice", rs.getInt("PRODUCT_PRICE"));
				map.put("productName", rs.getString("PRODUCT_NAME"));
				map.put("productKcal", rs.getInt("PRODUCT_KCAL"));

				pdtList.add(map);
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return pdtList;
	}

	@Override
	public boolean updateStock(ProductVo productVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(UPDATE_STOCK);

			// 將VO 設定好的資料->DB
			pstmt.setInt(1, productVo.getStock());
			pstmt.setInt(2, productVo.getProductID());
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
	public boolean updateStatus(ProductVo productVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(UPDATE_STATUS);

			// 將VO 設定好的資料->DB
			pstmt.setInt(1, productVo.getProductStatus());
			pstmt.setInt(2, productVo.getProductID());
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

}