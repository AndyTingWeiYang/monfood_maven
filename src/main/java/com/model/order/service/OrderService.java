package com.model.order.service;

import java.util.List;

import com.model.monster.MonsterVO;
import com.model.order.OrderVO;
import com.model.promotelist.PromoteListVO;

public interface OrderService {

	OrderVO adminFindOrderId(OrderVO orderVO);
	List<OrderVO> adminFindOrderAll();
	Integer createOrder(OrderVO orderVO);
	PromoteListVO promoteCheck(PromoteListVO promoteListVO);
	MonsterVO monsCheck(Integer userId);
}