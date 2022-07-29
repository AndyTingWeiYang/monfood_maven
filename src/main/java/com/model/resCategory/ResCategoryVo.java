package com.model.resCategory;

import java.io.Serializable;

public class ResCategoryVo implements Serializable {
	private Integer resCategoryID;
	private String resCategoryName;
	public Integer getResCategoryID() {
		return resCategoryID;
	}
	public void setResCategoryID(Integer resCategoryID) {
		this.resCategoryID = resCategoryID;
	}
	public String getResCategoryName() {
		return resCategoryName;
	}
	public void setResCategoryName(String resCategoryName) {
		this.resCategoryName = resCategoryName;
	}
	@Override
	public String toString() {
		return "resCategoryVo [resCategoryID=" + resCategoryID + ", resCategoryName=" + resCategoryName + "]";
	}
	

}
