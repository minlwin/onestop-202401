package com.jdc.onestop.hospital.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.hospital.api.input.PatientSearch;
import com.jdc.onestop.hospital.api.output.PatientDetails;
import com.jdc.onestop.hospital.api.output.PatientListItem;
import com.jdc.onestop.hospital.domain.PageInfo;

@RestController
@RequestMapping("patients")
public class PatientApi {

	@GetMapping
	PageInfo<PatientListItem> search(PatientSearch form) {
		return null;
	}
	
	@GetMapping("{id}")
	PatientDetails findById(@PathVariable int id) {
		return null;
	}
}
