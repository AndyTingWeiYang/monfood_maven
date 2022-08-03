package com.model.order.service;

import java.sql.SQLException;
import java.util.List;

import com.model.monster.MonsterVO;
import com.model.order.OrderVO;
import com.model.promotelist.PromoteListVO;

public interface OrderService {

	OrderVO adminFindOrderId(OrderVO orderVO);
	List<OrderVO> adminFindOrderAll();
	Integer createOrder(OrderVO orderVO);
	PromoteListVO promoteCheck(PromoteListVO promoteListVO);
	MonsterVO monsCheck(Integer userId) throws SQLException;
	Integer orderTimes(Integer userId);
	List<OrderVO> getAllForUser(Integer userId);
}