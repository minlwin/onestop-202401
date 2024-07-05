package com.jdc.onestop.hospital.api;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.hospital.api.input.PatientEditForm;
import com.jdc.onestop.hospital.api.output.PatientDetails;

@RestController
@RequestMapping("patient/profile")
public class PatientProfileEditApi {

	@PutMapping("{id}")
	PatientDetails edit(@PathVariable int id, 
			@Validated @RequestBody PatientEditForm form, 
			BindingResult result) {
		return null;
	}

}
