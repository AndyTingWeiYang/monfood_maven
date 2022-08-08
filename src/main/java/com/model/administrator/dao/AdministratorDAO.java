package com.model.administrator.dao;

import java.util.List;

import com.model.administrator.AdministratorVO;
import com.model.order.OrderVO;

public interface AdministratorDAO {

	public List<AdministratorVO> findAll ();
	public void insert(AdministratorVO admin);
	public void update(AdministratorVO admin);
	public void delete(AdministratorVO adminID);
	public AdministratorVO selectByIDPassword(Integer adminID, String adminPassword);
	public OrderVO getOrderByID(Integer orderID);
}
