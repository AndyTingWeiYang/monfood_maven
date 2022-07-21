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
	public String adminFindOrderId(OrderVO orderVO) {
		
		final Integer orderId = orderVO.getOrderId();
		
		if(orderId == null) {
			return "請輸入數字";
		}
		
		return null;
	}
	
	@Override
	public OrderVO adminFindVO(OrderVO orderVO) {
		
		final Integer orderId = orderVO.getOrderId();
		orderVO = dao.findByPrimaryKey(orderId);
		return orderVO;
	}
	
	
}
