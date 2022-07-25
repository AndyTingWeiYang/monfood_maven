package com.model.promotedetail.service.Impl;

import java.sql.Date;

import com.model.promotedetail.PromoteDetailVO;
import com.model.promotedetail.dao.PromoteDetailDAO;
import com.model.promotedetail.dao.Impl.PromoteDetailJDBCDAO;
import com.model.promotedetail.service.PromoteDetailService;


public class PromoteDetailServiceImpl implements PromoteDetailService{
	private PromoteDetailDAO dao;
	
	public PromoteDetailServiceImpl() {
		dao = new PromoteDetailJDBCDAO();
	}
	
	
	@Override
	public String AddPromoteDetail(PromoteDetailVO promoteDetailVO) {
		
		final Integer promoteId = promoteDetailVO.getPromoteId();
		if(promoteId == null) {
			return "優惠券編號請勿空白";
		}
		
		final Integer userId = promoteDetailVO.getUserId();
		if(userId == null) {
			return "使用者編號請勿空白";
		}
		
		final Integer usedStatus = promoteDetailVO.getUsedStatus();
		if(usedStatus == null) {
			return "請輸入狀態";
		}
	
		final Integer result = dao.insert(promoteDetailVO);
		if(result < 1) {
			return "系統錯誤";
			
		}
		
		return null;
	
	}

	@Override
	public PromoteDetailVO FindPromoteDetailOne(PromoteDetailVO promoteDetailVO) {
		
		final Integer promoteId = promoteDetailVO.getPromoteId();
		
		if(promoteId == null) {
			return null;
		}
		
		promoteDetailVO = dao.findByID(promoteId);
		// validate if the data exists in table
		if(promoteDetailVO == null) {
			return null;
		}
		
		// data exists, return to controller
		return promoteDetailVO;
		
	
	}

}
