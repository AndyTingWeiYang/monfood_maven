package com.order.dao;

import java.util.List;

import com.order.OrderVO;

public interface OrderDAO {

	void insert(OrderVO orderVO);

	List<OrderVO> getAll();

	OrderVO findByPrimaryKey(Integer orderId);

	void delete(Integer orderId);

	void update(OrderVO orderVO);

}