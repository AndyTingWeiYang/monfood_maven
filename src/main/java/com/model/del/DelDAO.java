package com.model.del;

import java.sql.Timestamp;
import java.util.List;

import com.model.order.OrderVO;

public interface DelDAO {
	void add(DelVO delVO);

	DelVO update(DelVO delVO);
	DelVO updateWithoutPic(DelVO delVO);

//Orderrecord
	String getCost(Timestamp startDate, Timestamp endDate, Integer delID);
	String getRideTimes(Timestamp startDate, Timestamp endDate, Integer delID);
	List<String> getComment(Timestamp startDate, Timestamp endDate, Integer delID);
	
	DelVO login(String delTel, String delPassword);

	DelVO findByaccount(String delAccount);
	DelVO findByTel(String delTel);
	DelVO findByDelID(Integer delID);
	DelVO findByDelNamePassword(String delName, String delTel);
//ForAdmin	
	List<DelVO> getAll();

	void delete(Integer delID);
}
