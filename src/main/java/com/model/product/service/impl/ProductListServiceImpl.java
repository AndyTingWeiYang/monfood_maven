package com.model.product.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.model.product.ProductVO;
import com.model.product.service.ProductListService;

public class ProductListServiceImpl implements ProductListService {

	@Override
	public List<ProductVO> findAll(Map<String, String> dataMap) {
		String productId = MapUtils.getString(dataMap, "productId");
		if (StringUtils.isNotBlank(productId)) {
			String[] productIdSp = productId.split("");
			String[] flag = new String[] { "!", "@", "#", "$", "%", "^", "&" };

			for (int i = 0; i < productIdSp.length; i++) {
				for (int j = 0; j < flag.length; j++) {
					if(productIdSp.equals(flag)) {
						System.out.println("有特殊符號");
					}
				}
			}

		}

		return null;
	}

}
