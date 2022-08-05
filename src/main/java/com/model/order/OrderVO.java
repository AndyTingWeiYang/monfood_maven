package com.model.order;

import java.io.Serializable;
import java.sql.Timestamp;

import com.model.del.DelVO;
import com.model.orderdetail.OrderDetailVO;
import com.model.product.ProductVo;
import com.model.res.ResVO;


public class OrderVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private OrderDetailVO orderDetailVO;
	private ProductVo productVo;
	private DelVO delVO;
	private ResVO resVO;
	private Integer orderId;
	private Integer userId;
	private Integer resId;
	private Integer delId;
	private Integer orderStatus;
	private String note;
	private String userLocation;
	private Timestamp orderCreate;
	private Timestamp orderDone;
	private Integer productKcalTotal;
	private Integer total;
	private Integer delCost;
	private Boolean useCash;
	private String creditId;
	private Integer discount;
	private Boolean rating;
	private Double resRate;
	private Double delRate;
	private String resComment;
	private String delComment;
	private Integer promoteId;
	
	public OrderVO() {
	}

	public OrderVO(OrderDetailVO orderDetailVO, ProductVo productVo, DelVO delVO, ResVO resVO, Integer orderId,
			Integer userId, Integer resId, Integer delId, Integer orderStatus, String note, String userLocation,
			Timestamp orderCreate, Timestamp orderDone, Integer productKcalTotal, Integer total, Integer delCost,
			Boolean useCash, String creditId, Integer discount, Boolean rating, Double resRate, Double delRate,
			String resComment, String delComment, Integer promoteId) {
		this.orderDetailVO = orderDetailVO;
		this.productVo = productVo;
		this.delVO = delVO;
		this.resVO = resVO;
		this.orderId = orderId;
		this.userId = userId;
		this.resId = resId;
		this.delId = delId;
		this.orderStatus = orderStatus;
		this.note = note;
		this.userLocation = userLocation;
		this.orderCreate = orderCreate;
		this.orderDone = orderDone;
		this.productKcalTotal = productKcalTotal;
		this.total = total;
		this.delCost = delCost;
		this.useCash = useCash;
		this.creditId = creditId;
		this.discount = discount;
		this.rating = rating;
		this.resRate = resRate;
		this.delRate = delRate;
		this.resComment = resComment;
		this.delComment = delComment;
		this.promoteId = promoteId;
	}

	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getResId() {
		return resId;
	}
	public void setResId(Integer resId) {
		this.resId = resId;
	}
	public Integer getDelId() {
		return delId;
	}
	public void setDelId(Integer delId) {
		this.delId = delId;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getUserLocation() {
		return userLocation;
	}
	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}
	public Timestamp getOrderCreate() {
		return orderCreate;
	}
	public void setOrderCreate(Timestamp orderCreate) {
		this.orderCreate = orderCreate;
	}
	public Timestamp getOrderDone() {
		return orderDone;
	}
	public void setOrderDone(Timestamp orderDone) {
		this.orderDone = orderDone;
	}
	public Integer getProductKcalTotal() {
		return productKcalTotal;
	}
	public void setProductKcalTotal(Integer productKcalTotal) {
		this.productKcalTotal = productKcalTotal;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getDelCost() {
		return delCost;
	}
	public void setDelCost(Integer delCost) {
		this.delCost = delCost;
	}
	public Boolean getUseCash() {
		return useCash;
	}
	public void setUseCash(Boolean useCash) {
		this.useCash = useCash;
	}
	public String getCreditId() {
		return creditId;
	}
	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public Boolean getRating() {
		return rating;
	}
	public void setRating(Boolean rating) {
		this.rating = rating;
	}
	public Double getResRate() {
		return resRate;
	}
	public void setResRate(Double resRate) {
		this.resRate = resRate;
	}
	public Double getDelRate() {
		return delRate;
	}
	public void setDelRate(Double delRate) {
		this.delRate = delRate;
	}
	public String getResComment() {
		return resComment;
	}
	public void setResComment(String resComment) {
		this.resComment = resComment;
	}
	public String getDelComment() {
		return delComment;
	}
	public void setDelComment(String delComment) {
		this.delComment = delComment;
	}
	public Integer getPromoteId() {
		return promoteId;
	}
	public void setPromoteId(Integer promoteId) {
		this.promoteId = promoteId;
	}

	public OrderDetailVO getOrderDetailVO() {
		return orderDetailVO;
	}

	public void setOrderDetailVO(OrderDetailVO orderDetailVO) {
		this.orderDetailVO = orderDetailVO;
	}

	public ProductVo getProductVo() {
		return productVo;
	}

	public void setProductVo(ProductVo productVo) {
		this.productVo = productVo;
	}

	public DelVO getDelVO() {
		return delVO;
	}

	public void setDelVO(DelVO delVO) {
		this.delVO = delVO;
	}

	public ResVO getResVO() {
		return resVO;
	}

	public void setResVO(ResVO resVO) {
		this.resVO = resVO;
	}
	
}
