package com.model.promotelist.service;

import java.util.List;

import com.model.order.OrderVO;
import com.model.promotelist.PromoteListVO;

public interface PromoteListService {

	//add
	String adminAddPromoteList(PromoteListVO promoteListVO);
	
	//getall
	List<PromoteListVO> adminFindPromoteListAll();
	
	//update
	String adminUpdatePromoteList(PromoteListVO promoteListVO);
	
	//getone
	PromoteListVO adminFindpromoteListOne(PromoteListVO promoteListVO);
	
	//getbyCode
	PromoteListVO adminFindListByCode(PromoteListVO promoteListVO);
	

}
