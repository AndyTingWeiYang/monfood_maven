package com.model.product;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

public class ProductVo implements Serializable {

	private Integer productID;
	private Integer resID;
	private Integer productStatus;
	private Integer productPrice;
	private Integer productKcal;
	private String productName;
	private Integer stock;
	private java.sql.Timestamp updateTime;
	private Integer minPrice;
	private Integer maxPrice;
	private byte[] productPic;

	public ProductVo() {

	}

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public Integer getResID() {
		return resID;
	}

	public void setResID(Integer resID) {
		this.resID = resID;
	}

	public Integer getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPice) {
		this.productPrice = productPice;
	}

	public Integer getProductKcal() {
		return productKcal;
	}

	public void setProductKcal(Integer productKcal) {
		this.productKcal = productKcal;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public java.sql.Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public byte[] getProductPic() {
		return productPic;
	}

	public void setProductPic(byte[] productPic) {
		this.productPic = productPic;
	}

	public Integer getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}

	public Integer getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}

	@Override
	public String toString() {
		return "ProductVO [productID=" + productID + ", resID=" + resID + ", productStatus=" + productStatus
				+ ", productPrice=" + productPrice + ", productKcal=" + productKcal + ", productName=" + productName
				+ ", stock=" + stock + ", updateTime=" + updateTime + ", minPrice=" + minPrice + ", maxPrice="
				+ maxPrice + ", productPic=" + Arrays.toString(productPic) + "]";
	}

	

}
