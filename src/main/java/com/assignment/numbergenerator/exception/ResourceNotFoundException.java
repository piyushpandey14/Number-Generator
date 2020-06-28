package com.assignment.numbergenerator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3808841150986258108L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}