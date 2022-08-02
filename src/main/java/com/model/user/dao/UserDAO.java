package com.model.user.dao;

import java.util.List;

import com.model.user.UserVO;

public interface UserDAO {
	int insert(UserVO userVO);
	String updatePassword(UserVO userVO);
	String updateAccountStatus(String userAccount);
	void delete(Integer userId);
	public UserVO selectByUserId(Integer userId);
	public UserVO selectByUserAccount(String userAccount);
	List<UserVO> selectByUserName(String userName);
//	public UserVO updateByUserAccount(String userAccount);
	List<UserVO> getAll();
	List<Integer> getAllUserId(); 
	List<UserVO> isDuplicateAccount(String userAccount);
	String updateMonsLv(Integer monsLevel, Integer userId);
	String updateBudget(Integer budget, Integer userId);
	String updateKcal(Integer kcal, Integer userId);
	String updateProfile(String profile, Integer userId);
	String updateProfilePic(byte[] pic, Integer userId);
	
}
