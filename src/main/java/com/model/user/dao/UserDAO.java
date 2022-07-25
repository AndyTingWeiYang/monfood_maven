package com.model.user.dao;

import java.util.List;

import com.model.user.UserVO;

public interface UserDAO {
	int insert(UserVO userVO);
	void update(UserVO userVO);
	void delete(Integer userId);
	public UserVO selectByUserId(Integer userId);
	List<UserVO> getAll();
	List<UserVO> getAllUserId();
	List<UserVO> isDuplicateAccount(String userAccount);
	
}
