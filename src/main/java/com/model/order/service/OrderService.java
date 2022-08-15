package com.model.order.service;

import java.sql.SQLException;
import java.util.List;

import com.google.gson.JsonElement;
import com.model.monster.MonsterVO;
import com.model.order.OrderVO;
import com.model.promotelist.PromoteListVO;

public interface OrderService {

	OrderVO adminFindOrderId(OrderVO orderVO);
	List<OrderVO> adminFindOrderAll();
	PromoteListVO promoteCheck(PromoteListVO promoteListVO);
	MonsterVO monsCheck(Integer userId) throws SQLException;
	Integer orderTimes(Integer userId);
	List<OrderVO> getAllForUser(Integer userId);
	List<OrderVO> getAllProductUser(Integer userId);
	Integer updateRating(OrderVO orderVO);
	String ecpayValidation(List<String> nameList, Integer orderId, OrderVO orderVO);
	Integer createOrder(List<JsonElement> list, OrderVO orderVO) throws SQLException;
	Integer updateDelId(OrderVO orderVO);
}