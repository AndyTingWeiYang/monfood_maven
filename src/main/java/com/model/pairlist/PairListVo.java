package com.model.pairlist;

import java.sql.Date;

import javax.xml.crypto.Data;

public class PairListVo {
	private Integer pairId;
	private Integer useraId;
	private Integer userbId;
	private Integer useraAnswer;
	private Integer userbAnswer;
	private Integer status;
	private java.sql.Date pairedDate;
	
	public PairListVo() {

	}

	public PairListVo(Integer pairId, Integer useraId, Integer userbId, Integer useraAnswer, Integer userbAnswer,
			Integer status, Date pairedDate, Date updateDate) {
		super();
		this.pairId = pairId;
		this.useraId = useraId;
		this.userbId = userbId;
		this.useraAnswer = useraAnswer;
		this.userbAnswer = userbAnswer;
		this.status = status;
		this.pairedDate = pairedDate;
	}

	@Override
	public String toString() {
		return "PairListVo [pairId=" + pairId + ", useraId=" + useraId + ", userbId=" + userbId + ", useraAnswer="
				+ useraAnswer + ", userbAnswer=" + userbAnswer + ", status=" + status + ", pairedDate=" + pairedDate
				+ "]";
	}

	public Integer getPairId() {
		return pairId;
	}

	public void setPairId(Integer pairId) {
		this.pairId = pairId;
	}

	public Integer getUseraId() {
		return useraId;
	}

	public void setUseraId(Integer useraId) {
		this.useraId = useraId;
	}

	public Integer getUserbId() {
		return userbId;
	}

	public void setUserbId(Integer userbId) {
		this.userbId = userbId;
	}

	public Integer getUseraAnswer() {
		return useraAnswer;
	}

	public void setUseraAnswer(Integer useraAnswer) {
		this.useraAnswer = useraAnswer;
	}

	public Integer getUserbAnswer() {
		return userbAnswer;
	}

	public void setUserbAnswer(Integer userbAnswer) {
		this.userbAnswer = userbAnswer;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public java.sql.Date getPairedDate() {
		return pairedDate;
	}

	public void setPairedDate(java.sql.Date pairedDate) {
		this.pairedDate = pairedDate;
	}


}
