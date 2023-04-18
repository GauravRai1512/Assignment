package com.assignment.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.review.model.ProductReviewRequest;
import com.assignment.review.model.RetrieveProductReviewResponse;
import com.assignment.review.services.ReviewService;

@RestController
@RequestMapping("/review")
public class ProductReviewController {

	@Autowired
	private ReviewService reviewService;

	@PostMapping(value = "/create")
	public ResponseEntity<String> createProductReview(@RequestBody ProductReviewRequest productReviewRequest) {

		return reviewService.createProductReview(productReviewRequest);
	}

	@GetMapping(value = "{product_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public RetrieveProductReviewResponse getProductReview(@PathVariable("product_id") String productId) {

		return reviewService.getProductReview(productId);

	}

	@DeleteMapping(value = "/delete/{product_id}")
	public ResponseEntity<String> deleteProductReview(@PathVariable("product_id") String productId) {
		return reviewService.deleteProductReview(productId);

	}

	@PutMapping(value = "{product_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public RetrieveProductReviewResponse updatedProductReview(@PathVariable("product_id") String productId,
			@RequestBody ProductReviewRequest productReviewRequest) {
		return reviewService.updateProductReview(productId, productReviewRequest);
	}
	
	@GetMapping(value="/products",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RetrieveProductReviewResponse> getAllProductReview() {
		return reviewService.getAllProductReview();
	}

}
