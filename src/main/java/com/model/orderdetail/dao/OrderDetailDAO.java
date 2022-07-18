package com.model.orderdetail.dao;

import java.util.List;

import com.model.orderdetail.OrderDetailVO;

public interface OrderDetailDAO {

	void insert(OrderDetailVO orderDetailVO);

	List<OrderDetailVO> getAll();

	OrderDetailVO findByPrimaryKey(Integer productId, Integer orderId);

	void delete(Integer productId, Integer orderId);

	void update(OrderDetailVO orderDetailVO);

}