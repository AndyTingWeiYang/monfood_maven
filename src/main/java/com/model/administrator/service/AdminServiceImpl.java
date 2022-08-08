package com.model.administrator.service;

import com.model.administrator.AdministratorVO;
import com.model.administrator.dao.AdministratorDAO;
import com.model.administrator.dao.impl.AdministratorDAOImpl;
import com.model.order.OrderVO;

public class AdminServiceImpl implements AdminService {
	AdministratorDAO dao = new AdministratorDAOImpl();
	@Override
	public AdministratorVO adminLogin(Integer adminID, String adminPassword) {
		return dao.selectByIDPassword(adminID, adminPassword);
	}
	@Override
	public OrderVO getOrderByID(Integer orderID) {
		return dao.getOrderByID(orderID);
	}

}
