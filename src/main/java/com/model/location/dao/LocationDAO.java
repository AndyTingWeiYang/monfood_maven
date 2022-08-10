package com.model.location.dao;

import java.sql.SQLException;
import java.util.List;

import com.model.location.LocationVO;

public interface LocationDAO {

	public void insert(LocationVO locationVO) throws SQLException;
	public List<LocationVO> findByPrimaryKey(Integer userId) throws SQLException;
	    
}