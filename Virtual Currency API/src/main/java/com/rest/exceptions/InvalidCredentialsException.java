package com.rest.exceptions;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author Muhammad Atta
 *
 */
public class InvalidCredentialsException extends Exception {

	private static final long serialVersionUID = 8226074402890699440L;

	@Override
	public String getMessage() {
		return "Invalid e-mail or password";
	}

	public int getCode() {
		return HttpStatus.BAD_REQUEST.value();
	}

	public HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}

}
