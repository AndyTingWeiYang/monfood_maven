package com.model.del.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.model.del.DelDAO;
import com.model.del.DelDAOImpl;
import com.model.del.DelVO;
import com.model.order.OrderVO;

public class DelServiceImpl implements DelService {
	DelDAO dao;
	
	public DelServiceImpl() {
		dao = new DelDAOImpl();
	}
	
	@Override
	public List getDelRecord(Timestamp startDate, Timestamp endDate, Integer delID) {
		List orderRecord = new ArrayList();
		
		orderRecord.add(dao.getCost(startDate, endDate, delID));
		orderRecord.add(dao.getRideTimes(startDate, endDate, delID));
		orderRecord.add(dao.getComment(startDate, endDate, delID));
		return orderRecord;
	}

	@Override
	public DelVO updatePic(DelVO delVO) {
		return dao.update(delVO);
	}

	@Override
	public DelVO delLogin(String delTel, String delPassword) {
		DelVO loginResult = dao.login(delTel, delPassword);
		return loginResult;
	}

	@Override
	public DelVO updateNoPic(DelVO delVO) {
		return dao.updateWithoutPic(delVO);
	}

	@Override
	public DelVO getOnebyID(Integer delID) {
		return dao.findByDelID(delID);
	}

	@Override
	public String delRegister(DelVO delVO) {
		DelVO resultBean = dao.findByaccount(delVO.getDelAccount());
		if(resultBean.getDelAccount()==null) {
			delVO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			dao.add(delVO);
			DelVO newVO = dao.findByaccount(delVO.getDelAccount());
			String regiResult = "新增成功" + "," + String.valueOf(newVO.getDelID());
			return regiResult;
		}
		return "sth wrong";
	}

	@Override
	public String acctAvailable(String delAccount) {
		DelVO resultBean = dao.findByaccount(delAccount);
		if(resultBean.getDelAccount()==null) {
			return "可以喔~";
		} else {
			return "這個帳號有人用囉";
		}
	}

	@Override
	public String telAvailable(String delTel) {
		DelVO resultBean = dao.findByTel(delTel);
		if(resultBean.getDelTel()==null) {
			return "可以喔~";
		} else {
			return "這個電話有人用囉";
		}
	
	}

	@Override
	public String resetPassword(String delAccount, String delPassword) {
		DelVO delVO = dao.findByaccount(delAccount);
		if(delVO.getDelID()==null) {
			return "此帳號尚未註冊";
		} else {
			delVO.setDelPassword(delPassword);
			dao.updateWithoutPic(delVO);
		}
		return "修改密碼成功";
	}
}
