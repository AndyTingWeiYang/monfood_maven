package com.model.user.service;

import com.model.user.UserVO;

public interface UserService {
	String userRegister(UserVO userVo);
	String userLogin(UserVO userVO);
	String userModify(UserVO userVO);
	String userForgetPass(UserVO userVO);
}
