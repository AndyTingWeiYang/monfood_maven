package com.model.promotedetail.dao;

import java.util.List;


import com.model.promotedetail.PromoteDetailVO;

public interface PromoteDetailDAO {
	
	  	PromoteDetailVO findByCode(String promoteCode);
	
	  	void update(PromoteDetailVO promoteDetailVO);

		Integer insert(PromoteDetailVO promoteDetailVO);

		PromoteDetailVO findByID(Integer promoteId, Integer userId);
		
		
}
