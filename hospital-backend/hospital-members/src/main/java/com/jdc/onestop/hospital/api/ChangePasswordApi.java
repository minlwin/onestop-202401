package com.jdc.onestop.hospital.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.hospital.api.input.ChangePassForm;
import com.jdc.onestop.hospital.api.output.ChangePassResult;

@RestController
@RequestMapping("change-pass")
public class ChangePasswordApi {

	@PostMapping("{username}")
	@PreAuthorize("@username == authentication.name")
	public ChangePassResult change(
			@PathVariable String username,
			@Validated @RequestBody ChangePassForm form, BindingResult result) {
		return null;
	}
}
