package com.products.product.globalexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.products.product.exceptions.ProductResponseNotAvailable;
import com.products.product.model.ErrorResponse;
import com.products.product.model.MoreInfo;



@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	

	
	@ExceptionHandler({ProductResponseNotAvailable.class})
	public ResponseEntity<Object> handleAllExceptions(ProductResponseNotAvailable ex){
		
		return parseBaseRuntimeException(ex, HttpStatus.GATEWAY_TIMEOUT);
		
	}

	private ResponseEntity<Object> parseBaseRuntimeException(RuntimeException ex, HttpStatus gatewayTimeOut) {
		// TODO Auto-generated method stub

		ErrorResponse error = new ErrorResponse();
		MoreInfo moreInfo = new MoreInfo();
		
		moreInfo.setUserMessage(ex.getMessage());
		
		error.setMoreInfo(moreInfo);
		return new ResponseEntity<Object>(error, gatewayTimeOut);
	}


}
	
	