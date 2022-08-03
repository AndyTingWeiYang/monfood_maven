package com.model.del.service;

import java.sql.Timestamp;
import java.util.List;

import com.model.del.DelVO;
import com.model.order.OrderVO;

public interface DelService {
//	String userRegister(UserVO userVo);
//	public DelVO delLogin(DelVO delVO);
//	String userModify(UserVO userVO);
//	String resetPassword(UserVO userVO);
//	String resetAccountStatus(String userAccount);
//	String isDuplicateAccount(UserVO userVO);
	List<String> getDelRecord(Timestamp startDate, Timestamp endDate, Integer delID);
	DelVO updatePic(DelVO delVO);
	DelVO updateNoPic(DelVO delVO);
	DelVO delLogin(String delTel, String delPassword);
	DelVO getOnebyID(Integer delID);
	String delRegister(DelVO delVO);
	String acctAvailable(String delAccount);
	String telAvailable(String delAccount);
	
}
