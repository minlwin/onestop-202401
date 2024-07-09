package com.jdc.onestop.hospital.exceptions;

public class ApiBusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ApiBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApiBusinessException(String message) {
		super(message);
	}
}
