package com.assignment.review.model;

import java.math.BigDecimal;

public class ProductReviewRequest {
	
	private String productID;
	private BigDecimal averageReviewScore;
	private int numerofReviews;
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public BigDecimal getAverageReviewScore() {
		return averageReviewScore;
	}
	public void setAverageReviewScore(BigDecimal averageReviewScore) {
		this.averageReviewScore = averageReviewScore;
	}
	public int getNumerofReviews() {
		return numerofReviews;
	}
	public void setNumerofReviews(int numerofReviews) {
		this.numerofReviews = numerofReviews;
	}

}
