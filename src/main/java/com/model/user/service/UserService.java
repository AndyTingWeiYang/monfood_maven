package com.model.user.service;

import com.model.user.UserVO;

public interface UserService {
	String userRegister(UserVO userVo);
	public UserVO userLogin(UserVO userVO);
	String userModify(UserVO userVO);
	String resetPassword(UserVO userVO);
	String isDuplicateAccount(UserVO userVO);
}
