package com.model.promotelist.service.Impl;

import com.model.promotelist.PromoteListVO;
import com.model.promotelist.dao.PromoteListDAO;
import com.model.promotelist.dao.Impl.PromoteListJDBCDAO;
import com.model.promotelist.service.PromoteListService;

public class PromoteListServiceImpl implements PromoteListService {

	private PromoteListDAO dao;
		
		public PromoteListServiceImpl() {
			dao = new PromoteListJDBCDAO();
		}
	
	@Override
	public String adminFindPromoteListId(PromoteListVO promoteListVO) {
		
		final Integer promoteId = promoteListVO.getPromoteId();
		
		if(promoteId == null) {
			return "請輸入數字";
		}
		
		return null;
	}

	@Override
	public PromoteListVO adminFindVO(PromoteListVO promoteListVO) {
		
		final Integer promoteId = promoteListVO.getPromoteId();
		promoteListVO = dao.findByPrimaryKey(promoteId);
		return promoteListVO;
	}

}
