package com.products.product.model;

import lombok.Data;

@Data
public class RetrieveProductDetails {

	private String message;
	private String location;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
