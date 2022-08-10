package com.model.promotelist.dao;

import java.util.List;
import java.util.Map;

import com.model.promotelist.PromoteListVO;

public interface PromoteListDAO {

		Integer insert(PromoteListVO promoteListVO);
		
		List<PromoteListVO> getAll();
		
		PromoteListVO findByPrimaryKey(Integer promoteId);
		
		PromoteListVO findByCode(String promoteCode);
		
		Integer update (PromoteListVO promoteListVO);
		
		Map<String, Object> showPromote();
}
