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

import com.jdc.onestop.hospital.api.input.DepartmentEditForm;
import com.jdc.onestop.hospital.api.input.DepartmentSearch;
import com.jdc.onestop.hospital.api.output.DepartmentDetails;
import com.jdc.onestop.hospital.api.output.DepartmentListItem;
import com.jdc.onestop.hospital.domain.PageInfo;
import com.jdc.onestop.hospital.service.DepartmentService;

@RestController
@RequestMapping("departments")
public class DepartmentApi {
	
	@Autowired
	private DepartmentService service;

	@GetMapping
	PageInfo<DepartmentListItem> search(DepartmentSearch form) {
		return service.search(form);
	}
	
	@GetMapping("{id}")
	DepartmentDetails findById(@PathVariable int id) {
		return service.findById(id);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyAuthority('Admin', 'Office')")
	DepartmentDetails create(
			@Validated @RequestBody DepartmentEditForm form, 
			BindingResult result) {
		return service.create(form);
	}

	@PutMapping("{id}")
	@PreAuthorize("hasAnyAuthority('Admin', 'Office')")
	DepartmentDetails edit(@PathVariable int id, 
			@Validated @RequestBody DepartmentEditForm form, 
			BindingResult result) {
		return service.update(id, form);
	}
}
