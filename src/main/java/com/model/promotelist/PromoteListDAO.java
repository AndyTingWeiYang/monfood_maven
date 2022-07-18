package com.model.promotelist;

import java.util.List;

import com.model.promotelist.PromoteListVO;

public interface PromoteListDAO {

	void insert(PromoteListVO promoteListVO);
		
		List<PromoteListVO> getAll();
		
		PromoteListVO findByPrimaryKey(Integer promoteId);
		
		void delete(Integer promoteId);
		
		void update(PromoteListVO promoteListVO);
		
}
