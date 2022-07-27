package com.model.product.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.product.ProductVO;
import com.model.product.dao.ProductDAO;
import com.model.product.dao.impl.ProductDAOImpl;
import com.model.product.service.ProductService;
import com.model.product.util.IntTypeAdapter;

public class ProductServiceImpl implements ProductService {

	private ProductDAO productDao;
	private Gson gson;

	public ProductServiceImpl() {
		this.productDao = new ProductDAOImpl();
		// Gson NumberException handle
		this.gson = new GsonBuilder().registerTypeAdapter(int.class, new IntTypeAdapter())
				.registerTypeAdapter(Integer.class, new IntTypeAdapter()).create();
	}

	@Override
	public List<ProductVO> findAll(Map<String, Object> dataMap) {
		// TODO: 檢核錯誤目前回傳 null 後續改成 throw ErrorInputException
		String productId = MapUtils.getString(dataMap, "productId");
		if (proccessCheckMsg(productId)) {
			return null;
		}

		String resCategory = MapUtils.getString(dataMap, "resCategory");
		if (proccessCheckMsg(resCategory)) {
			return null;
		}

		String productName = MapUtils.getString(dataMap, "productName");
		if (proccessCheckMsg(productName)) {
			return null;
		}

		String minPrice = MapUtils.getString(dataMap, "minPrice");
		if (proccessCheckMsg(minPrice)) {
			return null;
		}
		String maxPrice = MapUtils.getString(dataMap, "maxPrice");
		if (proccessCheckMsg(maxPrice)) {
			return null;
		}
		// 最大金額不得小於最小金額
		if (StringUtils.isNotBlank(minPrice) && StringUtils.isNotBlank(maxPrice)) {
			if (Integer.parseInt(maxPrice) < Integer.parseInt(minPrice)) {
				return null;
			}
		}

		// 將 Map 物件變成 JSON 字串做序列化
		String dataJsonStr = gson.toJson(dataMap);

		// 用 gson 將拿到的 JSON 字串轉 VO
		ProductVO productVO = gson.fromJson(dataJsonStr, ProductVO.class);

		List<ProductVO> productList = productDao.findAll(productVO);

		return productList;
	}

	public boolean proccessCheckMsg(String msg) {
		if (StringUtils.isNoneBlank(msg)) {
			String[] msgSp = msg.split("");
			String[] flag = new String[] { "!", "@", "#", "$", "%", "^", "&" };

			for (int i = 0; i < msgSp.length; i++) {
				for (int j = 0; j < flag.length; j++) {
					if (msgSp[i].equals(flag)) {
						System.out.println("有特殊字元，請重新輸入");
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public ProductVO findPic(String productId) {
		ProductVO pic = productDao.findPic(productId);
		return pic;
	}

	@Override
	public boolean insertResult(Map<String, Object> dataMap) {
		// 將 Map 物件變成 JSON 字串做序列化
		String dataJsonStr = gson.toJson(dataMap);

		// 用 gson 將拿到的 JSON 字串轉 VO
		ProductVO productVO = gson.fromJson(dataJsonStr, ProductVO.class);
		System.out.println(productVO);
		boolean result = productDao.insertResult(productVO);

		return result;
	}

	@Override
	public ProductVO findByID(String productID) {
		return productDao.findByID(productID);
	}
	
}
