package com.products.product.rest.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.products.product.model.ProductReviewDetails;

@Component
public class RetrieveProductReviewComponent {
	

	@Autowired
	RestTemplate restTemplate;
	
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
