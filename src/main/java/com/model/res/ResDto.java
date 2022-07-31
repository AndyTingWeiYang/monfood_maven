package com.model.res;

public class ResDto {
	private String ownerName;
	private String resPhone;
	private Integer resCategory;
	private String country;
	private String district;
	private Integer zipcode;
	private String bzAdd;
	private byte[] resFile;
	private Integer resID;
	
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getResPhone() {
		return resPhone;
	}

	public void setResPhone(String resPhone) {
		this.resPhone = resPhone;
	}

	public Integer getResCategory() {
		return resCategory;
	}

	public void setResCategory(Integer resCategory) {
		this.resCategory = resCategory;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	public String getBzAdd() {
		return bzAdd;
	}

	public void setBzAdd(String bzAdd) {
		this.bzAdd = bzAdd;
	}

	public byte[] getResFile() {
		return resFile;
	}

	public void setResFile(byte[] resFile) {
		this.resFile = resFile;
	}

	public Integer getResID() {
		return resID;
	}

	public void setResID(Integer resID) {
		this.resID = resID;
	}

	@Override
	public String toString() {
		return "ResDto [ownerName=" + ownerName + ", resPhone=" + resPhone + ", resCategory=" + resCategory
				+ ", country=" + country + ", district=" + district + ", zipcode=" + zipcode + ", bzAdd=" + bzAdd
				+ ", resID=" + resID + "]";
	}

	

}
