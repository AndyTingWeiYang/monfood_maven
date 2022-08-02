package com.model.cart;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Base64;

public class CartVO implements Serializable{
	
	private Integer resId;
	private String resLocation;
	private String resName;
	private Integer productId;
	private String productName;
	private Integer amount;
	private Integer productPrice;
	private Integer productKcal;
	private byte[] productPic;
	
	public CartVO() {
		super();
	}

	public CartVO(Integer resId, String resLocation, String resName, Integer productId, String productName,
			Integer amount, Integer productPrice, Integer productKcal, byte[] productPic) {
		super();
		this.resId = resId;
		this.resLocation = resLocation;
		this.resName = resName;
		this.productId = productId;
		this.productName = productName;
		this.amount = amount;
		this.productPrice = productPrice;
		this.productKcal = productKcal;
		this.productPic = productPic;
	}

	public Integer getResId() {
		return resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}

	public String getResLocation() {
		return resLocation;
	}

	public void setResLocation(String resLocation) {
		this.resLocation = resLocation;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getProductKcal() {
		return productKcal;
	}

	public void setProductKcal(Integer productKcal) {
		this.productKcal = productKcal;
	}

	public byte[] getProductPic() {
		return productPic;
	}

	public void setProductPic(byte[] productPic) {
		this.productPic = productPic;
	}

	@Override
	public String toString() {
		return "CartVO [resId=" + resId + ", resLocation=" + resLocation + ", resName=" + resName + ", productId="
				+ productId + ", productName=" + productName + ", amount=" + amount + ", productPrice=" + productPrice
				+ ", productKcal=" + productKcal + ", productPic=" + productPic + "]";
	}

	
}