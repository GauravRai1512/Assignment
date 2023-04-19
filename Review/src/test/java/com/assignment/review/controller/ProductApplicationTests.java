package com.assignment.review.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.assignment.review.model.ProductReviewRequest;
import com.assignment.review.model.RetrieveProductReviewResponse;
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
	
	@Test
	public void testGetProductReview() {
		RetrieveProductReviewResponse responseSuccess = productReviewController.getProductReview("AC7836");
		assertNotNull(responseSuccess);
		
	}
	
	@Test
	public void testGetProductReviewNotFound() {
		RetrieveProductReviewResponse responseSuccess = productReviewController.getProductReview("AC783656");
		assertNull(responseSuccess, "response is null");
		
	}
	
	@Test
	public void testGetAllProductReview() {
		List<RetrieveProductReviewResponse> responseSuccess = productReviewController.getAllProductReview();
		assertNotNull(responseSuccess);
		
	}
	
	@Test
	public void testPutProductReview() {
		ProductReviewRequest productReview = new ProductReviewRequest();
		productReview.setProductID("AC783656");
		productReview.setAverageReviewScore(new BigDecimal(5.4));
		productReview.setNumerofReviews(14);
		RetrieveProductReviewResponse responseSuccess = productReviewController.updatedProductReview("AC783656", productReview);
		assertNotNull(responseSuccess);
		
	}
	
	
	@Test
	public void testDeleteProductReview() {
		ResponseEntity<String> responseSuccess = productReviewController.deleteProductReview("AC783656");
		assertEquals(responseSuccess.getStatusCode(), HttpStatus.OK);
		
	}
	
	@Test(expected = ProductReviewHasAlreadyDeleted.class)
	public void testDeleteProductReviewException() {
		productReviewController.deleteProductReview("AC78365689798");
		
	}

}
