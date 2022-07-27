package com.model.order.service.impl;

import java.util.List;

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
		
		// validate orderId
		if(orderId == null) {
			return null;
		}
		
		orderVO = dao.findByPrimaryKey(orderId);
		// validate if the data exists in table
		if(orderVO == null) {
			return null;
		}
		
		// data exists, return to controller
		return orderVO;
		
	}
	
	@Override
	public List<OrderVO> adminFindOrderAll(){
		List<OrderVO> list = dao.getAll();
		return list;
	}
	
	@Override
	public Integer createOrder(OrderVO orderVO) {
		Integer generatedKey = dao.insert(orderVO);
		if(generatedKey == null) {
			return null;
		}
		return generatedKey;
	}
	
	
}
