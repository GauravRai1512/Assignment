package com.products.product.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.products.product.model.AggregateProductDetailsResponse;
import com.products.product.service.ProductDetailsService;

@RestController
@RequestMapping("/product")
public class ProductDetailsController {
	
	@Autowired
	ProductDetailsService productDetails;
	
	
	@GetMapping(value="{product_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AggregateProductDetailsResponse retrieveProductDetails(@PathVariable("product_id") String productID) throws InterruptedException, ExecutionException {
		
		return productDetails.retrieveProductDetails(productID);
	}

}
