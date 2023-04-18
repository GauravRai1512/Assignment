package com.products.product.rest.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.products.product.exceptions.ProductResponseNotAvailable;
import com.products.product.model.RetrieveProductDetails;

@Component
public class RetrieveProductDetailsComponent {
	

	@Autowired
	RestTemplate restTemplate;

	public RetrieveProductDetails getProductDetails(String productID) {
		RetrieveProductDetails response = null;
		
		try {
		response = restTemplate.getForObject("https://www.adidas.co.uk/api/products/"+productID, 
				RetrieveProductDetails.class);
		}catch(Exception e) {
					throw new ProductResponseNotAvailable("Product Details is not available", "Issue in service call");
				
		}
		return response;
	}

}
