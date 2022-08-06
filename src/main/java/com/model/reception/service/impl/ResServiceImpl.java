package com.model.reception.service.impl;

import java.sql.Time;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;

import com.model.reception.ResVO;
import com.model.reception.dao.ResDAO;
import com.model.reception.dao.impl.ResJDBCDAOimpl;
import com.model.reception.service.ResService;

public class ResServiceImpl implements ResService {

	private ResDAO dao;

	public ResServiceImpl() {
		dao = new ResJDBCDAOimpl();

	}

	public void update(Map<String, Object> dataMap) {
		// TODO: Gson 轉換 Map To ResVo
		// 目前先手動轉 ResVo
		ResVO resVO = new ResVO();
		// request.getParameter("resID") => 轉型 Integer.parseInt(request.getParameter("resID"))
		resVO.setResId(MapUtils.getIntValue(dataMap, "resID"));
		
		//request.getParameterValues("bzWeekTime") 轉型 String[]並在字串中間join","做區隔
		resVO.setBzWeekTime(String.join(",", (String[]) dataMap.get("bzWeekTime")));
		
		// request.getParameter("bzOpenHours") => 轉型 時間Time.valueOf(request.getParameter("bzOpenHours"))
		resVO.setBzOpenHours(Time.valueOf(MapUtils.getString(dataMap, "bzOpenHours")));
		
		// request.getParameter("bzCloseHours") => 轉型 時間Time.valueOf(request.getParameter("bzCloseHours"))
		resVO.setBzCloseHours(Time.valueOf(MapUtils.getString(dataMap, "bzCloseHours")));
		
		// request.getParameter("shop_status") => 轉型 Integer.parseInt(request.getParameter("shop_status"))
		resVO.setStatus(MapUtils.getIntValue(dataMap, "shop_status"));

		dao.update(resVO);
		return;
	}

	public ResVO findByPrimaryKey(Integer resId) {
		return dao.findByPrimaryKey(resId);
	}



}
