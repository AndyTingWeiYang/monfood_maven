package com.model.promotelist.service;

import java.util.List;

import com.model.order.OrderVO;
import com.model.promotelist.PromoteListVO;

public interface PromoteListService {


	String adminAddPromoteList(PromoteListVO promoteListVO);
	
	List<PromoteListVO> adminFindPromoteListAll();
	
	String adminUpdatePromoteList(PromoteListVO promoteListVO);
	
	PromoteListVO adminFindVO(PromoteListVO promoteListVO);
	

}
