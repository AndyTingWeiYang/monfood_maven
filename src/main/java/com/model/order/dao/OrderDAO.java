package com.model.order.dao;

import java.util.List;
import java.util.Map;

import com.model.order.OrderVO;

public interface OrderDAO {

	Integer insert(OrderVO orderVO);

	List<OrderVO> getAll();

	OrderVO findByPrimaryKey(Integer orderId);

	void delete(Integer orderId);

	void update(OrderVO orderVO);

	Integer insertNoPromote(OrderVO orderVO);

	Integer getOrderTimes(Integer userId);

	Double getRating(Integer resId);
	
	List<Map<String, Object>>  resFindOrderService(OrderVO orderVO);

	List<OrderVO> getAllById(Integer userId);

	List<OrderVO> getAllProductById(Integer userId);

	void updateDelId(OrderVO orderVO);

}