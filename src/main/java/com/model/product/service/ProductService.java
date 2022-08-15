package com.model.product.service;

import java.util.List;
import java.util.Map;

import com.model.product.ProductVo;
import com.model.product.util.ErrorMsgException;

public interface ProductService {

	Map<String, Object> findByID(String productID);

	List<Map<String, Object>> findAll(Map<String, Object> dataMap) throws ErrorMsgException;

	ProductVo findPic(String productID);

	boolean insert(Map<String, Object> dataMap)throws ErrorMsgException;

	boolean update(ProductVo product);

	List<ProductVo> adminFindProductVoAll();

	Map<String, Object> findResInfo(Integer resID);

	List<Map<String, Object>> getAllPdt(Integer resId);
}
