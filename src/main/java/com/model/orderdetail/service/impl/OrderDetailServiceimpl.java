package com.model.orderdetail.service.impl;

import com.model.orderdetail.OrderDetailVO;
import com.model.orderdetail.dao.OrderDetailDAO;
import com.model.orderdetail.dao.impl.OrderDetailDAOimpl;
import com.model.orderdetail.service.OrderDetailService;
import com.model.product.ProductVo;
import com.model.product.dao.ProductDao;
import com.model.product.dao.impl.ProductDAOImpl;

public class OrderDetailServiceimpl implements OrderDetailService{
	
	private OrderDetailDAO dao;
	
	public OrderDetailServiceimpl() {
		dao = new OrderDetailDAOimpl();
	}
	
	@Override
	synchronized public Integer createOrderDetail(OrderDetailVO orderDetailVO) {
		
		Integer stock = null;
		Integer orderQty = orderDetailVO.getAmount();
		Integer balance = null;
		ProductVo productVo = new ProductVo();
		
		// checking stock
		ProductDao productDao = new ProductDAOImpl();
		// get product vo by product id
		productVo = productDao.findPic(orderDetailVO.getProductId().toString());
		// get stock
		stock = productVo.getStock();
		balance = stock - orderQty;
		// reset stock
		productVo.setStock(balance);
		productDao.updateStock(productVo);
		// create order detail
		dao.insert(orderDetailVO);
		return 1;

		
	}
	
}
