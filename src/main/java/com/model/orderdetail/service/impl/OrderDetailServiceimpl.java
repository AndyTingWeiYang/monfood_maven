package com.model.orderdetail.service.impl;

import com.model.orderdetail.OrderDetailVO;
import com.model.orderdetail.dao.OrderDetailDAO;
import com.model.orderdetail.dao.impl.OrderDetailDAOimpl;
import com.model.orderdetail.service.OrderDetailService;

public class OrderDetailServiceimpl implements OrderDetailService{
	
	private OrderDetailDAO dao;
	
	public OrderDetailServiceimpl() {
		dao = new OrderDetailDAOimpl();
	}
	
	@Override
	public void createOrderDetail(OrderDetailVO orderDetailVO) {
		dao.insert(orderDetailVO);
	}
	
}
