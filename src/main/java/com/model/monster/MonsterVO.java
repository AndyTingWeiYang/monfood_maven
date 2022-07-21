package com.model.monster;

import java.io.Serializable;

public class MonsterVO implements Serializable {

	private Integer monsLevel;
	private Integer discount;
	private byte[] monsPic;
	
	public MonsterVO() {
		
	}

	public MonsterVO(Integer monsLevel, Integer discount, byte[] monsPic) {
		super();
		this.monsLevel = monsLevel;
		this.discount = discount;
		this.monsPic = monsPic;
	}

	public Integer getMonsLevel() {
		return monsLevel;
	}

	public void setMonsLevel(Integer monsLevel) {
		this.monsLevel = monsLevel;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public byte[] getMonsPic() {
		return monsPic;
	}

	public void setMonsPic(byte[] monsPic) {
		this.monsPic = monsPic;
	}

	
}
