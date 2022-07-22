package com.model.promotelist.service.Impl;

import java.sql.Date;

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
	public String adminAddPromoteList(PromoteListVO promoteListVO) {
		
		final String promoteCode = promoteListVO.getPromoteCode();
		if(promoteCode == null) {
			return "請輸入優惠代碼";
		}
		
		final Integer promotePrice = promoteListVO.getPromotePrice();
		if(promotePrice == null) {
			return "折扣金額請勿空白";
		}
		
		final Date starDate = promoteListVO.getStartDate();
		if(starDate == null) {
			return "請輸入開始日期";
		}
		
		final Date endDate = promoteListVO.getEndDate();
		if(endDate == null) {
			return "請輸入結束日期";
		}
		
		final Integer status = promoteListVO.getStatus();
		if(status == null) {
			return "請輸入優惠券狀態(數字)";
		}
		
		final Integer result = dao.insert(promoteListVO);
		if(result < 1) {
			return "系統錯誤";
			
		}
	
		
		return null;
	}

	@Override
	public String adminUpdatePromoteList(PromoteListVO promoteListVO) {
		
		
		
		final String promoteCode = promoteListVO.getPromoteCode();
		if(promoteCode == null) {
			return "請輸入優惠代碼";
		}
		
		final Integer promotePrice = promoteListVO.getPromotePrice();
		if(promotePrice == null) {
			return "折扣金額請勿空白";
		}
		
		final Date starDate = promoteListVO.getStartDate();
		if(starDate == null) {
			return "請輸入開始日期";
		}
		
		final Date endDate = promoteListVO.getEndDate();
		if(endDate == null) {
			return "請輸入結束日期";
		}
		
		final Integer status = promoteListVO.getStatus();
		if(status == null) {
			return "請輸入優惠券狀態(數字)";
		}
		
		final Integer result = dao.insert(promoteListVO);
		if(result < 1) {
			
		}
			return "系統錯誤";
		
	}

	@Override
	public PromoteListVO adminFindVO(PromoteListVO promoteListVO) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public PromoteListVO adminFindVO(PromoteListVO promoteListVO) {
//		
//		final Integer promoteId = promoteListVO.getPromoteId();
//		promoteListVO = dao.findByPrimaryKey(promoteId);
//		return promoteListVO;
//	}

}
