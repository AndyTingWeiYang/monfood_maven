package com.model.res.service;

import com.model.res.ResVO;

public interface ResService {
	String resRegister(ResVO resVO);
	//登入
	public ResVO resLogin(ResVO resVO);
	String resModify(ResVO resVO);
	String resetPassword(ResVO resVO);
	String isDuplicateAccount(ResVO resVO);
}
