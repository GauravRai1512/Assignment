package com.assignment.review.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.assignment.review.exceptions.ProductReviewHasAlreadyDeleted;
import com.assignment.review.exceptions.ReviewNotReceivedServiceException;
import com.assignment.review.model.ProductReviewRequest;
import com.assignment.review.model.RetrieveProductReviewResponse;
import com.assignment.review.persistence.productreviewdb.Review;
import com.assignment.review.repository.ReviewRepository;
import com.assignment.review.services.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;

	@SuppressWarnings("null")
	@Retryable(value=RuntimeException.class, maxAttempts = 3, backoff = @Backoff(3000))
	public RetrieveProductReviewResponse getProductReview(String productId){
		// TODO Auto-generated method stub
		RetrieveProductReviewResponse reviewResponse = new RetrieveProductReviewResponse();
		Review review = reviewRepository.findReviewByProductID(productId);
		if(null != review) {
		    reviewResponse.setProductID(productId);
		    reviewResponse.setAverageReviewScore(review.getAverageReviewScore());
		    reviewResponse.setNumerofReviews(review.getNumberOfReviews());
		}else {
			throw new ReviewNotReceivedServiceException("PRODUCT_REVIEW_NOT_AVAILABLE", "review has not recieved yet");
		}
		
		return reviewResponse;
	}

	@Override
	@Retryable(value=RuntimeException.class, maxAttempts = 3, backoff = @Backoff(3000))
	public ResponseEntity<String> createProductReview(ProductReviewRequest productReviewRequest) {
		// TODO Auto-generated method stub
		Review productReview = new Review();
		productReview.setAverageReviewScore(productReviewRequest.getAverageReviewScore());
		productReview.setNumberOfReviews(productReviewRequest.getNumerofReviews());
		productReview.setProductID(productReviewRequest.getProductID());
		Review createReview = reviewRepository.save(productReview);
		return new ResponseEntity<>("Successfuly Created",HttpStatus.CREATED);
	}

	@Override
	@Retryable(value = RuntimeException.class, maxAttempts = 3, backoff = @Backoff(3000))
	public ResponseEntity<String> deleteProductReview(String productId) {
		// TODO Auto-generated method stub
		Review review = reviewRepository.findReviewByProductID(productId);
		if (review != null) {

			reviewRepository.deleteByProductID(productId);
		} else {
			throw new ProductReviewHasAlreadyDeleted("Already deleted", "Already deleted or not available to delete");
		}
		return new ResponseEntity<>("Successfuly Deleted", HttpStatus.OK);
	}

	@Override
	@Retryable(value = RuntimeException.class, maxAttempts = 3, backoff = @Backoff(3000))
	public RetrieveProductReviewResponse updateProductReview(String productId, ProductReviewRequest productReviewRequest) {
		// TODO Auto-generated method stub
		RetrieveProductReviewResponse updatedProductReview = new RetrieveProductReviewResponse();
		Review updateReview = reviewRepository.findReviewByProductID(productId);
		
		updateReview.setNumberOfReviews(productReviewRequest.getNumerofReviews());
		updateReview.setAverageReviewScore(productReviewRequest.getAverageReviewScore());
		
		Review reviewResponse=reviewRepository.save(updateReview);
		
		updatedProductReview.setProductID(reviewResponse.getProductID());
		updatedProductReview.setAverageReviewScore(reviewResponse.getAverageReviewScore());
		updatedProductReview.setNumerofReviews(reviewResponse.getNumberOfReviews());
		
		return updatedProductReview;
	}

	@Override
	@Retryable(value = RuntimeException.class, maxAttempts = 3, backoff = @Backoff(3000))
	public List<RetrieveProductReviewResponse> getAllProductReview() {
		// TODO Auto-generated method stub
		List<RetrieveProductReviewResponse> getAllProductinList = new ArrayList<>();
		List<Review> updateReview = reviewRepository.findAll();
		updateReview.stream().forEach(productReview -> {

			RetrieveProductReviewResponse singleProductReview = new RetrieveProductReviewResponse();
			singleProductReview.setProductID(productReview.getProductID());
			singleProductReview.setAverageReviewScore(productReview.getAverageReviewScore());
			singleProductReview.setNumerofReviews(productReview.getNumberOfReviews());
			getAllProductinList.add(singleProductReview);
		});

		return getAllProductinList;
	}

	

}
