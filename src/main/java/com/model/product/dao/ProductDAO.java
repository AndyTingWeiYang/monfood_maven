package com.model.product.dao;

import java.io.IOException;
import java.util.List;

import com.model.product.ProductVO;

public interface ProductDAO {

	public List<ProductVO> findAll();

	public void insert(ProductVO product) throws IOException;

	public void update(ProductVO product);

	public void delete(ProductVO productID);

}
