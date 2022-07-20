package com.model.res;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;

public class ResVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer resId;
	private Integer resCategory;
	private String resAccount;
	private String accountName;
	private String bankCode;
	private String bankAccount;
	private String bankName;
	private java.sql.Timestamp updateTime;
	private String resName;
	private String resPassword;
	private String resTel;
	private String resMail;
	private byte[] bzLicence;
	private byte[] resPic;
	private String ownerName;
	private String ownerTel;
	private byte[] ownerId;
	private String bzLocation;
	private Integer zipCode;
	private java.sql.Time bzOpenHours;
	private java.sql.Time bzCloseHours;
	private Integer bzWeekTime;
	private Integer resStatus;
	
	public ResVO() {
		super();
	}

	public ResVO(Integer resId, Integer resCategory, String resAccount, String accountName, String bankCode,
			String bankAccount, String bankName, Timestamp updateTime, String resName, String resPassword,
			String resTel, String resMail, byte[] bzLicence, byte[] resPic, String ownerName, String ownerTel,
			byte[] ownerId, String bzLocation, Integer zipCode, Time bzOpenHours, Time bzCloseHours, Integer bzWeekTime,
			Integer resStatus) {
		super();
		this.resId = resId;
		this.resCategory = resCategory;
		this.resAccount = resAccount;
		this.accountName = accountName;
		this.bankCode = bankCode;
		this.bankAccount = bankAccount;
		this.bankName = bankName;
		this.updateTime = updateTime;
		this.resName = resName;
		this.resPassword = resPassword;
		this.resTel = resTel;
		this.resMail = resMail;
		this.bzLicence = bzLicence;
		this.resPic = resPic;
		this.ownerName = ownerName;
		this.ownerTel = ownerTel;
		this.ownerId = ownerId;
		this.bzLocation = bzLocation;
		this.zipCode = zipCode;
		this.bzOpenHours = bzOpenHours;
		this.bzCloseHours = bzCloseHours;
		this.bzWeekTime = bzWeekTime;
		this.resStatus = resStatus;
	}

	
	
	
	@Override
	public String toString() {
		return "ResVO [resId=" + resId + ", resCategory=" + resCategory + ", resAccount=" + resAccount
				+ ", accountName=" + accountName + ", bankCode=" + bankCode + ", bankAccount=" + bankAccount
				+ ", bankName=" + bankName + ", updateTime=" + updateTime + ", resName=" + resName + ", resPassword="
				+ resPassword + ", resTel=" + resTel + ", resMail=" + resMail + ", bzLicence="
				+ Arrays.toString(bzLicence) + ", resPic=" + Arrays.toString(resPic) + ", ownerName=" + ownerName
				+ ", ownerTel=" + ownerTel + ", ownerId=" + Arrays.toString(ownerId) + ", bzLocation=" + bzLocation
				+ ", zipCode=" + zipCode + ", bzOpenHours=" + bzOpenHours + ", bzCloseHours=" + bzCloseHours
				+ ", bzWeekTime=" + bzWeekTime + ", resStatus=" + resStatus + "]";
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

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
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

	public String getResMail() {
		return resMail;
	}

	public void setResMail(String resMail) {
		this.resMail = resMail;
	}

	public byte[] getBzLicence() {
		return bzLicence;
	}

	public void setBzLicence(byte[] bzLicence) {
		this.bzLicence = bzLicence;
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

	public byte[] getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(byte[] ownerId) {
		this.ownerId = ownerId;
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

	public Time getBzOpenHours() {
		return bzOpenHours;
	}

	public void setBzOpenHours(Time bzOpenHours) {
		this.bzOpenHours = bzOpenHours;
	}

	public Time getBzCloseHours() {
		return bzCloseHours;
	}

	public void setBzCloseHours(Time bzCloseHours) {
		this.bzCloseHours = bzCloseHours;
	}

	public Integer getBzWeekTime() {
		return bzWeekTime;
	}

	public void setBzWeekTime(Integer bzWeekTime) {
		this.bzWeekTime = bzWeekTime;
	}

	public Integer getResStatus() {
		return resStatus;
	}

	public void setResStatus(Integer resStatus) {
		this.resStatus = resStatus;
	}
	
	

}
