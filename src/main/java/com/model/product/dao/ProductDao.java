package com.model.product.dao;

import java.util.List;
import java.util.Map;

import com.model.product.ProductVo;

public interface ProductDao {
	// 查詢一筆資料
	public Map<String, Object> findByID(String productID);

	// 查詢多筆資料
	public List<Map<String, Object>> findAll(ProductVo productVO);

	public boolean insert(Map<String, Object> dataMap);

	public boolean update(ProductVo product);

	public boolean updateDynanicPic(ProductVo product);

	public void delete(ProductVo productID);

	public ProductVo findPic(String productId);

	public List<ProductVo> findAll();

	List<ProductVo> getAll();

	Map<String, Object> findResInfo(Integer resID);
	
	List<Map<String, Object>> getAllPdt(Integer resId);

	boolean updateStock(ProductVo productVo);

	boolean updateStatus(ProductVo productVo);

}
