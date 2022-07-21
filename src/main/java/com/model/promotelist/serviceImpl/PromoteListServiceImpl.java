package com.model.promotelist.serviceImpl;

import java.util.List;

import com.model.promotelist.PromoteListVO;
import com.model.promotelist.dao.PromoteListDAO;
import com.model.promotelist.dao.Impl.PromoteListJDBCDAO;

public class PromoteListServiceImpl {

	
	private PromoteListDAO dao;

	public PromoteListServiceImpl() {
		dao = new PromoteListJDBCDAO();
	}

	public PromoteListVO addPromoteList(String promoteCode, Integer promotePrice, 
			java.sql.Date startDate, java.sql.Date endDate, Integer status) {

		PromoteListVO promoteListVO = new PromoteListVO();

		promoteListVO.setPromoteCode(promoteCode);
		promoteListVO.setPromotePrice(promotePrice);
		promoteListVO.setStartDate(startDate);
		promoteListVO.setEndDate(endDate);
		promoteListVO.setStatus(status);

		dao.insert(promoteListVO);

		return promoteListVO;
	}

	public PromoteListVO updatePromoteList(String promoteCode, Integer promotePrice, 
			java.sql.Date startDate, java.sql.Date endDate, Integer status, Integer promoteId) {

		PromoteListVO promoteListVO = new PromoteListVO();

		promoteListVO.setPromoteCode(promoteCode);
		promoteListVO.setPromotePrice(promotePrice);
		promoteListVO.setStartDate(startDate);
		promoteListVO.setEndDate(endDate);
		promoteListVO.setStatus(status);
		promoteListVO.setPromoteId(promoteId);
	
		dao.update(promoteListVO);

		return promoteListVO;
	}

	public void deletePromoteList(Integer promoteId) {
		dao.delete(promoteId);
	}

	public PromoteListVO getOnePromoteList(Integer promoteId) {
		return dao.findByPrimaryKey(promoteId);
	}

	public List<PromoteListVO> getAll() {
		return dao.getAll();
	}
	
	
	
}
