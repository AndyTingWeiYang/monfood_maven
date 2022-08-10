package com.model.promotelist.service;

import java.util.List;
import java.util.Map;

import com.model.promotelist.PromoteListVO;

public interface PromoteListService {

	//add
	String adminAddPromoteList(PromoteListVO promoteListVO);
	
	//getall
	List<PromoteListVO> adminFindPromoteListAll();
	
	//update
	Integer adminUpdatePromoteList(PromoteListVO promoteListVO);

	//getone
	PromoteListVO adminFindpromoteListOne(PromoteListVO promoteListVO);
	
	//getbyCode
	PromoteListVO adminFindListByCode(PromoteListVO promoteListVO);
	
	Map<String, Object> showPromote();
}
