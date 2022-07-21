package com.model.pairlist.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.pairlist.PairListVo;
import com.model.pairlist.dao.PairListDao;

public class PairListDaoImpl implements PairListDao {

	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/MonFood?serverTimezone=Asia/Taipei";
	String USER = "root";
	String PASSWORD = "12345678";

	// 將名單放進PairList
	// INSERT INTO `MonFood`.`PAIR_LIST` ( `USER_A_ID`, `USER_B_ID`) VALUES ( ?, ?);
	public boolean insert(PairListVo pairListVo) {
		int rowCount = 0;
		String sql = "INSERT INTO `MonFood`.`PAIR_LIST` ( `USER_A_ID`, `USER_B_ID`) VALUES ( ?, ?);";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, pairListVo.getUseraId());
			ps.setInt(2, pairListVo.getUserbId());
			rowCount = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount != 0;

	}

	// 更新A會員答案
	// update PAIR_LIST set USER_A_ANSWER = ? where USER_A_ID = ? and PAIR_ID = ?;

	public boolean updateUseraAnswer(PairListVo pairListVo) {
		int rowCount = 0;
		String sql = "update PAIR_LIST set USER_A_ANSWER = ? where USER_A_ID = ? and PAIR_ID = ?;";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, pairListVo.getUseraAnswer());
			ps.setInt(2, pairListVo.getUseraId());
			ps.setInt(3, pairListVo.getPairId());
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	// 更新B會員答案
	// 需要嗎? 好像要?[to be checked]
	// update PAIR_LIST set USER_B_ANSWER = ? where USER_B_ID = ? and PAIR_ID = ?;
	public boolean updateUserbAnswer(PairListVo pairListVo) {
		int rowCount = 0;
		String sql = "update PAIR_LIST set USER_B_ANSWER = ? where USER_B_ID = ? and PAIR_ID = ?;";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, pairListVo.getUserbAnswer());
			ps.setInt(2, pairListVo.getUserbId());
			ps.setInt(3, pairListVo.getPairId());
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	// 更改好友狀態>雙方接受配對成為好友
	// update PAIR_LIST set status = 1 where USER_A_ANSWER = 1 and USER_B_ANSWER =1;
	public boolean updateStatus(PairListVo pairListVo) {
		int rowCount = 0;
		String sql = "update PAIR_LIST set status = ? where USER_A_ANSWER = ? and USER_B_ANSWER = ?;";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, 1);
			ps.setInt(2, 1);
			ps.setInt(3, 1);
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	// 查找A會員好友 ?為A會員ID > 查詢結果為B會員ID
	// union不會重複結果；union all會重複
//	select USER_B_ID from PAIR_LIST where USER_A_ID = ? and status = 1
//	union
//	select USER_A_ID from PAIR_LIST where USER_B_ID = ? and status = 1;
	public List<PairListVo> selectByIdAndStatus(Integer userAId ) {
		List<PairListVo> list = new ArrayList<PairListVo>();
		PairListVo pairListVo = null;
		String sql = "	select USER_B_ID from PAIR_LIST where USER_A_ID = ? and status = ?" + "	union"
				+ "	select USER_A_ID from PAIR_LIST where USER_B_ID = ? and status = ?;";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, userAId);
			ps.setInt(2, 1);
			ps.setInt(3, userAId);
			ps.setInt(4, 1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pairListVo = new PairListVo();
				pairListVo.setUserbId(rs.getInt(1));
				pairListVo.setUseraId(rs.getInt(1));
				list.add(pairListVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	//尋找A會員是否已被配對過 (雙向)  ( ?為A會員ID) 
	//這樣查找好友好像可以不用雙向? [to be checked]
//	select USER_A_ID from PAIR_LIST where USER_B_ID = ?
//	union
//	select USER_B_ID from PAIR_LIST where USER_A_ID = ?;
	public List<PairListVo> selectById(Integer userAId) {
		List<PairListVo> list = new ArrayList<PairListVo>();
		PairListVo pairListVo = null;	
		String sql = "select USER_A_ID from PAIR_LIST where USER_B_ID = ?"
				+ "union"
				+ "select USER_B_ID from PAIR_LIST where USER_A_ID = ?;";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, userAId);
			ps.setInt(2, userAId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pairListVo = new PairListVo();
				pairListVo.setUserbId(rs.getInt(1));
				list.add(pairListVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	

	// test
	public static void main(String[] args) {
//		// insert
//		PairListDao dao = new PairListDaoImpl();
//		PairListVo vo = new PairListVo();
//		vo.setUseraId(3);
//		vo.setUserbId(5);
//		dao.insert(vo);
//
//		// updateupdateUseraAnswer
//		vo.setUseraAnswer(1);
//		vo.setUseraId(1);
//		vo.setPairId(1);
//		dao.updateUseraAnswer(vo);
//
//		// updateupdateUserbAnswer
//		vo.setUserbAnswer(1);
//		vo.setUserbId(2);
//		vo.setPairId(1);
//		dao.updateUserbAnswer(vo);
//
//		// updateStatus
//		dao.updateStatus(vo);
//
//		// selectByIdAndStatus
//		// [to be revised]
//		dao.selectByIdAndStatus(1);
//		System.out.println(vo.getUserbId());
//
//		// selectByIdAndStatus
//		List<PairListVo> list = dao.selectByIdAndStatus(1);
//		for(PairListVo alist: list) {
//			System.out.print(alist.getUserbId() + ",");
//		}
//		
//		//selectById
//		List<PairListVo> list = dao.selectByIdAndStatus(1);
//		for(PairListVo alist: list) {
//			System.out.print(alist.getUserbId() + ",");
//		}
//
	}

}
