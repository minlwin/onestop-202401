package com.jdc.onestop.hospital.exceptions.handler;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jdc.onestop.hospital.domain.DomainException;
import com.jdc.onestop.hospital.exceptions.ApiBusinessException;
import com.jdc.onestop.hospital.exceptions.ApiJwtTokenExiprationException;
import com.jdc.onestop.hospital.exceptions.ApiJwtTokenInvalidationException;
import com.jdc.onestop.hospital.exceptions.ApiValidationException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RestControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	List<String> handle(ApiValidationException e) {
		return e.getMessages();
	}
	
	@ExceptionHandler(value = {
		ApiBusinessException.class,	
		DomainException.class
	})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	List<String> handle(RuntimeException e) {
		return List.of(e.getMessage());
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.REQUEST_TIMEOUT)
	List<String> handle(ApiJwtTokenExiprationException e) {
		log.error("Token Expiration Error", e);
		return List.of("Your access token has been expired. Please refresh token again.");
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	List<String> handle(ApiJwtTokenInvalidationException e) {
		log.error("Token Invalidation Error", e);
		return List.of(e.getMessage());
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	List<String> handle(AuthenticationException e) {
		log.error("Authentication Error", e);
		return switch(e) {
		case BadCredentialsException ex -> List.of("Please check password.");
		case UsernameNotFoundException ex -> List.of("Please check login id.");
		case DisabledException ex -> List.of("Your account is not enable at this time.");
		case AccountExpiredException ex -> List.of("Your account is expired.");
		default -> List.of("You need to login for this operation.");
		};
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	List<String> handle(AccessDeniedException e) {
		return List.of(e.getMessage());
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	List<String> handle(Exception e) {
		log.error("System Error", e);
		return List.of(e.getMessage());
	}
	
}
