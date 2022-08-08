package com.model.product.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.product.ProductVo;
import com.model.product.dao.ProductDao;
import com.model.product.dao.impl.ProductDAOImpl;
import com.model.product.service.ProductService;
import com.model.product.util.ErrorMsgException;
import com.model.product.util.IntTypeAdapter;

public class ProductServiceImpl implements ProductService {

	private ProductDao productDao;
	private Gson gson;

	public ProductServiceImpl() {
		this.productDao = new ProductDAOImpl();
		// Gson NumberException handle
		this.gson = new GsonBuilder().registerTypeAdapter(int.class, new IntTypeAdapter())
				.registerTypeAdapter(Integer.class, new IntTypeAdapter()).create();
	}

	@Override
	public List<ProductVo> findAll(Map<String, Object> dataMap) throws ErrorMsgException {
		String productID = MapUtils.getString(dataMap, "productID");

		ErrorMsgException eme = null;
		if (proccessCheckMsg(productID)) {
			eme = getErrorMsgException(eme, "productIdError", "商品編號有特殊字元，請重新輸入");
		}

		String productName = MapUtils.getString(dataMap, "productName");
		if (proccessCheckMsg(productName)) {
			eme = getErrorMsgException(eme, "productNameError", "商品名稱有特殊字元，請重新輸入");
		}

		String minPrice = MapUtils.getString(dataMap, "minPrice");
		if (proccessCheckMsg(minPrice)) {
			eme = getErrorMsgException(eme, "minPriceError", "商品最小價格有特殊字元，請重新輸入");
		}
		String maxPrice = MapUtils.getString(dataMap, "maxPrice");
		if (proccessCheckMsg(maxPrice)) {
			eme = getErrorMsgException(eme, "maxPriceError", "商品最高價格有特殊字元，請重新輸入");
		}
		// 最大金額不得小於最小金額
		if (StringUtils.isNotBlank(minPrice) && StringUtils.isNotBlank(maxPrice)) {
			if (Integer.parseInt(maxPrice) < Integer.parseInt(minPrice)) {
				eme = getErrorMsgException(eme, "rangeError", "最高金額不可小於最小金額，請重新輸入");
			}
		}
		// 拋出錯誤例外
		if (eme != null) {
			throw eme;
		}

		// 將 Map 物件變成 JSON 字串做序列化
		String dataJsonStr = gson.toJson(dataMap);

		// 用 gson 將拿到的 JSON 字串轉 VO
		ProductVo productVO = gson.fromJson(dataJsonStr, ProductVo.class);

		List<ProductVo> productList = productDao.findAll(productVO);

		return productList;
	}

	public boolean proccessCheckMsg(String msg) {
		if (StringUtils.isNoneBlank(msg)) {
			String[] msgSp = msg.split("");
			String[] flag = new String[] { "!", "@", "#", "$", "%", "^", "&" };

			for (int i = 0; i < msgSp.length; i++) {
				for (int j = 0; j < flag.length; j++) {
					if (msgSp[i].equals(flag[j])) {
						System.out.println("有特殊字元，請重新輸入");
						return true;
					}
				}
			}
		}
		return false;
	}

	public void checkResp() {
		// 檢核手機號碼
		String checkPhone = "";

	}

	@Override
	public ProductVo findPic(String productID) {
		ProductVo pic = productDao.findPic(productID);
		return pic;
	}

	@Override
	public boolean insert(Map<String, Object> dataMap) {
		// 將 Map 物件變成 JSON 字串做序列化
		String dataJsonStr = gson.toJson(dataMap);

		// 用 gson 將拿到的 JSON 字串轉 VO
		ProductVo productVO = gson.fromJson(dataJsonStr, ProductVo.class);
		System.out.println(productVO);
		// 將結果用 dao 回傳給 service
		boolean result = productDao.insert(productVO);

		return result;
	}

	@Override
	public Map<String, Object> findByID(String productID) {
		return productDao.findByID(productID);
	}

	@Override
	public boolean update(ProductVo product) {

		return productDao.updateDynanicPic(product);
	}

	@Override
	public List<ProductVo> adminFindProductVoAll() {
		List<ProductVo> list = productDao.getAll();
		return list;
	}

	@Override
	public Map<String, Object> findResInfo(Integer resID) {

		return productDao.findResInfo(resID);
	}

	@Override
	public List<Map<String, Object>> getAllPdt(Integer resId) {
		List<Map<String, Object>> list = productDao.getAllPdt(resId);
		return list;
	}

	private ErrorMsgException getErrorMsgException(ErrorMsgException eme, String msgKey, String msgValue) {
		if (eme == null) {
			eme = new ErrorMsgException();
		}

		eme.appendMessage(msgKey, msgValue);
		return eme;
	}

}
