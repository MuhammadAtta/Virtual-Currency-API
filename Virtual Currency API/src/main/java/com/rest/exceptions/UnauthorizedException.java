package com.rest.exceptions;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author Muhammad Atta
 *
 */
public class UnauthorizedException extends Exception  {

	private static final long serialVersionUID = -6977421002189953450L;
	private String message;

	public UnauthorizedException() {
		this.message = "";
	}

	public UnauthorizedException(String message) {
		this.message = " - " + message;
	}

	@Override
	public String getMessage() {
		return "Unauthorized" + message;
	}

	public int getCode() {
		return HttpStatus.UNAUTHORIZED.value();
	}

	public HttpStatus getHttpStatus() {
		return HttpStatus.UNAUTHORIZED;
	}

}
