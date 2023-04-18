package com.assignment.review.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.review.exceptions.ReviewNotReceivedServiceException;
import com.assignment.review.model.ProductReviewRequest;
import com.assignment.review.model.RetrieveProductReviewResponse;
import com.assignment.review.persistence.productreviewdb.Review;
import com.assignment.review.repository.ReviewRepository;
import com.assignment.review.services.ReviewService;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Single;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;

	@SuppressWarnings("null")
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
	public ResponseEntity<String> deleteProductReview(String productId) {
		// TODO Auto-generated method stub
		reviewRepository.deleteByProductID(productId);
		return new ResponseEntity<>("Successfuly Deleted",HttpStatus.OK);
	}

	@Override
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
