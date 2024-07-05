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

import com.jdc.onestop.hospital.api.input.OfficeStaffEditForm;
import com.jdc.onestop.hospital.api.input.OfficeStaffSearch;
import com.jdc.onestop.hospital.api.output.OfficeStaffDetails;
import com.jdc.onestop.hospital.api.output.OfficeStaffListItem;
import com.jdc.onestop.hospital.domain.PageInfo;

@RestController
@RequestMapping("staff")
public class OfficeStaffApi {

	@GetMapping
	PageInfo<OfficeStaffListItem> search(OfficeStaffSearch form) {
		return null;
	}
	
	@GetMapping("{id}")
	OfficeStaffDetails findById(@PathVariable int id) {
		return null;
	}
	
	@PostMapping
	OfficeStaffDetails create(
			@Validated @RequestBody OfficeStaffEditForm form, 
			BindingResult result) {
		return null;
	}

	@PutMapping("{id}")
	OfficeStaffDetails edit(@PathVariable int id, 
			@Validated @RequestBody OfficeStaffEditForm form, 
			BindingResult result) {
		return null;
	}
}
