package com.model.user.service;

import java.util.List;

import com.model.user.UserVO;

public interface UserService {
	String userRegister(UserVO userVo);
	public UserVO userLogin(UserVO userVO);
	String userModify(UserVO userVO);
	String resetPassword(UserVO userVO);
	String resetAccountStatus(String userAccount);
	String isDuplicateAccount(UserVO userVO);
	public UserVO getOneByUserAccount(String userAccount);
	List<UserVO> getOneByUserName(String userName);
	List<UserVO> getAll();
	UserVO getOneByUserId(UserVO userVO);
	String updateProfile(Integer userId, String data, String msg);
	String updateProfilePic(byte[] pic, Integer userId);
}
