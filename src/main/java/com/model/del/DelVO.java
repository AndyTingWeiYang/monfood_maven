package com.model.del;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.Arrays;

public class DelVO implements Serializable {
	private Integer delID;
	private String delName;
	private String delAccount;
	private String delPassword;
	private String delTel;
	private Date delBirthday;
	private String platenumber;
	private Integer status;
	private java.sql.Timestamp updateTime;
	private byte[] delIDPhoto;
	private byte[] carLicense;
	private byte[] driverLicense;
	private byte[] criminalRecord;
	private byte[] insurance;
	private String delAccountName;
	private String delBankname;
	private String delBankcode;
	private String delBankaccount;

	public DelVO() {
		super();
	}

//	public DelVO(Integer delID, String delName, String delAccount, String delPassword, String delTel, Date delBirthday,
//			String platenumber, Integer status, Timestamp updateTime, byte[] delIDPhoto, byte[] carLicense,
//			byte[] driverLicense, byte[] criminalRecord, byte[] insurance, String delAccountName, String delBankname,
//			String delBankcode, String delBankaccount) {
//		super();
//		this.delID = delID;
//		this.delName = delName;
//		this.delAccount = delAccount;
//		this.delPassword = delPassword;
//		this.delTel = delTel;
//		this.delBirthday = delBirthday;
//		this.platenumber = platenumber;
//		this.status = status;
//		this.updateTime = updateTime;
//		this.delIDPhoto = delIDPhoto;
//		this.carLicense = carLicense;
//		this.driverLicense = driverLicense;
//		this.criminalRecord = criminalRecord;
//		this.insurance = insurance;
//		this.delAccountName = delAccountName;
//		this.delBankname = delBankname;
//		this.delBankcode = delBankcode;
//		this.delBankaccount = delBankaccount;
//	}

	@Override
	public String toString() {
		return "DelVO [delID=" + delID + ", delName=" + delName + ", delAccount=" + delAccount + ", delPassword="
				+ delPassword + ", delTel=" + delTel + ", delBirthday=" + delBirthday + ", platenumber=" + platenumber
				+ ", status=" + status + ", updateTime=" + updateTime + ", delIDPhoto=" + Arrays.toString(delIDPhoto)
				+ ", carLicense=" + Arrays.toString(carLicense) + ", driverLicense=" + Arrays.toString(driverLicense)
				+ ", criminalRecord=" + Arrays.toString(criminalRecord) + ", insurance=" + Arrays.toString(insurance)
				+ ", delAccountName=" + delAccountName + ", delBankname=" + delBankname + ", delBankcode=" + delBankcode
				+ ", delBankaccount=" + delBankaccount + "]";
	}

	public Integer getDelID() {
		return delID;
	}

	public void setDelID(Integer delID) {
		this.delID = delID;
	}

	public String getDelName() {
		return delName;
	}

	public void setDelName(String delName) {
		this.delName = delName;
	}

	public String getDelAccount() {
		return delAccount;
	}

	public void setDelAccount(String delAccount) {
		this.delAccount = delAccount;
	}

	public String getDelPassword() {
		return delPassword;
	}

	public void setDelPassword(String delPassword) {
		this.delPassword = delPassword;
	}

	public String getDelTel() {
		return delTel;
	}

	public void setDelTel(String delTel) {
		this.delTel = delTel;
	}

	public Date getDelBirthday() {
		return delBirthday;
	}

	public void setDelBirthday(Date delBirthday) {
		this.delBirthday = delBirthday;
	}

	public String getPlatenumber() {
		return platenumber;
	}

	public void setPlatenumber(String platenumber) {
		this.platenumber = platenumber;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public java.sql.Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public byte[] getDelIDPhoto() {
		return delIDPhoto;
	}

	public void setDelIDPhoto(byte[] delIDPhoto) {
		this.delIDPhoto = delIDPhoto;
	}

	public byte[] getCarLicense() {
		return carLicense;
	}

	public void setCarLicense(byte[] carLicense) {
		this.carLicense = carLicense;
	}

	public byte[] getDriverLicense() {
		return driverLicense;
	}

	public void setDriverLicense(byte[] driverLicense) {
		this.driverLicense = driverLicense;
	}

	public byte[] getCriminalRecord() {
		return criminalRecord;
	}

	public void setCriminalRecord(byte[] criminalRecord) {
		this.criminalRecord = criminalRecord;
	}

	public byte[] getInsurance() {
		return insurance;
	}

	public void setInsurance(byte[] insurance) {
		this.insurance = insurance;
	}

	public String getDelAccountName() {
		return delAccountName;
	}

	public void setDelAccountName(String delAccountName) {
		this.delAccountName = delAccountName;
	}

	public String getDelBankname() {
		return delBankname;
	}

	public void setDelBankname(String delBankname) {
		this.delBankname = delBankname;
	}

	public String getDelBankcode() {
		return delBankcode;
	}

	public void setDelBankcode(String delBankcode) {
		this.delBankcode = delBankcode;
	}

	public String getDelBankaccount() {
		return delBankaccount;
	}

	public void setDelBankaccount(String delBankaccount) {
		this.delBankaccount = delBankaccount;
	}

}
