package com.jdc.onestop.hospital.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.hospital.api.input.ChangePassForm;
import com.jdc.onestop.hospital.commons.dto.TokenResponse;
import com.jdc.onestop.hospital.service.ChangePasswordPasswordService;

@RestController
@RequestMapping("change-pass")
public class ChangePasswordApi {
	
	@Autowired
	private ChangePasswordPasswordService service;

	@PostMapping
	@PreAuthorize("#form.username == authentication.name")
	public TokenResponse change(
			@Validated @RequestBody ChangePassForm form, BindingResult result) {
		return service.change(form);
	}
}
