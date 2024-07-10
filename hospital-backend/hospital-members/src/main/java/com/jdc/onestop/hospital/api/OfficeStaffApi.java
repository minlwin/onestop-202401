package com.jdc.onestop.hospital.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.jdc.onestop.hospital.commons.dto.StatusUpdateForm;
import com.jdc.onestop.hospital.domain.PageInfo;
import com.jdc.onestop.hospital.service.OfficeStaffService;

@RestController
@RequestMapping("staffs")
@PreAuthorize("hasAnyAuthority('Admin', 'Office')")
public class OfficeStaffApi {
	
	@Autowired
	private OfficeStaffService service;

	@GetMapping
	PageInfo<OfficeStaffListItem> search(OfficeStaffSearch form) {
		return service.search(form);
	}
	
	@GetMapping("{id}")
	OfficeStaffDetails findById(@PathVariable int id) {
		return service.findById(id);
	}
	
	@PostMapping
	OfficeStaffDetails create(
			@Validated @RequestBody OfficeStaffEditForm form, 
			BindingResult result) {
		return service.create(form);
	}

	@PutMapping("{id}")
	OfficeStaffDetails edit(@PathVariable int id, 
			@Validated @RequestBody OfficeStaffEditForm form, 
			BindingResult result) {
		return service.update(id, form);
	}
	
	@PutMapping("{id}/status")
	OfficeStaffDetails updateStatus(@PathVariable int id, 
			@Validated @RequestBody StatusUpdateForm form, 
			BindingResult result) {
		return service.update(id, form);
	}
	
}
