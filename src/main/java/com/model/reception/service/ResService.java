package com.model.reception.service;

import java.util.Map;

import com.model.order.OrderVO;
import com.model.res.ResVO;

public interface ResService {

	void update(Map<String, Object> dataMap);

	ResVO findByPrimaryKey(Integer resId);

	Map<String, Object> findByOrder(String orderId);

	void updateOrderStatus(OrderVO orderVO);
	
}
