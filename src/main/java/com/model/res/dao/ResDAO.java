package com.model.res.dao;

import java.util.List;

import com.model.res.ResVO;


public interface ResDAO {
	int insert(ResVO resVO);
	void update(ResVO resVO);
	String updatePassword(ResVO resVO);
	void delete(Integer resId);
	public ResVO selectByResId(Integer resId);
	public ResVO selectByResAccount(String resAccount);
	List<ResVO> getAll();
	List<ResVO> isDuplicateAccount(String resAccount);
	
}
