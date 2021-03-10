package com.rest.exceptions;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author Muhammad Atta
 *
 */
public class InvalidFieldsException extends Exception{

	private static final long serialVersionUID = 439407943406019281L;

	@Override
	public String getMessage() {
		return "Invalid fields";
	}
	
	public int getCode() {
		return HttpStatus.BAD_REQUEST.value();
	}
	
	public HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}

}
