package com.model.product.dao;

import java.util.List;
import java.util.Map;

import com.model.product.ProductVo;
import com.model.res.ResVO;

public interface ProductDao {
	// 查詢一筆資料
	public ProductVo findByID(String productID);

	// 查詢多筆資料
	public List<ProductVo> findAll(ProductVo productVO);

	public boolean insert(ProductVo product);

	public boolean update(ProductVo product);

	public boolean updateDynanicPic(ProductVo product);

	public void delete(ProductVo productID);

	public ProductVo findPic(String productId);

	public List<ProductVo> findAll();

	List<ProductVo> getAll();

	Map<String, Object> findResInfo(Integer resID);

}
