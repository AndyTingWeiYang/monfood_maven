package com.model.product.dao;

import java.util.List;

import com.model.product.ProductVO;

public interface ProductDAO {
	// 查詢一筆資料
	public ProductVO findByID(String productID);
	// 查詢多筆資料
	public List<ProductVO> findAll(ProductVO productVO);

	public boolean insertResult(ProductVO product);

	public void update(ProductVO product);

	public void delete(ProductVO productID);

	public ProductVO findPic(String productId);

}
