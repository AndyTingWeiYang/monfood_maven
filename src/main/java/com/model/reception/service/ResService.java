package com.model.reception.service;

import java.util.Map;

import com.model.reception.ResVO;
import com.model.reception.dao.ResDAO;

public interface ResService {

	void update(Map<String, Object> dataMap);

	ResVO findByPrimaryKey(Integer resId);

//	boolean updateResInfo(ResDAO resDao);
}
