package com.model.product.service;

import java.util.List;
import java.util.Map;

import com.model.product.ProductVo;

public interface ProductService {

	Map<String, Object> findByID(String productID);

	List<ProductVo> findAll(Map<String, Object> dataMap);

	ProductVo findPic(String productID);

	boolean insert(Map<String, Object> dataMap);

	boolean update(ProductVo product);

	List<ProductVo> adminFindProductVoAll();

	Map<String, Object> findResInfo(Integer resID);

}
