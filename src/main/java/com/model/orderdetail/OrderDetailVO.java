package com.model.orderdetail;

import java.io.Serializable;

public class OrderDetailVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer productId;
	private Integer orderId;
	private Integer amount;
	private Integer orderedPrice;
	
	public OrderDetailVO() {
	}
	
	public OrderDetailVO(Integer productId, Integer orderId, Integer amount, Integer orderedPrice) {
		this.productId = productId;
		this.orderId = orderId;
		this.amount = amount;
		this.orderedPrice = orderedPrice;
	}
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getOrderedPrice() {
		return orderedPrice;
	}
	public void setOrderedPrice(Integer orderedPrice) {
		this.orderedPrice = orderedPrice;
	}
	
	
}