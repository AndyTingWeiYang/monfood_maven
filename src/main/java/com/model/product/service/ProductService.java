package com.model.product.service;

import java.util.List;
import java.util.Map;

import com.model.product.ProductVO;

public interface ProductService {

	public ProductVO findByID(String productID);

	public List<ProductVO> findAll(Map<String, Object> dataMap);

	public ProductVO findPic(String productID);

	public boolean insert(Map<String, Object> dataMap);
	
	public boolean update(ProductVO product);
	
}
