package com.model.product;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

public class ProductVO implements Serializable {

	private Integer productID;
	private Integer resID;
	private Integer productStatus;
	private Integer productPrice;
	private Integer productKcal;
	private String productName;
	private java.sql.Timestamp updateTime;
	private byte[] productPic;

	public ProductVO() {

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

	@Override
	public String toString() {
		return "ProductVO [productID=" + productID + ", resID=" + resID + ", productStatus=" + productStatus
				+ ", productPrice=" + productPrice + ", productKcal=" + productKcal + ", productName=" + productName
				+ ", updateTime=" + updateTime + "]";
	}

}
