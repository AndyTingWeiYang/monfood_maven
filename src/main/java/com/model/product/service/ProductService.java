package com.model.product.service;

import java.util.List;
import java.util.Map;

import com.model.product.ProductVO;

public interface ProductService {

	public ProductVO findByID(String productID);

	public List<ProductVO> findAll(Map<String, Object> dataMap);

	public ProductVO findPic(String productID);

	public boolean insertResult(Map<String, Object> dataMap);
}
