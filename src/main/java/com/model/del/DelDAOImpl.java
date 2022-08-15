package com.model.del;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.model.order.OrderVO;

public class DelDAOImpl implements DelDAO{
	private static final String INSERT_STMT = "INSERT INTO MonFood.DEL (del_Name, del_Account, del_Password, del_Tel, del_Birthday, platenumber, status, update_Time, del_Account_Name, del_Bankname, del_Bankcode, del_bankaccount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String FIND_BY_PK = "SELECT * FROM DEL WHERE DEL_ID = ?";
	private static final String GET_ALL = "SELECT * FROM DEL"; 
	private static final String DELETE = "DELETE FROM DEL WHERE DEL_ID = ?";
	private static final String UPDATE = 
			"UPDATE MonFood.DEL SET DEL_NAME=?, DEL_ACCOUNT=?, DEL_PASSWORD=?, DEL_TEL=?, DEL_BIRTHDAY=?, PLATENUMBER=?, STATUS=?, UPDATE_TIME=?, DEL_ID_PHOTO=?, CAR_LICENSE=?, DRIVE_LICENSE=?, CRIMINAL_RECORD=?, INSURANCE=?, DEL_ACCOUNT_NAME=?, DEL_BANKNAME=?, DEL_BANKCODE=?, DEL_BANKACCOUNT=? WHERE DEL_ID=?";
	private static final String UPDATEWITHOUTPIC = 
			"UPDATE DEL SET del_name=?, del_account=?, del_password = ?, del_Tel = ?, del_birthday=?, platenumber = ?, status = ?, update_time=?, del_Account_Name = ?, del_Bankname = ?, del_Bankcode = ?, del_bankaccount = ? where del_ID = ?";
	private static final String FIND_BY_NAME = "SELECT * FROM DEL WHERE DEL_NAME = ? AND DEL_TEL = ?";
	private static final String LOGIN = "SELECT * FROM MonFood.DEL WHERE DEL_TEL = ? AND DEL_PASSWORD = ?";
	private static final String GETCOST = "SELECT SUM(DEL_COST) FROM MonFood.ORDER WHERE ORDER_CREATE between ? and ? and del_id = ?";	
	private static final String GETCOMMENT = "SELECT DEL_COMMENT FROM MonFood.ORDER WHERE ORDER_CREATE between ? and ? and del_id = ?";
	private static final String GETTIMES = "SELECT COUNT(*) FROM MonFood.ORDER WHERE ORDER_CREATE between ? and ? and del_id = ?";
	private static final String FIND_BY_ACCT = "SELECT * FROM MonFood.DEL WHERE DEL_ACCOUNT = ?";	
	private static final String FIND_BY_TEL = "SELECT * FROM MonFood.DEL WHERE DEL_TEL = ?";	
	
	
	// 貼在static區域裡面程式一載入就會執行一次載好因為驅動也只要載一次就好
	static {
		try {
			Class.forName(MyData.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void add(DelVO delVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, delVO.getDelName());
			pstmt.setString(2, delVO.getDelAccount());
			pstmt.setString(3, delVO.getDelPassword());
			pstmt.setString(4, delVO.getDelTel());
			pstmt.setDate(5, delVO.getDelBirthday());
			pstmt.setString(6, delVO.getPlatenumber());
			pstmt.setInt(7, delVO.getStatus());
			pstmt.setTimestamp(8, delVO.getUpdateTime());
			pstmt.setString(9, delVO.getDelAccountName());
			pstmt.setString(10, delVO.getDelBankname());
			pstmt.setString(11, delVO.getDelBankcode());
			pstmt.setString(12, delVO.getDelBankaccount());
			
			pstmt.executeUpdate();
						
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
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
	}

	@Override
	public DelVO update(DelVO delVO) {
		try (Connection connection = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(UPDATE)) {
			pstmt.setString(1, delVO.getDelName());
			pstmt.setString(2, delVO.getDelAccount());
			pstmt.setString(3, delVO.getDelPassword());
			pstmt.setString(4, delVO.getDelTel());
			pstmt.setDate(5, delVO.getDelBirthday());
			pstmt.setString(6, delVO.getPlatenumber());
			pstmt.setInt(7, delVO.getStatus());
			pstmt.setTimestamp(8, delVO.getUpdateTime());
			pstmt.setBytes(9, delVO.getDelIDPhoto());
			pstmt.setBytes(10, delVO.getCarLicense());
			pstmt.setBytes(11, delVO.getDriverLicense());
			pstmt.setBytes(12, delVO.getCriminalRecord());
			pstmt.setBytes(13, delVO.getInsurance());
			pstmt.setString(14, delVO.getDelAccountName());
			pstmt.setString(15, delVO.getDelBankname());
			pstmt.setString(16, delVO.getDelBankcode());
			pstmt.setString(17, delVO.getDelBankaccount());
			pstmt.setInt(18, delVO.getDelID());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return delVO;
	}

	@Override
	public void delete(Integer delID) {
		int rowCount = 0;
		try(Connection con = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD); 
				PreparedStatement pstmt = con.prepareStatement(DELETE) ){
			pstmt.setInt(1, delID);
			pstmt.executeUpdate();
			System.out.println("刪除成功");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public DelVO findByDelID(Integer delID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//先宣告一個變數用來接回傳的資料
		DelVO delVO = new DelVO();
		try {
			con = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, delID);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				delVO.setDelID(rs.getInt("del_ID"));
				delVO.setDelName(rs.getString("del_Name"));
				delVO.setDelAccount(rs.getString("del_Account"));
				delVO.setDelPassword(rs.getString("del_Password"));
				delVO.setDelTel(rs.getString("del_Tel"));
				delVO.setDelBirthday(rs.getDate("del_Birthday"));
				delVO.setPlatenumber(rs.getString("platenumber"));
				delVO.setStatus(rs.getInt("status"));
				delVO.setUpdateTime(rs.getTimestamp("update_Time"));
				delVO.setDelIDPhoto(rs.getBytes("del_id_Photo"));
				delVO.setCarLicense(rs.getBytes("car_License"));
				delVO.setDriverLicense(rs.getBytes("drive_License"));
				delVO.setCriminalRecord(rs.getBytes("criminal_Record"));
				delVO.setInsurance(rs.getBytes("insurance"));
				delVO.setDelAccountName(rs.getString("del_Account_Name"));
				delVO.setDelBankname(rs.getString("del_Bankname"));
				delVO.setDelBankcode(rs.getString("del_Bankcode"));
				delVO.setDelBankaccount(rs.getString("del_Bankaccount"));
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
		
		return delVO;
	}

	@Override
	public List<DelVO> getAll() {
		List<DelVO> delVOList = new ArrayList<DelVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
//	準備好一個arraylist裝查回來的每筆資料，所以在迴圈裡面設定查回來的每一個物件(迴圈一次包裝一列)
				DelVO delVO = new DelVO();
				delVO.setDelID(rs.getInt("del_ID"));
				delVO.setDelName(rs.getString("del_Name"));
				delVO.setDelAccount(rs.getString("del_Account"));
				delVO.setDelPassword(rs.getString("del_Password"));
				delVO.setDelTel(rs.getString("del_Tel"));
				delVO.setDelBirthday(rs.getDate("del_Birthday"));
				delVO.setPlatenumber(rs.getString("platenumber"));
				delVO.setStatus(rs.getInt("status"));
				delVO.setUpdateTime(rs.getTimestamp("update_Time"));
				delVO.setDelIDPhoto(rs.getBytes("del_id_Photo"));
				delVO.setCarLicense(rs.getBytes("car_License"));
				delVO.setDriverLicense(rs.getBytes("drive_License"));
				delVO.setCriminalRecord(rs.getBytes("criminal_Record"));
				delVO.setInsurance(rs.getBytes("insurance"));
				delVO.setDelAccountName(rs.getString("del_Account_Name"));
				delVO.setDelBankname(rs.getString("del_Bankname"));
				delVO.setDelBankcode(rs.getString("del_Bankcode"));
				delVO.setDelBankaccount(rs.getString("del_Bankaccount"));
				//再將包裝好的物件放進袋子(集合)
				delVOList.add(delVO);
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
		
		
		return delVOList;
	}

	@Override
	public DelVO findByDelNamePassword(String delName, String deTel) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DelVO delVO = null;
		try {
			con = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_NAME);
			pstmt.setString(1, delName);
			pstmt.setString(2, deTel);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				delVO = new DelVO();
				delVO.setDelID(rs.getInt("del_ID"));
				delVO.setDelName(rs.getString("del_Name"));
				delVO.setDelAccount(rs.getString("del_Account"));
				delVO.setDelPassword(rs.getString("del_Password"));
				delVO.setDelTel(rs.getString("del_Tel"));
				delVO.setDelBirthday(rs.getDate("del_Birthday"));
				delVO.setPlatenumber(rs.getString("platenumber"));
				delVO.setStatus(rs.getInt("status"));
				delVO.setUpdateTime(rs.getTimestamp("update_Time"));
				delVO.setDelIDPhoto(rs.getBytes("del_id_Photo"));
				delVO.setCarLicense(rs.getBytes("car_License"));
				delVO.setDriverLicense(rs.getBytes("drive_License"));
				delVO.setCriminalRecord(rs.getBytes("criminal_Record"));
				delVO.setInsurance(rs.getBytes("insurance"));
				delVO.setDelAccountName(rs.getString("del_Account_Name"));
				delVO.setDelBankname(rs.getString("del_Bankname"));
				delVO.setDelBankcode(rs.getString("del_Bankcode"));
				delVO.setDelBankaccount(rs.getString("del_Bankaccount"));
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
		
		return delVO;
	}

	@Override
	public DelVO updateWithoutPic(DelVO delVO) {
		try (Connection connection = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(UPDATEWITHOUTPIC)) {
			pstmt.setString(1, delVO.getDelName());
			pstmt.setString(2, delVO.getDelAccount());
			pstmt.setString(3, delVO.getDelPassword());
			pstmt.setString(4, delVO.getDelTel());
			pstmt.setDate(5, delVO.getDelBirthday());
			pstmt.setString(6, delVO.getPlatenumber());
			pstmt.setInt(7, delVO.getStatus());
			pstmt.setTimestamp(8, delVO.getUpdateTime());
			pstmt.setString(9, delVO.getDelAccountName());
			pstmt.setString(10, delVO.getDelBankname());
			pstmt.setString(11, delVO.getDelBankcode());
			pstmt.setString(12, delVO.getDelBankaccount());
			pstmt.setInt(13, delVO.getDelID());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return delVO;
	}

	



	@Override
	public DelVO login(String delTel, String delPassword) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//先宣告一個變數用來接回傳的資料
		DelVO delVO = new DelVO();
		try {
			con = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
			pstmt = con.prepareStatement(LOGIN);
			pstmt.setString(1, delTel);
			pstmt.setString(2, delPassword);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				delVO.setDelID(rs.getInt("del_ID"));
				delVO.setDelName(rs.getString("del_Name"));
				delVO.setDelAccount(rs.getString("del_Account"));
				delVO.setDelPassword(rs.getString("del_Password"));
				delVO.setDelTel(rs.getString("del_Tel"));
				delVO.setDelBirthday(rs.getDate("del_Birthday"));
				delVO.setPlatenumber(rs.getString("platenumber"));
				delVO.setStatus(rs.getInt("status"));
				delVO.setUpdateTime(rs.getTimestamp("update_Time"));
				delVO.setDelIDPhoto(rs.getBytes("del_id_Photo"));
				delVO.setCarLicense(rs.getBytes("car_License"));
				delVO.setDriverLicense(rs.getBytes("drive_License"));
				delVO.setCriminalRecord(rs.getBytes("criminal_Record"));
				delVO.setInsurance(rs.getBytes("insurance"));
				delVO.setDelAccountName(rs.getString("del_Account_Name"));
				delVO.setDelBankname(rs.getString("del_Bankname"));
				delVO.setDelBankcode(rs.getString("del_Bankcode"));
				delVO.setDelBankaccount(rs.getString("del_Bankaccount"));
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
		
		return delVO;
	}
	
	

	@Override
	public String getCost(Timestamp startDate, Timestamp endDate, Integer delID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO orderVO = null;
		String totalCost = null;
		try {
			con = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
			pstmt = con.prepareStatement(GETCOST);
			pstmt.setTimestamp(1, startDate);
			pstmt.setTimestamp(2, endDate);
			pstmt.setInt(3, delID);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				orderVO = new OrderVO();
				orderVO.setDelCost(rs.getInt("sum(del_cost)"));
				totalCost = String.valueOf(orderVO.getDelCost());
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
		
		return totalCost;

	}

	@Override
	public String getRideTimes(Timestamp startDate, Timestamp endDate, Integer delID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO orderVO = null;
		String totalTimes = null;
		try {
			con = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
			pstmt = con.prepareStatement(GETTIMES);
			pstmt.setTimestamp(1, startDate);
			pstmt.setTimestamp(2, endDate);
			pstmt.setInt(3, delID);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				orderVO = new OrderVO();
				totalTimes = String.valueOf(rs.getInt("count(*)"));
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
		
		return totalTimes;
	}
	
	@Override
	public List<String> getComment(Timestamp startDate, Timestamp endDate, Integer delID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> comment = new ArrayList<String>();
		try {
			con = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
			pstmt = con.prepareStatement(GETCOMMENT);
			pstmt.setTimestamp(1, startDate);
			pstmt.setTimestamp(2, endDate);
			pstmt.setInt(3, delID);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				comment.add(rs.getString("del_comment"));
				System.out.println(comment+"dao找到的comment");
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
		
		return comment;
	}

	@Override
	public DelVO findByaccount(String delAccount) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//先宣告一個變數用來接回傳的資料
		DelVO delVO = new DelVO();
		try {
			con = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_ACCT);
			pstmt.setString(1, delAccount);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				delVO.setDelID(rs.getInt("del_ID"));
				delVO.setDelName(rs.getString("del_Name"));
				delVO.setDelAccount(rs.getString("del_Account"));
				delVO.setDelPassword(rs.getString("del_Password"));
				delVO.setDelTel(rs.getString("del_Tel"));
				delVO.setDelBirthday(rs.getDate("del_Birthday"));
				delVO.setPlatenumber(rs.getString("platenumber"));
				delVO.setStatus(rs.getInt("status"));
				delVO.setUpdateTime(rs.getTimestamp("update_Time"));
				delVO.setDelIDPhoto(rs.getBytes("del_id_Photo"));
				delVO.setCarLicense(rs.getBytes("car_License"));
				delVO.setDriverLicense(rs.getBytes("drive_License"));
				delVO.setCriminalRecord(rs.getBytes("criminal_Record"));
				delVO.setInsurance(rs.getBytes("insurance"));
				delVO.setDelAccountName(rs.getString("del_Account_Name"));
				delVO.setDelBankname(rs.getString("del_Bankname"));
				delVO.setDelBankcode(rs.getString("del_Bankcode"));
				delVO.setDelBankaccount(rs.getString("del_Bankaccount"));
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
		
		return delVO;
	}

	@Override
	public DelVO findByTel(String delTel) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//先宣告一個變數用來接回傳的資料
		DelVO delVO = new DelVO();
		try {
			con = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_TEL);
			pstmt.setString(1, delTel);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				delVO.setDelID(rs.getInt("del_ID"));
				delVO.setDelName(rs.getString("del_Name"));
				delVO.setDelAccount(rs.getString("del_Account"));
				delVO.setDelPassword(rs.getString("del_Password"));
				delVO.setDelTel(rs.getString("del_Tel"));
				delVO.setDelBirthday(rs.getDate("del_Birthday"));
				delVO.setPlatenumber(rs.getString("platenumber"));
				delVO.setStatus(rs.getInt("status"));
				delVO.setUpdateTime(rs.getTimestamp("update_Time"));
				delVO.setDelIDPhoto(rs.getBytes("del_id_Photo"));
				delVO.setCarLicense(rs.getBytes("car_License"));
				delVO.setDriverLicense(rs.getBytes("drive_License"));
				delVO.setCriminalRecord(rs.getBytes("criminal_Record"));
				delVO.setInsurance(rs.getBytes("insurance"));
				delVO.setDelAccountName(rs.getString("del_Account_Name"));
				delVO.setDelBankname(rs.getString("del_Bankname"));
				delVO.setDelBankcode(rs.getString("del_Bankcode"));
				delVO.setDelBankaccount(rs.getString("del_Bankaccount"));
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
		
		return delVO;
	}


	
	
	
	
	
	
	

}
