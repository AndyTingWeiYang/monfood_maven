package com.model.location.service;

import java.sql.SQLException;
import java.util.List;

import com.model.location.LocationVO;

public interface LocationService {

	List<LocationVO> getAllById(Integer userId) throws SQLException;

	void insert(LocationVO locationVO) throws SQLException;

}