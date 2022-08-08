package com.model.product.util;

import java.util.HashMap;
import java.util.Map;

public class ErrorMsgException extends Exception {

	private static final long serialVersionUID = 1L;

	private Map<String, String> errorMsg;

	public ErrorMsgException() {
		initErrorMsg();
	}

	public void appendMessage(String msgKey, String msgValue) {
		if (errorMsg != null) {
			errorMsg.put(msgKey, msgValue);
		}
	}

	public Map<String, String> getErrorMsg() {
		return errorMsg;
	}

	private void initErrorMsg() {
		if (errorMsg == null) {
			errorMsg = new HashMap<>();
		}
	}
}
