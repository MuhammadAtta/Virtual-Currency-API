package com.rest.exceptions;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author Muhammad Atta
 *
 */
public class MissingFieldsException extends Exception{

	private static final long serialVersionUID = -2771673915935688251L;

	@Override
	public String getMessage() {
		return "Missing fields";
	}
	
	public int getCode() {
		return HttpStatus.BAD_REQUEST.value();
	}
	
	public HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}
}
