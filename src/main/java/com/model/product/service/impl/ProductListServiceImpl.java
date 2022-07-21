package com.model.product.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.model.product.ProductVO;
import com.model.product.dao.ProductDAO;
import com.model.product.dao.impl.ProductDAOImpl;
import com.model.product.service.ProductListService;

public class ProductListServiceImpl implements ProductListService {

	@Override
	public List<ProductVO> findAll(Map<String, String> dataMap) {
		// TODO: 檢核錯誤目前回傳 null 後續改成 throw ErrorInputException
		String productId = MapUtils.getString(dataMap, "productId");
		if (proccessCheckMsg(productId)) {
			return null;
		}

		String resCategory = MapUtils.getString(dataMap, "resCategory");
		if (proccessCheckMsg(resCategory)) {
			return null;
		}

		String productName = MapUtils.getString(dataMap, "productName");
		if (proccessCheckMsg(productName)) {
			return null;
		}

		String minPrice = MapUtils.getString(dataMap, "minPrice");
		if (proccessCheckMsg(minPrice)) {
			return null;
		}
		String maxPrice = MapUtils.getString(dataMap, "maxPrice");
		if (proccessCheckMsg(maxPrice)) {
			return null;
		}

		if (Integer.parseInt(maxPrice) < Integer.parseInt(minPrice)) {
			return null;
		}

		ProductDAO productDao=new ProductDAOImpl();
		List<ProductVO> productList = productDao.findAll();
		
		return productList;
	}

	public boolean proccessCheckMsg(String msg) {
		if (StringUtils.isNoneBlank(msg)) {
			String[] msgSp = msg.split("");
			String[] flag = new String[] { "!", "@", "#", "$", "%", "^", "&" };

			for (int i = 0; i < msgSp.length; i++) {
				for (int j = 0; j < flag.length; j++) {
					if (msgSp[i].equals(flag)) {
						System.out.println("有特殊字元，請重新輸入");
						return true;
					}
				}
			}
		}
		return false;
	}

}
