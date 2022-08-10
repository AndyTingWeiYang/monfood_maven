package com.model.location.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.model.location.LocationVO;
import com.model.location.dao.LocationDAO;
import com.model.location.dao.impl.LocationJDBCDAOimpl;
import com.model.location.service.LocationService;

public class LocationServiceImpl implements LocationService{
	
	private LocationDAO dao;
	
	public LocationServiceImpl() {
		dao = new LocationJDBCDAOimpl();
	}
	
	@Override
	public List<LocationVO> getAllById(Integer userId) throws SQLException{
		List<LocationVO> list = dao.findByPrimaryKey(userId);
		return list;
	}
	
	@Override
	public void insert(LocationVO locationVO) throws SQLException {
		dao.insert(locationVO);
	}
}
