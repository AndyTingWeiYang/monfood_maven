package com.model.order.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.model.monster.IMonsterDAO;
import com.model.monster.MonsterJDBCDAO;
import com.model.monster.MonsterVO;
import com.model.order.OrderVO;
import com.model.order.dao.OrderDAO;
import com.model.order.dao.impl.OrderJDBCDAOimpl;
import com.model.order.service.OrderService;
import com.model.orderdetail.OrderDetailVO;
import com.model.orderdetail.service.OrderDetailService;
import com.model.orderdetail.service.impl.OrderDetailServiceimpl;
import com.model.product.ProductVo;
import com.model.product.dao.ProductDao;
import com.model.product.dao.impl.ProductDAOImpl;
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
		aioCheckOutALL.setClientBackURL("https://35.201.129.109:8443/monfood_maven/searching.html");
		aioCheckOutALL.setReturnURL("https://35.201.129.109:8443/monfood_maven/searching.html");
		aioCheckOutALL.setNeedExtraPaidInfo("N");
		
		return allInOne.aioCheckOut(aioCheckOutALL, null);
		
	}
	
	@Override
	synchronized public Integer createOrder(List<com.google.gson.JsonElement> list , OrderVO orderVO) throws SQLException {
		Gson gson = new Gson();
		Integer generatedKey = null;
		Integer total = 0;
		Integer monsDiscount = null;
		OrderDetailService orderDetailService = new OrderDetailServiceimpl();
		ProductVo productVo = new ProductVo();
		ProductDao productDao = new ProductDAOImpl();
		PromoteListDAO promoteListDAO = new PromoteListJDBCDAO();
		List<OrderDetailVO> productList = new ArrayList<OrderDetailVO>();

		for (JsonElement jsonElement : list) {
			
			OrderDetailVO orderDetailVO = new OrderDetailVO();
			orderDetailVO = gson.fromJson(jsonElement, OrderDetailVO.class);
			// get productVo by product ID
			productVo = productDao.findPic(orderDetailVO.getProductId().toString());
			// reset the product price from DB
			orderDetailVO.setOrderedPrice(productVo.getProductPrice());
			// reset total amount from DB
			total += productVo.getProductPrice()*orderDetailVO.getAmount();
			
			// stock check 
			Integer stock = null;
			Integer orderQty = orderDetailVO.getAmount();
			
			// checking stock
			productVo = productDao.findPic(orderDetailVO.getProductId().toString());
			stock = productVo.getStock();
			
			if (stock <= 0) {
				productVo.setProductStatus(2);
				productDao.updateStatus(productVo);
				return -1;
			}else if(stock < orderQty){
				return -1;
			}else {
				productList.add(orderDetailVO);
			}
			
		}
		
		// no promote code
		if (orderVO.getPromoteId() == null) {
			// reset monster discount from DB
			monsDiscount = monsCheck(orderVO.getUserId()).getDiscount();
			// reset del cost from DB
			orderVO.setDelCost(40);
			// reset total amount
			total += orderVO.getDelCost() - monsDiscount;
			// reset orderVO
			orderVO.setTotal(total);
			
			// create order
			generatedKey = dao.insertNoPromote(orderVO);
			if(generatedKey == null) {
				return -1;
			}
			
			// create order detail
			for (OrderDetailVO vo : productList) {
				vo.setOrderId(generatedKey);
				if(orderDetailService.createOrderDetail(vo) < 0) {
					return -1;
				}
			}
			
			return generatedKey;
			
		} 	
		
		// reset monster discount from DB
		monsDiscount = monsCheck(orderVO.getUserId()).getDiscount();
		// reset del cost from DB
		orderVO.setDelCost(40);
		// reset discount value from DB
		orderVO.setDiscount(promoteListDAO.findByPrimaryKey(orderVO.getPromoteId()).getPromotePrice());
		// reset total amount
		total += orderVO.getDelCost() - monsDiscount - orderVO.getDiscount();
		// reset orderVO
		orderVO.setTotal(total);
		
		// with promote code
		// create order
		generatedKey = dao.insert(orderVO);
		if(generatedKey == null) {
			return -1;
		}
		// create order detail
		for (OrderDetailVO vo : productList) {
			vo.setOrderId(generatedKey);
			orderDetailService.createOrderDetail(vo);
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
	
	@Override
	public Integer updateDelId(OrderVO orderVO) {
		if (orderVO == null) {
			return -1;
		}
		
		dao.updateDelId(orderVO);
		return 1;
	}
	
	
}
