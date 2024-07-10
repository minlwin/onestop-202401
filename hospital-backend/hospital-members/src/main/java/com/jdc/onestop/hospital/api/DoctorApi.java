package com.jdc.onestop.hospital.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
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
import org.springframework.web.multipart.MultipartFile;

import com.jdc.onestop.hospital.api.input.DoctorEditForm;
import com.jdc.onestop.hospital.api.input.DoctorSearch;
import com.jdc.onestop.hospital.api.output.DoctorDetails;
import com.jdc.onestop.hospital.api.output.DoctorListItem;
import com.jdc.onestop.hospital.commons.dto.DoctorInfo;
import com.jdc.onestop.hospital.commons.dto.StatusUpdateForm;
import com.jdc.onestop.hospital.domain.PageInfo;
import com.jdc.onestop.hospital.service.DoctorService;

@RestController
@RequestMapping("doctors")
public class DoctorApi {
	
	@Autowired
	private DoctorService service;

	@GetMapping
	PageInfo<DoctorListItem> search(DoctorSearch form) {
		return service.search(form);
	}
	
	@GetMapping("{id}")
	@PostAuthorize("hasAnyAuthority('Admin', 'Office') || (hasAuthority('Doctor') && #returnObject.email eq authentication.name)")
	DoctorDetails findById(@PathVariable int id) {
		return service.findById(id);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyAuthority('Admin', 'Office')")
	DoctorInfo create(
			@Validated @RequestBody DoctorEditForm form, 
			BindingResult result) {
		return service.create(form);
	}

	@PutMapping("{id}")
	@PreAuthorize("hasAnyAuthority('Admin', 'Office') || (hasAuthority('Doctor') && #form.email eq authentication.name)")
	DoctorInfo edit(@PathVariable int id, 
			@Validated @RequestBody DoctorEditForm form, 
			BindingResult result) {
		return service.update(id, form);
	}
	
	@PutMapping("{id}/status")
	@PreAuthorize("hasAnyAuthority('Admin', 'Office')")
	DoctorInfo updateStatus(@PathVariable int id, 
			@Validated @RequestBody StatusUpdateForm form, 
			BindingResult result) {
		return service.updateStatus(id, form);
	}
	
	@PutMapping("{id}/profile")
	DoctorInfo uploadProfileImage(@PathVariable int id, 
			MultipartFile file) {
		return service.uploadImage(id, file);
	}
	
}
