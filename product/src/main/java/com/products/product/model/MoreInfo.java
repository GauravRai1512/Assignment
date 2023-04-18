package com.products.product.model;

import lombok.Data;

@Data
public class MoreInfo {
	
	private String code;
	private String userMessage;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUserMessage() {
		return userMessage;
	}
	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

}
