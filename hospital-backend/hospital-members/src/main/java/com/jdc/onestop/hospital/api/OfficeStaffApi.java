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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.hospital.api.input.OfficeStaffCreateForm;
import com.jdc.onestop.hospital.api.input.OfficeStaffEditForm;
import com.jdc.onestop.hospital.api.input.OfficeStaffSearch;
import com.jdc.onestop.hospital.api.output.OfficeStaffDetails;
import com.jdc.onestop.hospital.api.output.OfficeStaffListItem;
import com.jdc.onestop.hospital.commons.dto.AddressChangeForm;
import com.jdc.onestop.hospital.commons.dto.DepartmentChangeForm;
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
	PageInfo<OfficeStaffListItem> search(OfficeStaffSearch form, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		return service.search(form, page, size);
	}
	
	@GetMapping("{id}")
	OfficeStaffDetails findById(@PathVariable int id) {
		return service.findById(id);
	}
	
	@PostMapping
	OfficeStaffDetails create(
			@Validated @RequestBody OfficeStaffCreateForm form, 
			BindingResult result) {
		return service.create(form);
	}

	@PutMapping("{id}/info")
	OfficeStaffDetails update(@PathVariable int id, 
			@Validated @RequestBody OfficeStaffEditForm form, 
			BindingResult result) {
		return service.update(id, form);
	}
	
	@PutMapping("{id}/status")
	OfficeStaffDetails update(@PathVariable int id, 
			@Validated @RequestBody StatusUpdateForm form, 
			BindingResult result) {
		return service.update(id, form);
	}
	
	@PutMapping("{id}/department")
	OfficeStaffDetails update(@PathVariable int id, 
			@Validated @RequestBody DepartmentChangeForm form, 
			BindingResult result) {
		return service.update(id, form);
	}

	@PutMapping("{id}/address")
	OfficeStaffDetails update(@PathVariable int id, 
			@Validated @RequestBody AddressChangeForm form, 
			BindingResult result) {
		return service.update(id, form);
	}
}
