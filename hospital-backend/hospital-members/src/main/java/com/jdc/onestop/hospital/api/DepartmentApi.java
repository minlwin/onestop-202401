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

import com.jdc.onestop.hospital.api.input.DepartmentEditForm;
import com.jdc.onestop.hospital.api.input.DepartmentSearch;
import com.jdc.onestop.hospital.api.output.DepartmentDetails;
import com.jdc.onestop.hospital.api.output.DepartmentListItem;
import com.jdc.onestop.hospital.domain.PageInfo;

@RestController
@RequestMapping("department")
public class DepartmentApi {

	@GetMapping
	PageInfo<DepartmentListItem> search(DepartmentSearch form) {
		return null;
	}
	
	@GetMapping("{id}")
	DepartmentDetails findById(@PathVariable int id) {
		return null;
	}
	
	@PostMapping
	DepartmentDetails create(
			@Validated @RequestBody DepartmentEditForm form, 
			BindingResult result) {
		return null;
	}

	@PutMapping("{id}")
	DepartmentDetails edit(@PathVariable int id, 
			@Validated @RequestBody DepartmentEditForm form, 
			BindingResult result) {
		return null;
	}
}
