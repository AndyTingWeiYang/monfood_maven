package com.model.order.service.impl;

import java.nio.channels.AcceptPendingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.hibernate.loader.entity.NaturalIdEntityJoinWalker;
import org.quartz.jobs.ee.jms.SendDestinationMessageJob;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.model.monster.IMonsterDAO;
import com.model.monster.MonsterJDBCDAO;
import com.model.monster.MonsterVO;
import com.model.order.OrderVO;
import com.model.order.dao.OrderDAO;
import com.model.order.dao.impl.OrderJDBCDAOimpl;
import com.model.order.service.OrderService;
import com.model.orderdetail.OrderDetailVO;
import com.model.orderdetail.dao.OrderDetailDAO;
import com.model.orderdetail.dao.impl.OrderDetailDAOimpl;
import com.model.orderdetail.service.OrderDetailService;
import com.model.orderdetail.service.impl.OrderDetailServiceimpl;
import com.model.promotelist.PromoteListVO;
import com.model.promotelist.dao.PromoteListDAO;
import com.model.promotelist.dao.Impl.PromoteListJDBCDAO;
import com.model.user.dao.UserDAO;
import com.model.user.dao.impl.UserDAOImpl;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

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
	public List<OrderVO> getAllForUser(Integer userId) {
		List<OrderVO> list = dao.getAllById(userId);
		return list;
	}
	@Override
	public List<OrderVO> getAllProductUser(Integer userId) {
		List<OrderVO> productList = dao.getAllProductById(userId);
		return productList;
	}
	
	@Override
	public String ecpayValidation(List<String> nameList, Integer orderId, OrderVO orderVO) {
		
		Optional<String> reduce = nameList.stream().reduce((String acc, String curr) ->{
			return acc + "#" + curr;
		});
		String itemName = reduce.get();
			
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String orderDate = sdf.format(new Date());
		AllInOne allInOne = new AllInOne("");
		AioCheckOutALL aioCheckOutALL = new AioCheckOutALL();
		aioCheckOutALL.setMerchantTradeNo(orderId + "MonFood");
		aioCheckOutALL.setMerchantTradeDate(orderDate);
		aioCheckOutALL.setTotalAmount(orderVO.getTotal().toString());
		aioCheckOutALL.setTradeDesc("test");
		aioCheckOutALL.setItemName(itemName);
		aioCheckOutALL.setClientBackURL("http://localhost:8080/monfood_maven/searching.html");
		aioCheckOutALL.setReturnURL("http://localhost:8080/monfood_maven/searching.html");
		aioCheckOutALL.setNeedExtraPaidInfo("N");
		
		return allInOne.aioCheckOut(aioCheckOutALL, null);
		
	}
	
	@Override
	public Integer createOrder(List<com.google.gson.JsonElement> list , OrderVO orderVO) {
		Gson gson = new Gson();
		Integer generatedKey = null;
		OrderDetailService orderDetailService = new OrderDetailServiceimpl();
		
		// no promote code
		if (orderVO.getPromoteId() == null) {
			generatedKey = dao.insertNoPromote(orderVO);
			if(generatedKey == null) {
				return -1;
			}
			
			for (JsonElement jsonElement : list) {
				OrderDetailVO orderDetailVO = new OrderDetailVO();
				orderDetailVO = gson.fromJson(jsonElement, OrderDetailVO.class);
				orderDetailVO.setOrderId(generatedKey);
				orderDetailService.createOrderDetail(orderDetailVO);
			}
		
			return generatedKey;
			
		} 	
		
		// with promote code
		generatedKey = dao.insert(orderVO);
		if(generatedKey == null) {
			return -1;
		}
		for (JsonElement jsonElement : list) {
			OrderDetailVO orderDetailVO = new OrderDetailVO();
			orderDetailVO = gson.fromJson(jsonElement, OrderDetailVO.class);
			orderDetailVO.setOrderId(generatedKey);
			orderDetailService.createOrderDetail(orderDetailVO);
		}
		
		return generatedKey;
	}
	
	@Override
	public PromoteListVO promoteCheck(PromoteListVO promoteListVO) {
		
		PromoteListDAO promoteListDAO = new PromoteListJDBCDAO();
		final String promoteCode = promoteListVO.getPromoteCode();
		if (promoteCode == null || "".equals(promoteCode.trim())) {
			return null;
		}

		promoteListVO = promoteListDAO.findByCode(promoteCode);
		return promoteListVO;
	}
	
	@Override
	public MonsterVO monsCheck(Integer userId) throws SQLException {
		Integer orderTimes = dao.getOrderTimes(userId);
		if (orderTimes == null) {
			orderTimes = 0;
		}
		
		UserDAO userDAO = new UserDAOImpl();
		IMonsterDAO monsterDAO = new MonsterJDBCDAO();
		MonsterVO monsterVO = new MonsterVO();
		System.out.println(userId);
		System.out.println(orderTimes);
		
		if (orderTimes / 10 < 1 || orderTimes == 0) {
			System.out.println(orderTimes);

			userDAO.updateMonsLv(1, userId);
			monsterVO = monsterDAO.findByPrimaryKey(1);
			return monsterVO;
			
		}else if (orderTimes / 10 == 1) {
			userDAO.updateMonsLv(2, userId);
			monsterVO = monsterDAO.findByPrimaryKey(2);
			return monsterVO;
			
		}else if (orderTimes / 10 == 2) {
			userDAO.updateMonsLv(3, userId);
			monsterVO = monsterDAO.findByPrimaryKey(3);
			return monsterVO;
			
		}else if (orderTimes / 10 == 3) {
			userDAO.updateMonsLv(4, userId);
			monsterVO = monsterDAO.findByPrimaryKey(4);
			return monsterVO;
			
		}else if (orderTimes / 10 >= 4) {
			userDAO.updateMonsLv(5, userId);
			monsterVO = monsterDAO.findByPrimaryKey(5);
			return monsterVO;
			
		}else {
			return null;
		}
	}
	
	@Override
	public Integer orderTimes(Integer userId) {
		Integer orderTimes = dao.getOrderTimes(userId);
		if (orderTimes == null) {
			orderTimes = 0;
		}
		return orderTimes;
	}
	
	@Override
	public Integer updateRating(OrderVO orderVO) {
		
		if (orderVO == null) {
			return -1;
		}
		
		dao.update(orderVO);
		return 1;
	}
	
	
	
	
}
