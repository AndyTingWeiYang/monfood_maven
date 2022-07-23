package com.model.creditcard;

import java.io.Serializable;
import java.sql.Date;

public class CreditcardVO implements Serializable {

	private Integer creditcardId;
	private Integer userId;
	private String cardNum;
	private String securityCode;
	private java.sql.Date exDate;
	private Integer defaultStatus;
		
	public CreditcardVO() {
	}

	public CreditcardVO(Integer creditcardId, Integer userId, String cardNum, String securityCode, Date exDate,
			Integer defaultStatus) {
		super();
		this.creditcardId = creditcardId;
		this.userId = userId;
		this.cardNum = cardNum;
		this.securityCode = securityCode;
		this.exDate = exDate;
		this.defaultStatus = defaultStatus;
	}

	public Integer getCreditcardId() {
		return creditcardId;
	}

	public void setCreditcardId(Integer creditcardId) {
		this.creditcardId = creditcardId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public java.sql.Date getExDate() {
		return exDate;
	}

	public void setExDate(java.sql.Date exDate) {
		this.exDate = exDate;
	}

	public Integer getDefaultStatus() {
		return defaultStatus;
	}

	public void setDefaultStatus(Integer defaultStatus) {
		this.defaultStatus = defaultStatus;
	}
	
	
}
