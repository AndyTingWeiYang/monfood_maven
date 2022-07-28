package com.model.promotedetail.service;



import com.model.promotedetail.PromoteDetailVO;

public interface PromoteDetailService {

	
	//add
	String AddPromoteDetail(PromoteDetailVO promoteDetailVO);
	
	//getone
	PromoteDetailVO FindPromoteDetailOne(PromoteDetailVO promoteDetailVO);
	
	//getonebyCode
	PromoteDetailVO findPromteDetailByCode(PromoteDetailVO promoteDetailVO);
	
	}

