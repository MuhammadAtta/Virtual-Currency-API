package com.rest.handler;

import java.io.Serializable;

/**
 * 
 * @author Muhammad Atta
 *
 */

public class ErrorDetails implements Serializable {

	private static final long serialVersionUID = -4864531505149055954L;

	private String message;

	private int errorCode;

	public ErrorDetails() {
		super();
	}

	public ErrorDetails(String message, int errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
