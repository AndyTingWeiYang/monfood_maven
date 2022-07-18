package com.model.adminLocation;

import java.io.Serializable;

public class AdminLocationVO implements Serializable {
	private Integer zipCode;
	private String adminRegion;
	
	@Override
	public String toString() {
		return "AdminLocationVO [zipCode=" + zipCode + ", adminRegion=" + adminRegion + "]";
	}
	public AdminLocationVO() {
		super();
	}
	public AdminLocationVO(Integer zipCode, String adminRegion) {
		super();
		this.zipCode = zipCode;
		this.adminRegion = adminRegion;
	}
	public Integer getZipCode() {
		return zipCode;
	}
	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
	public String getAdminRegion() {
		return adminRegion;
	}
	public void setAdminRegion(String adminRegion) {
		this.adminRegion = adminRegion;
	}
	
	
	
}
