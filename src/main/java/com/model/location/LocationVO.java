package com.model.location;

import java.io.Serializable;

public class LocationVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer locationId;
	private Integer userId;
	private Integer zipCode;
	private String location;
	private Integer defaultStatus;
		
	public LocationVO() {
	}

	public LocationVO(Integer locationId, Integer userId, Integer zipCode, String location, Integer defaultStatus) {
		this.locationId = locationId;
		this.userId = userId;
		this.zipCode = zipCode;
		this.location = location;
		this.defaultStatus = defaultStatus;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getDefaultStatus() {
		return defaultStatus;
	}

	public void setDefaultStatus(Integer defaultStatus) {
		this.defaultStatus = defaultStatus;
	}
	 
	
}
