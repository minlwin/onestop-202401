package com.jdc.onestop.hospital.exceptions;

import org.springframework.security.core.AuthenticationException;

public class ApiJwtTokenExiprationException extends AuthenticationException {

	private static final long serialVersionUID = 1L;
	
	public ApiJwtTokenExiprationException(String msg) {
		super(msg);
	}

}
