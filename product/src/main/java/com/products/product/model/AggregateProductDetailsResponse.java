package com.products.product.model;

import java.math.BigDecimal;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class AggregateProductDetailsResponse {


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
	private String message;
	private String location;
	private BigDecimal averageReviewScore;
	private int numerofReviews;
}
