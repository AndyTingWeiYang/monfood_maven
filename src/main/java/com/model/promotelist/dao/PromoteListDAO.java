package com.model.promotelist.dao;

import java.util.List;

import com.model.promotelist.PromoteListVO;

public interface PromoteListDAO {

		Integer insert(PromoteListVO promoteListVO);
		
		List<PromoteListVO> getAll();
		
		PromoteListVO findByPrimaryKey(Integer promoteId);
		
		PromoteListVO findByCode(String promoteCode);
		
		void update(PromoteListVO promoteListVO);
		
//		void delete(Integer promoteId);
}
