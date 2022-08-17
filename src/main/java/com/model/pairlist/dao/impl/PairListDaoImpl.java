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
	String PASSWORD = "password";

	// 將名單放進PairList
	// INSERT INTO `MonFood`.`PAIR_LIST` ( `USER_A_ID`, `USER_B_ID`,`PAIRED_DATE` ) VALUES ( ?, ?, ?);
	public boolean insert(PairListVo pairListVo) {
		int rowCount = 0;
		String sql = "INSERT INTO `MonFood`.`PAIR_LIST` ( `USER_A_ID`, `USER_B_ID`,`PAIRED_DATE` ) VALUES ( ?, ?, ?);";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, pairListVo.getUseraId());
			ps.setInt(2, pairListVo.getUserbId());
			ps.setDate(3,pairListVo.getPairedDate());
			rowCount = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount != 0;

	}

	// 更新A會員答案
	// update PAIR_LIST set USER_A_ANSWER = ? where USER_A_ID = ? and PAIRED_DATE = ?;
	//where第一個?為A會員ID，後面日期?為當天日期
	public boolean updateUseraAnswer(PairListVo pairListVo) {
		int rowCount = 0;
		String sql = "update PAIR_LIST set USER_A_ANSWER = ? where USER_A_ID = ? and PAIRED_DATE = ?;";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, pairListVo.getUseraAnswer());
			ps.setInt(2, pairListVo.getUseraId());
			ps.setDate(3, pairListVo.getPairedDate());
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	// 更新B會員答案
	// update PAIR_LIST set USER_B_ANSWER = ? where USER_B_ID = ? and PAIRED_DATE = ?;
	//where第一個?為A會員ID，後面日期?為當天日期
	public boolean updateUserbAnswer(PairListVo pairListVo) {
		int rowCount = 0;
		String sql = "update PAIR_LIST set USER_B_ANSWER = ? where USER_B_ID = ? and PAIRED_DATE = ?;";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, pairListVo.getUserbAnswer());
			ps.setInt(2, pairListVo.getUserbId());
			ps.setDate(3, pairListVo.getPairedDate());
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	// 更改好友狀態>雙方接受配對成為好友
	// update PAIR_LIST set STATUS = 1 where USER_A_ANSWER = 1 and USER_B_ANSWER =1;
	public boolean updateStatus(PairListVo pairListVo) {
		int rowCount = 0;
		String sql = "update PAIR_LIST set STATUS = ? where USER_A_ANSWER = ? and USER_B_ANSWER = ?;";
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
//	select USER_B_ID from PAIR_LIST where USER_A_ID = ? and STATUS = 1
//	union
//	select USER_A_ID from PAIR_LIST where USER_B_ID = ? and STATUS = 1;
	public List<PairListVo> selectByIdAndStatus(Integer useraId ) {
		List<PairListVo> list = new ArrayList<PairListVo>();
		PairListVo pairListVo = null;
		String sql = "	select USER_B_ID from PAIR_LIST where USER_A_ID = ? and STATUS = ?" + "	union"
				+ "	select USER_A_ID from PAIR_LIST where USER_B_ID = ? and STATUS = ?;";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, useraId);
			ps.setInt(2, 1);
			ps.setInt(3, useraId);
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
//	select USER_A_ID from PAIR_LIST where USER_B_ID = ?
	public List<PairListVo> selectById(Integer useraId) {
		List<PairListVo> list = new ArrayList<PairListVo>();
		PairListVo pairListVo = null;	
		String sql = "select USER_A_ID from PAIR_LIST where USER_B_ID = ?";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, useraId);
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
	
	//尋找A會員是否已被配對過 (雙向2)  ( ?為A會員ID) 
//	select USER_B_ID from PAIR_LIST where USER_A_ID = ?
	public List<PairListVo> selectById2(Integer useraId) {
		List<PairListVo> list = new ArrayList<PairListVo>();
		PairListVo pairListVo = null;	
		String sql = "select USER_B_ID from PAIR_LIST where USER_A_ID = ?";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, useraId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pairListVo = new PairListVo();
				pairListVo.setUseraId(rs.getInt(1));
				list.add(pairListVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//尋找當日配對者
	//前面?為A會員ID，後面日期?為當天日期
//	select USER_B_ID from PAIR_LIST where USER_A_ID = ? and PAIRED_DATE = ?
//	union
//	select USER_A_ID from PAIR_LIST where USER_B_ID = ? and PAIRED_DATE = ?;
	public List<PairListVo> selectByIdAndPairedDate(Integer useraId, java.sql.Date pairedDate ) {
		List<PairListVo> list = new ArrayList<PairListVo>();
		PairListVo pairListVo = null;
		String sql = "	select USER_B_ID from PAIR_LIST where USER_A_ID = ? and PAIRED_DATE = ?" + "union"
				+ "	select USER_A_ID from PAIR_LIST where USER_B_ID = ? and PAIRED_DATE = ?;";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, useraId);
			ps.setDate(2, pairedDate);
			ps.setInt(3, useraId);
			ps.setDate(4, pairedDate);
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
	
	
}
