package com.products.product.service;

import java.util.concurrent.ExecutionException;

import com.products.product.model.AggregateProductDetailsResponse;

public interface ProductDetailsService {
	
	AggregateProductDetailsResponse retrieveProductDetails(String productID) throws InterruptedException, ExecutionException;

}
