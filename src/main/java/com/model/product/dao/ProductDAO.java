package com.model.product.dao;

import java.io.IOException;
import java.util.List;

import com.model.product.ProductVO;

public interface ProductDAO {

	public List<ProductVO> findAll(ProductVO productVO);

	public void insert(ProductVO product) throws IOException;

	public boolean insertResult(ProductVO product);

	public void update(ProductVO product);

	public void delete(ProductVO productID);

	public ProductVO findPic(String productId);

}
