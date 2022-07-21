package com.model.res.dao;

import java.util.List;

import com.model.res.ResVO;


public interface ResDAO {
	void insert(ResVO resVO);
	void update(ResVO resVO);
	void delete(Integer resId);
	public ResVO selectByResId(Integer resId);
	List<ResVO> getAll();
}
