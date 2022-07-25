package com.model.product.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.model.product.ProductVO;
import com.model.product.dao.ProductDAO;
import com.model.product.dao.impl.ProductDAOImpl;
import com.model.product.service.ProductHistoryService;

public class ProductHistoryServiceImpl implements ProductHistoryService {

	@Override
	public List<ProductVO> findAll(Map<String, String> dataMap) {
		String orderId = MapUtils.getString(dataMap, "orderId"); // dataMap.get("orderId");
		// 檢核有錯誤 回傳空集合
		if (proccessCheckMsg(orderId)) {
			return new ArrayList<ProductVO>();
		}

		String userId = MapUtils.getString(dataMap, "userId");
		if (proccessCheckMsg(userId)) {
			return new ArrayList<ProductVO>();
		}

		// 檢核無誤 呼叫 DAO
		ProductDAO prodctDao = new ProductDAOImpl();
		List<ProductVO> prodcutList = prodctDao.findAll(new ProductVO());
		return prodcutList;
	}

	private boolean proccessCheckMsg(String msg) {
		if (StringUtils.isNotBlank(msg)) {
			String[] msgSp = msg.split("");
			String[] flag = new String[] { "!", "@", "#", "$", "%", "^", "&" };

			// 不得輸入特殊符號，將輸入框的字元切開
			for (int i = 0; i < msgSp.length; i++) {
				// 一個字一個字去比對
				for (int j = 0; j < flag.length; j++) {
					if (msgSp[i].equals(flag)) {
						System.out.println("不可以有特殊符號!");
						return true;
					}
				}
			}
		}
		return false;
	}

}
