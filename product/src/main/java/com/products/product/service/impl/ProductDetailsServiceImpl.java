package com.products.product.service.impl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.products.product.exceptions.ProductResponseNotAvailable;
import com.products.product.model.AggregateProductDetailsResponse;
import com.products.product.model.ProductReviewDetails;
import com.products.product.model.RetrieveProductDetails;
import com.products.product.service.ProductDetailsService;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {
	
	@Autowired
	RestTemplate restTemplate;
	
	public AggregateProductDetailsResponse retrieveProductDetails(String productID) throws InterruptedException, ExecutionException {
		ResponseEntity<RetrieveProductDetails> response = null;
		HttpEntity<String> requestEntiy = new HttpEntity<>(productID, null);
		
		CompletableFuture<ProductReviewDetails> future1 = 
				CompletableFuture.supplyAsync(()->getProductReview(productID));
		
		CompletableFuture<RetrieveProductDetails> future2 = 
				CompletableFuture.supplyAsync(()->getProductDetails(productID));
		
		ProductReviewDetails productReview = future1.get();
		RetrieveProductDetails retrieveProduct = future2.get();
		
		return aggregateBoth(productReview, retrieveProduct);
		
	}
	
	private AggregateProductDetailsResponse aggregateBoth(ProductReviewDetails productReview,
			RetrieveProductDetails retrieveProduct) {

		AggregateProductDetailsResponse aggregateObject = new AggregateProductDetailsResponse();
		// TODO Auto-generated method stub
		if (productReview != null && retrieveProduct != null) {
			aggregateObject.setMessage(retrieveProduct.getMessage());
			aggregateObject.setLocation(retrieveProduct.getLocation());
			aggregateObject.setAverageReviewScore(productReview.getAverageReviewScore());
			aggregateObject.setNumerofReviews(productReview.getNumerofReviews());
		} else {
			throw new ProductResponseNotAvailable("Product Details is not available", "Issue is service call");
		}
		return aggregateObject;
	}

	public RetrieveProductDetails getProductDetails(String productID) {
		RetrieveProductDetails response = null;
		try {
		response = restTemplate.getForObject("https://www.adidas.co.uk/api/products/"+productID, 
				RetrieveProductDetails.class);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return response;
	}
	
	
	public ProductReviewDetails getProductReview(String productID) {
		ResponseEntity<ProductReviewDetails> response = null;
		HttpEntity<String> requestEntiy = new HttpEntity<>(productID, null);
		try {
		response = restTemplate.exchange("http://localhost:8080/review/"+productID, HttpMethod.GET, requestEntiy,
				ProductReviewDetails.class);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(response.getBody().getNumerofReviews());
		return response.getBody();
	}

}
