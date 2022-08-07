package com.model.res.dao;

import java.util.List;
import java.util.Map;

import com.model.res.ResDto;
import com.model.res.ResVO;

public interface ResDAO {
	int insert(ResVO resVO);

	void update(ResVO resVO);

	String updatePassword(ResVO resVO);

	void delete(Integer resId);

	public ResVO selectByResId(Integer resId);

	public ResVO selectByResAccount(String resAccount);

	List<ResVO> selectByResName(String resName);

	List<ResVO> getAll();

	List<ResVO> isDuplicateAccount(String resAccount);

	Map<String, Object> updateResInfo(ResDto resDto);

	List<Map<String, Object>> selectByCategory(Integer resCategory);

	List<Map<String, Object>> getRate();

	List<Map<String, Object>> searchProduct(String searchPdt);

	List<ResVO> getResForZipcode();

	List<Map<String, Object>> getResComment(Integer resId);

	Map<String, Object> getToResPage(Integer resId);

	Map<String, Object> selectResInfo(Integer resID);

}
