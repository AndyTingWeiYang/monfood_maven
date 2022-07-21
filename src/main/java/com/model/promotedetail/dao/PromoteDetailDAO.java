package com.model.promotedetail.dao;

import java.util.List;

import com.model.promotedetail.PromoteDetailVO;

public interface PromoteDetailDAO {
	
	  	List<PromoteDetailVO> getAll();
	
	  	PromoteDetailVO findByPrimaryKey(Integer promoteId);
	
	  	void update(PromoteDetailVO promoteDetailVO);
}
