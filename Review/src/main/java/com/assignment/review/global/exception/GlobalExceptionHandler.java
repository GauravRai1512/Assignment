package com.assignment.review.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.assignment.review.exceptions.ReviewNotReceivedServiceException;
import com.assignment.review.model.ErrorResponse;
import com.assignment.review.model.MoreInfo;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({ReviewNotReceivedServiceException.class})
	public ResponseEntity<Object> handleAllExceptions(ReviewNotReceivedServiceException ex){
		
		return parseBaseRuntimeException(ex, HttpStatus.NOT_FOUND);
		
	}

	private ResponseEntity<Object> parseBaseRuntimeException(RuntimeException ex, HttpStatus internalServerError) {
		// TODO Auto-generated method stub

		ErrorResponse error = new ErrorResponse();
		MoreInfo moreInfo = new MoreInfo();
		
		moreInfo.setUserMessage(ex.getMessage());
		
		error.setMoreInfo(moreInfo);
		return new ResponseEntity<Object>(error, internalServerError);
	}

}
