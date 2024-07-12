package com.jdc.onestop.hospital.service;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.onestop.hospital.api.input.ChangePassForm;
import com.jdc.onestop.hospital.commons.dto.TokenResponse;
import com.jdc.onestop.hospital.domain.member.repo.AccountRepo;
import com.jdc.onestop.hospital.domain.member.repo.EmployeeRepo;
import com.jdc.onestop.hospital.exceptions.ApiBusinessException;
import com.jdc.onestop.hospital.security.JwtTokenGenerator;
import com.jdc.onestop.hospital.security.TokenType;

@Service
public class ChangePasswordPasswordService {
	
	@Autowired
	private JwtTokenGenerator jwtTokenGenerator;
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public TokenResponse change(ChangePassForm form) {
		
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		var account = accountRepo.findOneByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Invalid login id."));
		
		if(!passwordEncoder.matches(form.oldPass(), account.getPassword())) {
			throw new ApiBusinessException("Invalid old password.");
		}
		
		account.setPassword(passwordEncoder.encode(form.newPass()));
		
		String[] roles = switch(account.getRole()) {
		case Admin, Patient -> new String[] {account.getRole().name(), "Activated"};
		case Office, Doctor -> {
			var employee = employeeRepo.findOneByAccountUsername(account.getUsername())
					.orElseThrow(() -> new UsernameNotFoundException("Invalid login id."));
			if(!employee.isActivated()) {
				employee.setActivated(true);
				employee.setActivatedAt(LocalDateTime.now());
			}
			yield new String[] {account.getRole().name(), "Activated"};
		}
		};
		
		var authentication = UsernamePasswordAuthenticationToken.authenticated(username, null, Arrays.stream(roles).map(SimpleGrantedAuthority::new).toList());
		
		var accesToken = jwtTokenGenerator.generate(TokenType.Access, authentication);
		var refreshToken = jwtTokenGenerator.generate(TokenType.Refresh, authentication);
		
		return new TokenResponse(account.getFullName(), Arrays.asList(roles), username, accesToken, refreshToken);
	}
	
}
