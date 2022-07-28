package com.model.promotelist.service;

import java.util.List;



import com.model.promotelist.PromoteListVO;

public interface PromoteListService {

	//add
	String adminAddPromoteList(PromoteListVO promoteListVO);
	
	//getall
	List<PromoteListVO> adminFindPromoteListAll();
	
	//update
	PromoteListVO adminUpdatePromoteList(PromoteListVO promoteListVO);

	//getone
	PromoteListVO adminFindpromoteListOne(PromoteListVO promoteListVO);
	
	//getbyCode
	PromoteListVO adminFindListByCode(PromoteListVO promoteListVO);
	

}
