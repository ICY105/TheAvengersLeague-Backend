package com.revature.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.revature.errorhandling.ApiError;
import com.revature.errorhandling.ApiValidationError;
import com.revature.exceptions.NoUserException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	private ResponseEntity<Object> buildResponseEntity(final ApiError apiError) {
		return ResponseEntity.status(apiError.getStatus()).body(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		
		final String error = "Malformed JSON Request";
		
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		
		final String error = "Request failed validation";
		
		final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error, ex);
		
		for(final FieldError e : ex.getFieldErrors()) {
			apiError.addSubError(new ApiValidationError(e.getObjectName(), e.getDefaultMessage(), e.getField(),
					e.getRejectedValue()));
		}
		return buildResponseEntity(apiError);
		
	}

	@ExceptionHandler(NoUserException.class)
	public ResponseEntity<Object> handleUserNotFoundException(final NoUserException ex) {
		final String error = "No User Found";
		
		return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, error, ex));
	}
	
}
