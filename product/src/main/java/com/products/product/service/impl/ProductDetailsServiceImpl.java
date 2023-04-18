package com.products.product.service.impl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.products.product.model.AggregateProductDetailsResponse;
import com.products.product.model.ProductReviewDetails;
import com.products.product.model.RetrieveProductDetails;
import com.products.product.rest.component.RetrieveProductDetailsComponent;
import com.products.product.rest.component.RetrieveProductReviewComponent;
import com.products.product.service.ProductDetailsService;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {
	
	@Autowired
	private RetrieveProductDetailsComponent retrieveProductDetails;
	
	@Autowired
	private RetrieveProductReviewComponent retrieveProductReview;
	
	public AggregateProductDetailsResponse retrieveProductDetails(String productID) throws InterruptedException, ExecutionException {
		
		CompletableFuture<ProductReviewDetails> future1 = 
				CompletableFuture.supplyAsync(()->retrieveProductReview.getProductReview(productID));
		
		CompletableFuture<RetrieveProductDetails> future2 = 
				CompletableFuture.supplyAsync(()->retrieveProductDetails.getProductDetails(productID));
		
		ProductReviewDetails productReview = future1.get();
		RetrieveProductDetails retrieveProduct = future2.get();
		
		return aggregateBoth(productReview, retrieveProduct);
		
	}
	
	private AggregateProductDetailsResponse aggregateBoth(ProductReviewDetails productReview,
			RetrieveProductDetails retrieveProduct) {

		AggregateProductDetailsResponse aggregateObject = new AggregateProductDetailsResponse();
		// TODO Auto-generated method stub
		
			aggregateObject.setMessage(retrieveProduct.getMessage());
			aggregateObject.setLocation(retrieveProduct.getLocation());
			aggregateObject.setAverageReviewScore(productReview.getAverageReviewScore());
			aggregateObject.setNumerofReviews(productReview.getNumerofReviews());
		
		return aggregateObject;
	}
	

}
