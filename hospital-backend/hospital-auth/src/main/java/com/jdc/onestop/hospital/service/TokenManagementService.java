package com.jdc.onestop.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.onestop.hospital.api.input.TokenRefreshForm;
import com.jdc.onestop.hospital.api.input.TokenRequestForm;
import com.jdc.onestop.hospital.commons.dto.TokenResponse;
import com.jdc.onestop.hospital.domain.member.repo.AccountRepo;
import com.jdc.onestop.hospital.security.JwtTokenGenerator;
import com.jdc.onestop.hospital.security.JwtTokenParser;
import com.jdc.onestop.hospital.security.TokenType;

@Service
public class TokenManagementService {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenParser jwtTokenParser;
	
	@Autowired
	private JwtTokenGenerator jwtTokenGenerator;
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Transactional(readOnly = true)
	public TokenResponse generate(TokenRequestForm form) {
		
		var usernamePasswodToken = UsernamePasswordAuthenticationToken.unauthenticated(form.username(), form.password());
		var authentication = authenticationManager.authenticate(usernamePasswodToken);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return getResponse(authentication);
	}

	@Transactional(readOnly = true)
	public TokenResponse refresh(TokenRefreshForm form) {
		var authentication = jwtTokenParser.parse(TokenType.Refresh, form.refreshToken());
		return getResponse(authentication);
	}


	private TokenResponse getResponse(Authentication authentication) {
		
		var account = accountRepo.findOneByUsername(authentication.getName())
				.orElseThrow(() -> new UsernameNotFoundException("Invalid login id."));
		
		var roles = authentication.getAuthorities().stream().map(a -> a.getAuthority()).toList();
		var accesToken = jwtTokenGenerator.generate(TokenType.Access, authentication);
		var refreshToken = jwtTokenGenerator.generate(TokenType.Refresh, authentication);
		
		return TokenResponse.from(account, roles, accesToken, refreshToken);
	}
}
