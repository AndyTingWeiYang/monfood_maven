package com.model.product.service;

import java.util.List;
import java.util.Map;

import com.model.product.ProductVo;

public interface ProductService {

	public ProductVo findByID(String productID);

	public List<ProductVo> findAll(Map<String, Object> dataMap);

	public ProductVo findPic(String productID);

	public boolean insert(Map<String, Object> dataMap);
	
	public boolean update(ProductVo product);
	
}
