package com.model.product.service;

import java.io.IOException;
import java.util.Map;

import com.model.product.ProductVO;

public interface NewProductService {
	public ProductVO insert(Map<String, String> dataMap) throws IOException;

}
