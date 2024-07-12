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

import com.jdc.onestop.hospital.api.input.AppointmentEditForm;
import com.jdc.onestop.hospital.api.input.AppointmentSearch;
import com.jdc.onestop.hospital.api.input.AppointmentStatusUpdateForm;
import com.jdc.onestop.hospital.api.output.AppointmentDetails;
import com.jdc.onestop.hospital.api.output.AppointmentListItem;
import com.jdc.onestop.hospital.domain.PageInfo;
import com.jdc.onestop.hospital.service.AppointmentService;

@RestController
@RequestMapping("appointments")
public class AppointmentApi {
	
	@Autowired
	private AppointmentService service;

	@GetMapping
	@PreAuthorize("hasAnyAuthority('Admin', 'Office') || (hasAuthority('Doctor') && #form.doctorEmail eq authentication.name) || (hasAuthority('Patient') && #form.patientEmail eq authentication.name)")
	PageInfo<AppointmentListItem> search(AppointmentSearch form, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		return service.search(form, page, size);
	}
	
	@GetMapping("{id}")
	@PostAuthorize("hasAnyAuthority('Admin', 'Office') || (hasAuthority('Doctor') && #returnObject.doctor.email eq authentication.name) || (hasAuthority('Patient') && #returnObject.patient.email eq authentication.name)")
	AppointmentDetails findById(@PathVariable String id) {
		return service.findById(id);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('Patient') && #form.email eq authentication.name")
	AppointmentDetails create(
			@Validated @RequestBody AppointmentEditForm form, BindingResult result) {
		return service.create(form);
	}
	
	@PutMapping("{id}")
	AppointmentDetails updateStatus(
			@PathVariable String id,
			@Validated @RequestBody AppointmentStatusUpdateForm form, BindingResult result) {
		return service.update(id, form);
	}
}
