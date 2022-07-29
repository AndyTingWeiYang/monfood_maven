package com.model.res;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;

public class ResVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer resId;
	private Integer resCategory;
	private String resAccount;
	private java.sql.Timestamp updateTime;
	private String resName;
	private String resPassword;
	private String resTel;
	private byte[] resPic;
	private String ownerName;
	private String ownerTel;
	private String bzLocation;
	private Integer zipCode;
	private java.sql.Time bzOpenHours;
	private java.sql.Time bzCloseHours;
	private Integer bzWeekTime;

	public ResVO() {
		super();
	}

	public ResVO(Integer resId, Integer resCategory, String resAccount, Timestamp updateTime, String resName,
			String resPassword, String resTel, byte[] resPic, String ownerName, String ownerTel, String bzLocation,
			Integer zipCode, Time bzOpenHours, Time bzCloseHours, Integer bzWeekTime) {
		super();
		this.resId = resId;
		this.resCategory = resCategory;
		this.resAccount = resAccount;
		this.updateTime = updateTime;
		this.resName = resName;
		this.resPassword = resPassword;
		this.resTel = resTel;
		this.resPic = resPic;
		this.ownerName = ownerName;
		this.ownerTel = ownerTel;
		this.bzLocation = bzLocation;
		this.zipCode = zipCode;
		this.bzOpenHours = bzOpenHours;
		this.bzCloseHours = bzCloseHours;
		this.bzWeekTime = bzWeekTime;
	}

	@Override
	public String toString() {
		return "ResVO [resId=" + resId + ", resCategory=" + resCategory + ", resAccount=" + resAccount + ", updateTime="
				+ updateTime + ", resName=" + resName + ", resPassword=" + resPassword + ", resTel=" + resTel
				+ ", resPic=" + Arrays.toString(resPic) + ", ownerName=" + ownerName + ", ownerTel=" + ownerTel
				+ ", bzLocation=" + bzLocation + ", zipCode=" + zipCode + ", bzOpenHours=" + bzOpenHours
				+ ", bzCloseHours=" + bzCloseHours + ", bzWeekTime=" + bzWeekTime + "]";
	}

	public Integer getResId() {
		return resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}

	public Integer getResCategory() {
		return resCategory;
	}

	public void setResCategory(Integer resCategory) {
		this.resCategory = resCategory;
	}

	public String getResAccount() {
		return resAccount;
	}

	public void setResAccount(String resAccount) {
		this.resAccount = resAccount;
	}

	public java.sql.Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.sql.Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResPassword() {
		return resPassword;
	}

	public void setResPassword(String resPassword) {
		this.resPassword = resPassword;
	}

	public String getResTel() {
		return resTel;
	}

	public void setResTel(String resTel) {
		this.resTel = resTel;
	}

	public byte[] getResPic() {
		return resPic;
	}

	public void setResPic(byte[] resPic) {
		this.resPic = resPic;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerTel() {
		return ownerTel;
	}

	public void setOwnerTel(String ownerTel) {
		this.ownerTel = ownerTel;
	}

	public String getBzLocation() {
		return bzLocation;
	}

	public void setBzLocation(String bzLocation) {
		this.bzLocation = bzLocation;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public java.sql.Time getBzOpenHours() {
		return bzOpenHours;
	}

	public void setBzOpenHours(java.sql.Time bzOpenHours) {
		this.bzOpenHours = bzOpenHours;
	}

	public java.sql.Time getBzCloseHours() {
		return bzCloseHours;
	}

	public void setBzCloseHours(java.sql.Time bzCloseHours) {
		this.bzCloseHours = bzCloseHours;
	}

	public Integer getBzWeekTime() {
		return bzWeekTime;
	}

	public void setBzWeekTime(Integer bzWeekTime) {
		this.bzWeekTime = bzWeekTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
