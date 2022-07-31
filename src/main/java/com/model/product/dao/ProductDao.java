package com.model.product.dao;

import java.util.List;


import com.model.product.ProductVo;

public interface ProductDao {
	// 查詢一筆資料
	public ProductVo findByID(String productID);
	// 查詢多筆資料
	public List<ProductVo> findAll(ProductVo productVO);

	public boolean insert(ProductVo product);

	public boolean  update(ProductVo product);
	
	public boolean updateDynanicPic(ProductVo product);

	public void delete(ProductVo productID);

	public ProductVo findPic(String productId);
	
	List<ProductVo> getAll();

}
