package com.model.order.service;

import com.model.order.OrderVO;

public interface OrderService {

	String adminFindOrderId(OrderVO orderVO);

	OrderVO adminFindVO(OrderVO orderVO);

}