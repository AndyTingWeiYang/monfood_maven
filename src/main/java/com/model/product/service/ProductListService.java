package com.model.product.service;

import java.util.List;
import java.util.Map;

import com.model.product.ProductVO;

public interface ProductListService {

	public List<ProductVO> findAll(Map<String, String> dataMap);
}
