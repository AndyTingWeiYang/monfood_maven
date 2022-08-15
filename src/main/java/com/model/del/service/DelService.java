package com.model.del.service;

import java.sql.Timestamp;
import java.util.List;

import com.model.del.DelVO;
import com.model.order.OrderVO;

public interface DelService {
	DelVO getOnebyID(Integer delID);
	List<String> getDelRecord(Timestamp startDate, Timestamp endDate, Integer delID);
	DelVO updatePic(DelVO delVO);
	DelVO updateNoPic(DelVO delVO);

//login and register	
	DelVO delLogin(String delTel, String delPassword);
	String delRegister(DelVO delVO);
	String acctAvailable(String delAccount);
	String telAvailable(String delAccount);
	String resetPassword(String delAccount, String delPassword);
	
}
