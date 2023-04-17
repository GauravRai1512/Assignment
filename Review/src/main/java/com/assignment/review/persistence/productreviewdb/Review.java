package com.assignment.review.persistence.productreviewdb;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Reviews")
public class Review implements Serializable {
	
	private static final long serialVersionUID = 1972346746378637863L;
	
	@Id
	private String productID;
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	private BigDecimal averageReviewScore;
	private int numberOfReviews;
	public BigDecimal getAverageReviewScore() {
		return averageReviewScore;
	}
	public void setAverageReviewScore(BigDecimal averageReviewScore) {
		this.averageReviewScore = averageReviewScore;
	}
	public int getNumberOfReviews() {
		return numberOfReviews;
	}
	public void setNumberOfReviews(int numberOfReviews) {
		this.numberOfReviews = numberOfReviews;
	}
	

}
