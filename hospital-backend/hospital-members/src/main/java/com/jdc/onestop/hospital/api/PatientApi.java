package com.jdc.onestop.hospital.api;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.hospital.api.input.PatientEditForm;
import com.jdc.onestop.hospital.api.input.PatientSearch;
import com.jdc.onestop.hospital.api.output.PatientDetails;
import com.jdc.onestop.hospital.api.output.PatientListItem;
import com.jdc.onestop.hospital.domain.PageInfo;

@RestController
@RequestMapping("patient")
public class PatientApi {

	@GetMapping
	PageInfo<PatientListItem> search(PatientSearch form) {
		return null;
	}
	
	@GetMapping("{id}")
	PatientDetails findById(@PathVariable int id) {
		return null;
	}
	
	@PostMapping
	PatientDetails create(
			@Validated @RequestBody PatientEditForm form, 
			BindingResult result) {
		return null;
	}

	@PutMapping("{id}")
	PatientDetails edit(@PathVariable int id, 
			@Validated @RequestBody PatientEditForm form, 
			BindingResult result) {
		return null;
	}
}
