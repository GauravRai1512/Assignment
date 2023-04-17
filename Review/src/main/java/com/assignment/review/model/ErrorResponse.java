package com.assignment.review.model;

import lombok.Data;

@Data
public class ErrorResponse {
	
	private MoreInfo moreInfo;

	public MoreInfo getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(MoreInfo moreInfo) {
		this.moreInfo = moreInfo;
	}

}
