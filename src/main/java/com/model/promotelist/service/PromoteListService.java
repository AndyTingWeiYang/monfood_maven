package com.model.promotelist.service;

import com.model.promotelist.PromoteListVO;

public interface PromoteListService {


	String adminAddPromoteList(PromoteListVO promoteListVO);
	
	String adminUpdatePromoteList(PromoteListVO promoteListVO);
	
	PromoteListVO adminFindVO(PromoteListVO promoteListVO);
	

}
