package com.model.reception.service;

import java.util.List;
import java.util.Map;

import com.model.order.OrderVO;
import com.model.res.ResVO;

public interface ResService {

	void update(Map<String, Object> dataMap);

	ResVO findByPrimaryKey(Integer resId);

	List<Map<String, Object>> findByOrder(Integer resId, String orderStatus);

	List<Map<String, Object>> updateOrderStatus(Map<String, Object> dataMap);

}
