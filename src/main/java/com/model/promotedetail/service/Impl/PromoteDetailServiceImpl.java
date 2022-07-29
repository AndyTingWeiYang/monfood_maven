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
		final Integer userId = promoteDetailVO.getUserId();
		
		if(promoteId == null || userId == null) {
			return "ID為空值";
		}
		
		final Integer result = dao.insert(promoteDetailVO);
		
		if(result < 1) {
			return "系統錯誤";
			
		}
		
		return "新增成功";
	
	}

	@Override
	public Integer FindPromoteDetailOne(PromoteDetailVO promoteDetailVO) {
		
		final Integer promoteId = promoteDetailVO.getPromoteId();
		final Integer userId = promoteDetailVO.getUserId();
		
		if(promoteId == null || userId == null) {
			return null;
		}
		
		promoteDetailVO = dao.findByID(promoteId, userId);
		// validate if the data exists in table
		if(promoteDetailVO == null) {
			return 0;
		}
		
		// data exists, return to controller
		return promoteDetailVO.getUsedStatus();
	
	}


	@Override
	public PromoteDetailVO findPromteDetailByCode(PromoteDetailVO promoteDetailVO) {

		final String promoteCode = promoteDetailVO.getPromoteCode();
		
		if(promoteCode == null) {
			return null;
		}
		
		promoteDetailVO = dao.findByCode(promoteCode);
		// validate if the data exists in table
		if(promoteDetailVO == null) {
			return null;
		}
		
		// data exists, return to controller
		return promoteDetailVO;
	}

}
