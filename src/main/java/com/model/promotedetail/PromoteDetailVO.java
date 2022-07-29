package com.model.promotedetail;

import java.io.Serializable;

public class PromoteDetailVO implements Serializable{

	private Integer promoteId;
	private Integer userId;
	private Integer usedStatus;
	private String promoteCode;
	
	public PromoteDetailVO() {
	}

	public PromoteDetailVO(Integer promoteId, Integer userId, Integer usedStatus, String promoteCode) {
		this.promoteId = promoteId;
		this.userId = userId;
		this.usedStatus = usedStatus;
		this.promoteCode = promoteCode;
	}

	public Integer getPromoteId() {
		return promoteId;
	}

	public void setPromoteId(Integer promoteId) {
		this.promoteId = promoteId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUsedStatus() {
		return usedStatus;
	}

	public void setUsedStatus(Integer usedStatus) {
		this.usedStatus = usedStatus;
	}
	
	public String getPromoteCode() {
		return promoteCode;
	}
	
	public void setPromoteCode(String promoteCode) {
		this.promoteCode = promoteCode;
	}
}
