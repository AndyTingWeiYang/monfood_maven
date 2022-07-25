package com.model.promotedetail.service;

import java.util.List;


import com.model.promotedetail.PromoteDetailVO;
import com.model.promotedetail.dao.PromoteDetailDAO;

public interface PromoteDetailService {

	
	//add
	String AddPromoteDetail(PromoteDetailVO promoteDetailVO);
	
	//getone
	PromoteDetailVO FindPromoteDetailOne(PromoteDetailVO promoteDetailVO);
	
	}

