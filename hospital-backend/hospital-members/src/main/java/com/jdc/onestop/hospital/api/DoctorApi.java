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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.onestop.hospital.api.input.DoctorEditForm;
import com.jdc.onestop.hospital.api.input.DoctorSearch;
import com.jdc.onestop.hospital.api.input.DoctorSectionForms;
import com.jdc.onestop.hospital.api.output.DoctorDetails;
import com.jdc.onestop.hospital.api.output.DoctorListItem;
import com.jdc.onestop.hospital.commons.dto.AddressChangeForm;
import com.jdc.onestop.hospital.commons.dto.DepartmentChangeForm;
import com.jdc.onestop.hospital.commons.dto.StatusUpdateForm;
import com.jdc.onestop.hospital.domain.PageInfo;
import com.jdc.onestop.hospital.service.DoctorService;

@RestController
@RequestMapping("doctors")
public class DoctorApi {
	
	@Autowired
	private DoctorService service;

	@GetMapping
	PageInfo<DoctorListItem> search(DoctorSearch form,
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		return service.search(form, page, size);
	}
	
	@GetMapping("{id}")
	@PostAuthorize("hasAnyAuthority('Admin', 'Office') || (hasAuthority('Doctor') && #returnObject.email eq authentication.name)")
	DoctorDetails findById(@PathVariable int id) {
		return service.findById(id);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyAuthority('Admin', 'Office')")
	DoctorDetails create(
			@Validated @RequestBody DoctorEditForm form, 
			BindingResult result) {
		return service.create(form);
	}

	@PutMapping("{id}")
	@PreAuthorize("hasAnyAuthority('Admin', 'Office') || (hasAuthority('Doctor') && #form.email eq authentication.name)")
	DoctorDetails edit(@PathVariable int id, 
			@Validated @RequestBody DoctorEditForm form, 
			BindingResult result) {
		return service.update(id, form);
	}
	
	@PutMapping("{id}/status")
	@PreAuthorize("hasAnyAuthority('Admin', 'Office')")
	DoctorDetails updateStatus(@PathVariable int id, 
			@Validated @RequestBody StatusUpdateForm form, 
			BindingResult result) {
		return service.updateStatus(id, form);
	}
	
	@PutMapping("{id}/department")
	@PreAuthorize("hasAnyAuthority('Admin', 'Office')")
	DoctorDetails update(@PathVariable int id, 
			@Validated @RequestBody DepartmentChangeForm form, 
			BindingResult result) {
		return service.update(id, form);
	}

	@PutMapping("{id}/address")
	@PostAuthorize("hasAnyAuthority('Admin', 'Office') || (hasAuthority('Doctor') && #returnObject.email eq authentication.name)")
	DoctorDetails update(@PathVariable int id, 
			@Validated @RequestBody AddressChangeForm form, 
			BindingResult result) {
		return service.update(id, form);
	}
	
	@PutMapping("{id}/profile")
	@PostAuthorize("hasAnyAuthority('Admin', 'Office') || (hasAuthority('Doctor') && #returnObject.email eq authentication.name)")
	DoctorDetails uploadProfileImage(@PathVariable int id, 
			MultipartFile file) {
		return service.uploadImage(id, file);
	}
	
	@PutMapping("{id}/section")
	@PostAuthorize("hasAnyAuthority('Admin', 'Office') || (hasAuthority('Doctor') && #returnObject.email eq authentication.name)")
	DoctorDetails update(@PathVariable int id, 
			@Validated @RequestBody DoctorSectionForms form, BindingResult result) {
		return service.updateSection(id, form);
	}
	
}
