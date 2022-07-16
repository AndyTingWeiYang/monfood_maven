package com.model.user;

import java.io.Serializable;

public class UserVO implements Serializable{
private static final long serialVersionUID = 1L;
	
	private String userName;
	private String userAccount;
	private String userPassword;
	private String userTel;
	private String userProfile; 
	private java.sql.Date birthday;
	private Integer calories;
	private Integer budget;
	private byte[] profilePic;
	private Integer monsLevel;
	private String monsName;
	private java.sql.Timestamp updateTime;
	
	public UserVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}

	public java.sql.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.sql.Date birthday) {
		this.birthday = birthday;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public Integer getBudget() {
		return budget;
	}

	public void setBudget(Integer budget) {
		this.budget = budget;
	}

	public byte[] getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}

	public Integer getMonsLevel() {
		return monsLevel;
	}

	public void setMonsLevel(Integer monsLevel) {
		this.monsLevel = monsLevel;
	}

	public String getMonsName() {
		return monsName;
	}

	public void setMonsName(String monsName) {
		this.monsName = monsName;
	}

	public java.sql.Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.sql.Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}
