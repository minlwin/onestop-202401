package com.jdc.onestop.hospital.exceptions;

import org.springframework.security.core.AuthenticationException;

public class ApiJwtTokenInvalidationException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public ApiJwtTokenInvalidationException(String msg) {
		super(msg);
	}

	public ApiJwtTokenInvalidationException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
