package com.model.order.service;

import java.util.List;

import com.model.order.OrderVO;

public interface OrderService {

	OrderVO adminFindOrderId(OrderVO orderVO);
	List<OrderVO> adminFindOrderAll();
}