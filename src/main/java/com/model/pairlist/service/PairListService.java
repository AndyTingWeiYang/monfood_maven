package com.model.pairlist.service;



import java.util.List;

import com.model.user.UserVO;

public interface PairListService {
	
	// 取好友名單ID並取出資料
	List<UserVO> findFriends(Integer useraId);
	
	void match();
	

}
