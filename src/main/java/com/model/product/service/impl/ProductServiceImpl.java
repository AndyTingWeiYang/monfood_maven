package com.model.product.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.transform.TupleSubsetResultTransformer;

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
	public List<Map<String, Object>> findAll(Map<String, Object> dataMap) throws ErrorMsgException {
		ErrorMsgException eme = null;
		// 檢核特殊字元
		String productID = MapUtils.getString(dataMap, "productID");
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

		List<Map<String, Object>> productList = productDao.findAll(productVO);

		return productList;
	}

	@Override
	public ProductVo findPic(String productID) {
		ProductVo pic = productDao.findPic(productID);
		return pic;
	}

	@Override
	public boolean insert(Map<String, Object> dataMap) throws ErrorMsgException {
		ErrorMsgException eme = null;
		String productName = MapUtils.getString(dataMap, "productName");
		String productPriceStr = MapUtils.getString(dataMap, "productPrice");
		String productKcalStr = MapUtils.getString(dataMap, "productKcal");
		String stockStr = MapUtils.getString(dataMap, "stock");

		// 檢核是否空白
		if (StringUtils.isBlank(productName)) {
			eme = getErrorMsgException(eme, "productName", "請勿留取空白，請輸入內容");
		}
		if (StringUtils.isBlank(productPriceStr)) {
			eme = getErrorMsgException(eme, "productPrice", "請勿留取空白，請輸入內容");
		}

		if (StringUtils.isBlank(productKcalStr)) {
			eme = getErrorMsgException(eme, "productKcal", "請勿留取空白，請輸入內容");
		}

		if (StringUtils.isBlank(stockStr)) {
			eme = getErrorMsgException(eme, "stock", "請勿留取空白，請輸入內容");
		}

		// 檢核是否有特殊字元
		if (proccessCheckMsg(productName)) {
			eme = getErrorMsgException(eme, "productName", "商品名稱有特殊字元，請重新輸入");
		}

		if (proccessCheckMsg(productPriceStr)) {
			eme = getErrorMsgException(eme, "productPrice", "商品價格有特殊字元，請重新輸入");
		}
		if (proccessCheckMsg(productKcalStr)) {
			eme = getErrorMsgException(eme, "productKcal", "熱量有特殊字元，請重新輸入");
		}
		if (proccessCheckMsg(stockStr)) {
			eme = getErrorMsgException(eme, "stock", "庫存有特殊字元，請重新輸入");
		}

		// 拋出錯誤例外
		if (eme != null) {
			throw eme;
		}

		// 將 Map 物件變成 JSON 字串做序列化
//		String dataJsonStr = gson.toJson(dataMap);

		// 用 gson 將拿到的 JSON 字串轉 VO
//		ProductVo productVO = gson.fromJson(dataJsonStr, ProductVo.class);

		// 將結果用 dao 回傳給 service
		boolean result = productDao.insert(dataMap);

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

	private ErrorMsgException getErrorMsgException(ErrorMsgException eme, String msgKey, String msgValue) {
		if (eme == null) {
			eme = new ErrorMsgException();
		}

		eme.appendMessage(msgKey, msgValue);
		return eme;
	}

}
