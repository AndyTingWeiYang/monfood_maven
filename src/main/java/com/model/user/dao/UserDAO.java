package com.model.user.dao;

import java.util.List;

import com.model.user.UserVO;

public interface UserDAO {
	int insert(UserVO userVO);
	String updatePassword(UserVO userVO);
	void delete(Integer userId);
	public UserVO selectByUserId(Integer userId);
	public UserVO selectByUserAccount(String userAccount);
//	public UserVO updateByUserAccount(String userAccount);
	List<UserVO> getAll();
	List<Integer> getAllUserId(); 
	List<UserVO> isDuplicateAccount(String userAccount);
	
}
