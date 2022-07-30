package com.model.resCategory.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.resCategory.ResCategoryVo;
import com.model.resCategory.dao.ResCategoryDao;

public class ResCategoryDaoImpl implements ResCategoryDao {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/MonFood?serverTimezone=Asia/Taipei";
	public static final String USER = "root";
	public static final String PASSWORD = "password";

	private static final String SELECT = "select * from MonFood.RES_CATEGORY";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ResCategoryVo> findAll() {

		List<ResCategoryVo> resCategoryList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(SELECT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ResCategoryVo resCategoryVo = new ResCategoryVo();
				resCategoryVo.setResCategoryID(rs.getInt("RES_CATEGORY_ID"));
				resCategoryVo.setResCategoryName(rs.getString("RES_CATEGORY_NAME"));
				resCategoryList.add(resCategoryVo);
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
		return resCategoryList;
	}

}
