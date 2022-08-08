package com.model.administrator.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.administrator.AdministratorVO;
import com.model.administrator.dao.AdministratorDAO;
import com.model.order.OrderVO;

public class AdministratorDAOImpl implements AdministratorDAO {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/MonFood?serverTimezone=Asia/Taipei";
	public static final String USER = "root";
	public static final String PASSWORD = "password";

	private static final String SELECT = "select * from MonFood.ADMINISTRATOR";
	private static final String INSERT = "insert into MonFood.ADMINISTRATOR (ADMIN_PASSWORD, PERMISSION) values (?, ?)";
	private static final String UPDATE = "update MonFood.ADMINISTRATOR set ADMIN_PASSWORD =?, PERMISSION =? where ADMIN_ID = ?";
	private static final String DELETE = "delete from MonFood.ADMINISTRATOR where ADMIN_ID= ?";
	private static final String GETBYID = "SELECT * FROM MonFood.ADMINISTRATOR WHERE ADMIN_ID= ? AND  ADMIN_PASSWORD=?";
	private static final String GETORDERBYID = "SELECT * FROM MonFood.ORDER WHERE ORDER_ID= ?";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<AdministratorVO> findAll() {
		List<AdministratorVO> adminList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(SELECT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AdministratorVO admin = new AdministratorVO();
				admin.setAdminID(rs.getInt(1));
				admin.setAdminPassword(rs.getString(2));
				admin.setPermission(rs.getInt(3));

				adminList.add(admin);

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
		return adminList;
	}

	@Override
	public void insert(AdministratorVO admin) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(INSERT);

			pstmt.setString(1, admin.getAdminPassword());
			pstmt.setInt(2, admin.getPermission());

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
	public void update(AdministratorVO admin) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(UPDATE);

			pstmt.setString(1, admin.getAdminPassword());
			pstmt.setInt(2, admin.getPermission());
			pstmt.setInt(3, admin.getAdminID());
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
	public void delete(AdministratorVO adminID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, adminID.getAdminID());

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
	public AdministratorVO selectByIDPassword(Integer adminID, String adminPassword) {
		AdministratorVO administratorVO = new AdministratorVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(GETBYID);
			pstmt.setInt(1, adminID);
			pstmt.setString(2, adminPassword);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				administratorVO.setAdminID(rs.getInt(1));
				administratorVO.setAdminPassword(rs.getString(2));
				administratorVO.setPermission(rs.getInt(3));
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
		return administratorVO;
	}

	@Override
	public OrderVO getOrderByID(Integer orderID) {
		OrderVO orderVO = new OrderVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(GETORDERBYID);
			pstmt.setInt(1, orderID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				orderVO.setOrderId(rs.getInt("ORDER_ID"));
				orderVO.setResId(rs.getInt("RES_ID"));;
				orderVO.setUserId(rs.getInt("USER_ID"));
				orderVO.setDelId(rs.getInt("DEL_ID"));
				orderVO.setTotal(rs.getInt("TOTAL"));
				orderVO.setDelCost(rs.getInt("DEL_COST"));
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
		return orderVO;
	}

}
