package com.model.administrator;

import java.io.Serializable;

public class AdministratorVO implements Serializable {
	private Integer adminID;
	private String adminPassword;
	private Integer permission;
	
	public AdministratorVO() {
		
	}

	public Integer getAdminID() {
		return adminID;
	}

	public void setAdminID(Integer adminID) {
		this.adminID = adminID;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public Integer getPermission() {
		return permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "AdministratorVO [adminID=" + adminID + ", adminPassword=" + adminPassword + ", permission=" + permission + "]";
	}
	

}
