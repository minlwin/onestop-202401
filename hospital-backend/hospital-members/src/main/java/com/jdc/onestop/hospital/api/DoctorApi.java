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

import com.jdc.onestop.hospital.api.input.DoctorEditForm;
import com.jdc.onestop.hospital.api.input.DoctorSearch;
import com.jdc.onestop.hospital.api.output.DoctorDetails;
import com.jdc.onestop.hospital.api.output.DoctorListItem;
import com.jdc.onestop.hospital.domain.PageInfo;

@RestController
@RequestMapping("doctor")
public class DoctorApi {

	@GetMapping
	PageInfo<DoctorListItem> search(DoctorSearch form) {
		return null;
	}
	
	@GetMapping("{id}")
	DoctorDetails findById(@PathVariable int id) {
		return null;
	}
	
	@PostMapping
	DoctorDetails create(
			@Validated @RequestBody DoctorEditForm form, 
			BindingResult result) {
		return null;
	}

	@PutMapping("{id}")
	DoctorDetails edit(@PathVariable int id, 
			@Validated @RequestBody DoctorEditForm form, 
			BindingResult result) {
		return null;
	}
}
