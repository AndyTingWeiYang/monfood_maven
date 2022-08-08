package com.model.administrator.service;

import java.sql.Timestamp;
import java.util.List;

import com.model.administrator.AdministratorVO;
import com.model.del.DelVO;
import com.model.order.OrderVO;

public interface AdminService {
	AdministratorVO adminLogin(Integer adminID, String adminPassword);
	OrderVO getOrderByID(Integer orderID);
}
