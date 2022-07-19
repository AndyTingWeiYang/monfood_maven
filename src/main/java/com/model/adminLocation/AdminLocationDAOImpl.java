package com.model.adminLocation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.del.DelVO;
import com.model.del.MyData;

public class AdminLocationDAOImpl implements AdminLocationDAO {
	private static final String INSERT = "INSERT INTO MonFood.ADMIN_LOCATION (zip_code, admin_region) VALUES (?, ?)";
	private static final String FIND_BY_PK = "SELECT * FROM MonFood.ADMIN_LOCATION WHERE zip_code = ?";
	private static final String GET_ALL = "SELECT * FROM MonFood.ADMIN_LOCATION";
	private static final String DELETE = "DELETE FROM MonFood.ADMIN_LOCATION WHERE zip_code = ?";
	private static final String UPDATE = "UPDATE MonFood.ADMIN_LOCATION SET zip_code = ?, admin_region = ? where zip_code = ?";

	// 貼在static區域裡面程式一載入就會執行一次載好因為驅動也只要載一次就好
	static {
		try {
			Class.forName(MyData.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

//insert
	@Override
	public void add(AdminLocationVO adminLocationVO) {

		try (Connection con = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(INSERT)) {
			pstmt.setInt(1, adminLocationVO.getZipCode());
			pstmt.setString(2, adminLocationVO.getAdminRegion());

			pstmt.execute();
			System.out.println("新增成功");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

//update
	@Override
	public void update(AdminLocationVO adminLocationVO) {

		try (Connection con = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(UPDATE)) {
			pstmt.setInt(1, adminLocationVO.getZipCode());
			pstmt.setString(2, adminLocationVO.getAdminRegion());
			pstmt.setInt(3, adminLocationVO.getZipCode());

			pstmt.execute();
			System.out.println("更新成功");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

//delete
	@Override
	public void delete(Integer zipCode) {
		try (Connection con = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(DELETE)) {
			pstmt.setInt(1, zipCode);
			pstmt.executeUpdate();
			System.out.println("刪除成功");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

//search by ID
	@Override
	public AdminLocationVO findByzipCode(Integer zipCode) {
		ResultSet rs = null;
		AdminLocationVO adminLocationVO = null;
		try(Connection con = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(FIND_BY_PK)){
			
			pstmt.setInt(1, zipCode);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				adminLocationVO = new AdminLocationVO();
				adminLocationVO.setZipCode(zipCode);
				adminLocationVO.setAdminRegion(rs.getString("admin_region"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (final SQLException se) {
					se.printStackTrace();
				}
			}
		return adminLocationVO;
		}
		
	}

	@Override
	public List<AdminLocationVO> getAll() {
		List<AdminLocationVO> adminLocationVOs = new ArrayList<AdminLocationVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
//	準備好一個arraylist裝查回來的每筆資料，所以在迴圈裡面設定查回來的每一個物件(迴圈一次包裝一列)
				AdminLocationVO adminLocationVO = new AdminLocationVO();
				adminLocationVO.setZipCode(rs.getInt("zip_code"));
				adminLocationVO.setAdminRegion(rs.getString("admin_region"));
				//再將包裝好的物件放進袋子(集合)
				adminLocationVOs.add(adminLocationVO);
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		
		
		return adminLocationVOs;

	}

}
