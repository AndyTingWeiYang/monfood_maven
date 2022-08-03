package com.model.chatroom;

import java.util.Date;

public class ChatInfoVo {
	
	private String type;
	private Integer senderId;
	private Integer receiverId;
	private String message;
	
	public ChatInfoVo() {

	}
	
	public ChatInfoVo(String type, Integer senderId, Integer receiverId, String message) {
		this.type = type;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



}
