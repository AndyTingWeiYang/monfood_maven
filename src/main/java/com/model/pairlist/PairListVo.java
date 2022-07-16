package com.model.pairlist;

public class PairListVo {
	private Integer pairId;
	private Integer userAId;
	private Integer userBId;
	private Integer userAAnswer;
	private Integer userBAnswer;
	private Integer status;
	
	public PairListVo() {

	}
	
	public PairListVo(Integer pairId, Integer userAId, Integer userBId, Integer userAAnswer, Integer userBAnswer,
			Integer status) {
		super();
		this.pairId = pairId;
		this.userAId = userAId;
		this.userBId = userBId;
		this.userAAnswer = userAAnswer;
		this.userBAnswer = userBAnswer;
		this.status = status;
	}



	public Integer getPairId() {
		return pairId;
	}

	public void setPairId(Integer pairId) {
		this.pairId = pairId;
	}

	public Integer getUserAId() {
		return userAId;
	}

	public void setUserAId(Integer userAId) {
		this.userAId = userAId;
	}

	public Integer getUserBId() {
		return userBId;
	}

	public void setUserBId(Integer userBId) {
		this.userBId = userBId;
	}

	public Integer getUserAAnswer() {
		return userAAnswer;
	}

	public void setUserAAnswer(Integer userAAnswer) {
		this.userAAnswer = userAAnswer;
	}

	public Integer getUserBAnswer() {
		return userBAnswer;
	}

	public void setUserBAnswer(Integer userBAnswer) {
		this.userBAnswer = userBAnswer;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
	
}
