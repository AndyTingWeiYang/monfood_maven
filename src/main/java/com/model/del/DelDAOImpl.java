package com.model.del;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DelDAOImpl implements DelDAO{
	private static final String INSERT_STMT = "INSERT INTO MonFood.DEL (del_ID, del_Name, del_Account, del_Password, del_Tel, del_Birthday, platenumber, status, update_Time, del_ID_Photo, car_License, drive_License, criminal_Record, insurance, del_Account_Name, del_Bankname, del_Bankcode, del_bankaccount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String FIND_BY_PK = "SELECT * FROM MonFood.DEL WHERE DEL_ID = ?";
	private static final String GET_ALL = "SELECT * FROM MonFood.DEL"; 
	private static final String DELETE = "DELETE FROM MonFood.DEL WHERE DEL_ID = ?";
	private static final String UPDATE = "UPDATE MonFood.DEL SET del_ID = ?, del_Name = ?, del_Account = ?, del_Password = ?, del_Tel = ?, del_Birthday = ?, platenumber = ?, status = ?, update_Time = ?, del_ID_Photo = ?, car_License = ?, drive_License = ?, criminal_Record = ?, insurance = ?, del_Account_Name = ?, del_Bankname = ?, del_Bankcode = ?, del_bankaccount = ? where del_ID = ?";

		
	
	
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
			pstmt.setInt(1, delVO.getDelID());
			pstmt.setString(2, delVO.getDelName());
			pstmt.setString(3, delVO.getDelAccount());
			pstmt.setString(4, delVO.getDelPassword());
			pstmt.setString(5, delVO.getDelTel());
			pstmt.setDate(6, delVO.getDelBirthday());
			pstmt.setString(7, delVO.getPlatenumber());
			pstmt.setInt(8, delVO.getStatus());
			pstmt.setTimestamp(9, delVO.getUpdateTime());
			pstmt.setBytes(10, delVO.getDelIDPhoto());
			pstmt.setBytes(11, delVO.getCarLicense());
			pstmt.setBytes(12, delVO.getDriverLicense());
			pstmt.setBytes(13, delVO.getCriminalRecord());
			pstmt.setBytes(14, delVO.getInsurance());
			pstmt.setString(15, delVO.getDelAccountName());
			pstmt.setString(16, delVO.getDelBankname());
			pstmt.setString(17, delVO.getDelBankcode());
			pstmt.setString(18, delVO.getDelBankaccount());
			
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
	public void update(DelVO delVO) {
		try (Connection connection = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(UPDATE)) {
			pstmt.setInt(1, delVO.getDelID());
			pstmt.setString(2, delVO.getDelName());
			pstmt.setString(3, delVO.getDelAccount());
			pstmt.setString(4, delVO.getDelPassword());
			pstmt.setString(5, delVO.getDelTel());
			pstmt.setDate(6, delVO.getDelBirthday());
			pstmt.setString(7, delVO.getPlatenumber());
			pstmt.setInt(8, delVO.getStatus());
			pstmt.setTimestamp(9, delVO.getUpdateTime());
			pstmt.setBytes(10, delVO.getDelIDPhoto());
			pstmt.setBytes(11, delVO.getCarLicense());
			pstmt.setBytes(12, delVO.getDriverLicense());
			pstmt.setBytes(13, delVO.getCriminalRecord());
			pstmt.setBytes(14, delVO.getInsurance());
			pstmt.setString(15, delVO.getDelAccountName());
			pstmt.setString(16, delVO.getDelBankname());
			pstmt.setString(17, delVO.getDelBankcode());
			pstmt.setString(18, delVO.getDelBankaccount());
			pstmt.setInt(19, delVO.getDelID());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		DelVO delVO = null;
		
		try {
			con = DriverManager.getConnection(MyData.URL, MyData.USER, MyData.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, delID);
			rs = pstmt.executeQuery();
			while(rs.next()) {
//				String delName = rs.getString(delName);
//				String delAccount = rs.getString(delAccount);
//				String delPassword = rs.getString(delPassword);
//				String delTel = rs.getString(delTel);
//				Date del_birthday = rs.getDate(del_birthday);
//				但是我們return 只能一種型別或是物件所以要使用包裝資料(包裝成bean)
//				delVO = new DelVO();
//				delVO.setDelId(delID);
//				delVO.setDelName(delName);
//				delVO.setDelAccount(delAccount);
//				delVO.setDelPassword(delPassword);
//				delVO.setDelTel(delTel);
				// 把上面程式碼濃縮
				delVO = new DelVO();
				delVO.setDelID(delID);
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
	

}
