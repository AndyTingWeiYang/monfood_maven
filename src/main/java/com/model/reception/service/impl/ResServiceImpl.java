package com.model.reception.service.impl;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MapUtils;

import com.model.order.OrderVO;
import com.model.reception.OrderStatus;
import com.model.reception.dao.ResDAO;
import com.model.reception.dao.impl.ResJDBCDAOimpl;
import com.model.reception.service.ResService;
import com.model.res.ResVO;

@SuppressWarnings("unchecked")
public class ResServiceImpl implements ResService {

	private ResDAO dao;

	public ResServiceImpl() {
		dao = new ResJDBCDAOimpl();

	}

	public void update(Map<String, Object> dataMap) {
		// TODO: Gson 轉換 Map To ResVo
		// 目前先手動轉 ResVo
		ResVO resVO = new ResVO();
		// request.getParameter("resID") => 轉型
		// Integer.parseInt(request.getParameter("resID"))
		resVO.setResId(MapUtils.getIntValue(dataMap, "resID"));

		// request.getParameterValues("bzWeekTime") 轉型 String[]並在字串中間join","做區隔
		// 原本是String再用,改了Integer失效
//		resVO.setBzWeekTime(String.join(",", (String[]) dataMap.get("bzWeekTime")));

		// 轉型成整數存入資料庫(新的 感謝阿仁!)
		resVO.setBzWeekTime(Integer.parseInt(String.join("", (String[]) dataMap.get("bzWeekTime"))));

		// request.getParameter("bzOpenHours") => 轉型
		// 時間Time.valueOf(request.getParameter("bzOpenHours"))
		resVO.setBzOpenHours(Time.valueOf(MapUtils.getString(dataMap, "bzOpenHours")));

		// request.getParameter("bzCloseHours") => 轉型
		// 時間Time.valueOf(request.getParameter("bzCloseHours"))
		resVO.setBzCloseHours(Time.valueOf(MapUtils.getString(dataMap, "bzCloseHours")));

		// request.getParameter("shop_status") => 轉型
		// Integer.parseInt(request.getParameter("shop_status"))
//		resVO.setStatus(MapUtils.getIntValue(dataMap, "shop_status"));

		dao.update(resVO);
		return;
	}

	public ResVO findByPrimaryKey(Integer resId) {
		return dao.findByPrimaryKey(resId);
	}

	@Override
	public List<Map<String, Object>> findByOrder(Integer resId, String orderStatus) {
		List<Map<String, Object>> tmpOrderList = dao.findByOrder(resId, orderStatus);

		// groupby
		Map<String, List<Map<String, Object>>> dataList = tmpOrderList.stream().collect(Collectors
				.groupingBy(map -> MapUtils.getString(map, "ORDER_ID"), LinkedHashMap::new, Collectors.toList()));

		List<Map<String, Object>> orderList = new ArrayList<>();
		for (String key : dataList.keySet()) {
			// groupby 過後的資料
			List<Map<String, Object>> orderGroupList = dataList.get(key);
			Map<String, Object> parentMap = orderGroupList.get(0);

			Map orderGroupMap = new HashMap<>();
			orderGroupMap.put("RES_NAME", MapUtils.getString(parentMap, "RES_NAME"));
			orderGroupMap.put("ORDER_ID", MapUtils.getString(parentMap, "ORDER_ID"));
			int statusIndex = Integer.parseInt(MapUtils.getString(parentMap, "ORDER_STATUS"));
			orderGroupMap.put("ORDER_STATUS", OrderStatus.getOrderStatus(statusIndex));
			orderGroupMap.put("TOTAL", MapUtils.getString(parentMap, "TOTAL"));
			orderGroupMap.put("ORDER_CREATE", MapUtils.getString(parentMap, "ORDER_CREATE"));
			orderGroupMap.put("BZ_LOCATION", MapUtils.getString(parentMap, "BZ_LOCATION"));
			orderGroupMap.put("RES_ID", MapUtils.getString(parentMap, "RES_ID"));
			orderGroupMap.put("NOTE", MapUtils.getString(parentMap, "NOTE"));
			orderGroupMap.put("RES_ACCOUNT", MapUtils.getString(parentMap, "RES_ACCOUNT"));
			orderGroupMap.put("USER_ID", MapUtils.getString(parentMap, "USER_ID"));

			List<Map<String, Object>> productList = new ArrayList<>();
			for (Map<String, Object> orderInnerMap : orderGroupList) {
				Map<String, Object> productMap = new HashMap<>();
				productMap.put("PRODUCT_NAME", MapUtils.getString(orderInnerMap, "PRODUCT_NAME"));
				productMap.put("PRODUCT_PRICE", MapUtils.getString(orderInnerMap, "PRODUCT_PRICE"));
				productMap.put("AMOUNT", MapUtils.getString(orderInnerMap, "AMOUNT"));
				productList.add(productMap);
			}

			orderGroupMap.put("productList", productList);
			orderList.add(orderGroupMap);
		}

		return orderList;
	}

	@Override
	public List<Map<String, Object>> updateOrderStatus(Map<String, Object> dataMap) {

		OrderVO orderVO = new OrderVO();
		orderVO.setResId(MapUtils.getInteger(dataMap, "resId"));
		orderVO.setOrderId(MapUtils.getInteger(dataMap, "orderId"));
		orderVO.setOrderStatus(MapUtils.getInteger(dataMap, "orderStatus"));
		List<Map<String, Object>> tmpOrderList = dao.updateOrderStatus(orderVO);

		// groupby
		Map<String, List<Map<String, Object>>> dataList = tmpOrderList.stream().collect(Collectors
				.groupingBy(map -> MapUtils.getString(map, "ORDER_ID"), LinkedHashMap::new, Collectors.toList()));

		List<Map<String, Object>> orderList = new ArrayList<>();
		for (String key : dataList.keySet()) {
			// groupby 過後的資料
			List<Map<String, Object>> orderGroupList = dataList.get(key);
			Map<String, Object> parentMap = orderGroupList.get(0);

			Map orderGroupMap = new HashMap<>();
			orderGroupMap.put("RES_NAME", MapUtils.getString(parentMap, "RES_NAME"));
			orderGroupMap.put("ORDER_ID", MapUtils.getString(parentMap, "ORDER_ID"));
			int statusIndex = Integer.parseInt(MapUtils.getString(parentMap, "ORDER_STATUS"));
			orderGroupMap.put("ORDER_STATUS", OrderStatus.getOrderStatus(statusIndex));
			orderGroupMap.put("TOTAL", MapUtils.getString(parentMap, "TOTAL"));
			orderGroupMap.put("ORDER_CREATE", MapUtils.getString(parentMap, "ORDER_CREATE"));
			orderGroupMap.put("BZ_LOCATION", MapUtils.getString(parentMap, "BZ_LOCATION"));
			orderGroupMap.put("RES_ID", MapUtils.getString(parentMap, "RES_ID"));
			orderGroupMap.put("NOTE", MapUtils.getString(parentMap, "NOTE"));
			orderGroupMap.put("RES_ACCOUNT", MapUtils.getString(parentMap, "RES_ACCOUNT"));
			orderGroupMap.put("USER_ID", MapUtils.getString(parentMap, "USER_ID"));

			List<Map<String, Object>> productList = new ArrayList<>();
			for (Map<String, Object> orderInnerMap : orderGroupList) {
				Map<String, Object> productMap = new HashMap<>();
				productMap.put("PRODUCT_NAME", MapUtils.getString(orderInnerMap, "PRODUCT_NAME"));
				productMap.put("PRODUCT_PRICE", MapUtils.getString(orderInnerMap, "PRODUCT_PRICE"));
				productMap.put("AMOUNT", MapUtils.getString(orderInnerMap, "AMOUNT"));
				productList.add(productMap);
			}

			orderGroupMap.put("productList", productList);
			orderList.add(orderGroupMap);
		}

		return orderList;
	}

}
