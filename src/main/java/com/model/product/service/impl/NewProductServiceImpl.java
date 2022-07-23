package com.model.product.service.impl;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;

import com.model.product.ProductVO;
import com.model.product.dao.ProductDAO;
import com.model.product.dao.impl.ProductDAOImpl;
import com.model.product.service.NewProductService;

public class NewProductServiceImpl implements NewProductService {
	private ProductDAO productDAO;

	public NewProductServiceImpl() {
		productDAO = new ProductDAOImpl();
	}

	@Override
	public ProductVO insert(Map<String, String> dataMap) throws IOException {
		// 將產品新增
		ProductVO product = new ProductVO();

//		product.setProductName(productName);
//		product.setProductPrice(productPrice);
//		product.setProductKcal(productKcal);
//		product.setProductStatus(productStatus);
//		product.setProductPic(productPic);

//		productDAO.insert(product);

		return product;
	}

}
