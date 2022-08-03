package com.model.res.service;

import java.util.List;
import java.util.Map;

import com.model.res.ResDto;
import com.model.res.ResVO;

public interface ResService {
	// 前台登入註冊 
	String resRegister(ResVO resVO);
	public ResVO resLogin(ResVO resVO);
	String resetPassword(ResVO resVO);
	String isDuplicateAccount(ResVO resVO);
	// 後臺查詢
	List<ResVO> getByResName(String resName);
	ResVO getOneByResAccount(String resAccount);
	
	String resModify(ResVO resVO);
	List<ResVO> adminFindResAll();
	List<Map<String, Object>> adminFindByCategory(Integer resCategory);
	boolean updateResInfo(ResDto resDto);
	ResVO selectByResId(Integer resId);
	
	// 評分
	List<Map<String, Object>> getRate();
	// 搜尋商品
	List<Map<String, Object>> searchProduct(String searchPdt);
}
