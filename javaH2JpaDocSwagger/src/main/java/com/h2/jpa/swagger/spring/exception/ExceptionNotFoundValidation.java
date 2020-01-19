package com.h2.jpa.swagger.spring.exception;

public class ExceptionNotFoundValidation extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExceptionNotFoundValidation(String message) {
		super(message);
	}
}
