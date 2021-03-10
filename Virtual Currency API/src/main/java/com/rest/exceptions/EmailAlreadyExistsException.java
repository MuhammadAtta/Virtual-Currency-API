package com.rest.exceptions;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author Muhammad Atta
 *
 */

public class EmailAlreadyExistsException extends Exception {

	private static final long serialVersionUID = -7005873522410177942L;

	@Override
	public String getMessage() {
		return "E-mail already exists";
	}

	public int getCode() {
		return HttpStatus.BAD_REQUEST.value();
	}

	public HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}
}
