package com.products.product.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductReviewDetails {
	
	private BigDecimal averageReviewScore;
	private int numerofReviews;
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
