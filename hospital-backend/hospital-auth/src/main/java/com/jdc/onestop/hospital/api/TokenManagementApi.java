package com.jdc.onestop.hospital.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.hospital.api.input.TokenRefreshForm;
import com.jdc.onestop.hospital.api.input.TokenRequestForm;
import com.jdc.onestop.hospital.api.output.TokenResponse;
import com.jdc.onestop.hospital.service.TokenManagementService;

@RestController
@RequestMapping("token")
public class TokenManagementApi {
	
	@Autowired
	private TokenManagementService service;

	@PostMapping("generate")
	public TokenResponse generate(@Validated TokenRequestForm form, BindingResult result) {		
		return service.generate(form);
	}

	@PostMapping("refresh")
	public TokenResponse refresh(@Validated TokenRefreshForm form, BindingResult result) {
		return service.refresh(form);
	}
}
