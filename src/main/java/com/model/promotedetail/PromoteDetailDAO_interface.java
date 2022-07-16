package com.model.promotedetail;

import java.util.List;

import com.model.promotedetail.PromoteDetailVO;

public interface PromoteDetailDAO_interface {

      void insert(PromoteDetailVO promoteDetailVO);
	
	  	List<PromoteDetailVO> getAll();
	
	  	PromoteDetailVO findByPrimaryKey(Integer promoteId);
	
	  	void delete(Integer promoteId);
	
	  	void update(PromoteDetailVO promoteDetailVO);
}
