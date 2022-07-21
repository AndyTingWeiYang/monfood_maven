package com.model.order.service.impl;

import com.model.order.OrderVO;
import com.model.order.dao.OrderDAO;
import com.model.order.dao.impl.OrderJDBCDAOimpl;
import com.model.order.service.OrderService;

public class OrderServiceImpl implements OrderService {
	
	private OrderDAO dao;
	
	public OrderServiceImpl() {
		dao = new OrderJDBCDAOimpl();
	}
	
	@Override
	public OrderVO adminFindOrderId(OrderVO orderVO) {
		
		final Integer orderId = orderVO.getOrderId();
		orderVO = dao.findByPrimaryKey(orderId);

		if(orderId == null) {
			return null;
		}
		
		return orderVO;
		
	}
	
	
}
