package com.model.order.dao;

import java.util.List;

import com.model.order.OrderVO;

public interface OrderDAO {

	Integer insert(OrderVO orderVO);

	List<OrderVO> getAll();

	OrderVO findByPrimaryKey(Integer orderId);

	void delete(Integer orderId);

	void update(OrderVO orderVO);

}