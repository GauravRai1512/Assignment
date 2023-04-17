package com.assignment.review.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.assignment.review.model.ProductReviewRequest;
import com.assignment.review.services.ReviewService;


@RunWith(MockitoJUnitRunner.class)
class ProductReviewControllerTest {
   
	@InjectMocks
	ProductReviewController productReviewController;
	
	@Mock
	private ReviewService reviewService;
	
	@Test
	public void testPostProductReview() {
		
		ProductReviewRequest productReview = new ProductReviewRequest();
		productReview.setProductID("ABC7865");
		productReview.setAverageReviewScore(new BigDecimal(3.4));
		productReview.setNumerofReviews(4);
		
		ResponseEntity<String> responseSuccess = productReviewController.createProductReview(productReview);
		assertEquals(responseSuccess.getStatusCode(), HttpStatus.CREATED);
		
	}

}
