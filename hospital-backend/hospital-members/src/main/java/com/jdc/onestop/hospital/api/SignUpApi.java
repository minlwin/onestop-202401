package com.jdc.onestop.hospital.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.hospital.api.input.SignUpForm;
import com.jdc.onestop.hospital.api.output.PatientDetails;
import com.jdc.onestop.hospital.service.SignUpService;

@RestController
@RequestMapping("public/signup")
public class SignUpApi {
	
	@Autowired
	private SignUpService service;

	@PostMapping
	PatientDetails signUp(
			@Validated @RequestBody SignUpForm form, 
			BindingResult result) {
		return service.signUp(form);
	}

}
