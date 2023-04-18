package com.assignment.review.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.assignment.review.model.ProductReviewRequest;
import com.assignment.review.model.RetrieveProductReviewResponse;

public interface ReviewService {
	
	RetrieveProductReviewResponse getProductReview(String productId);
	ResponseEntity<String> createProductReview(ProductReviewRequest productReviewRequest);
	ResponseEntity<String> deleteProductReview(String productId);
	RetrieveProductReviewResponse updateProductReview(String numberOfReviews, ProductReviewRequest productReviewRequest);
	List<RetrieveProductReviewResponse> getAllProductReview();
}
