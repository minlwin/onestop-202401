package com.jdc.onestop.hospital.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.hospital.api.input.PatientSearch;
import com.jdc.onestop.hospital.api.output.PatientDetails;
import com.jdc.onestop.hospital.api.output.PatientListItem;
import com.jdc.onestop.hospital.domain.PageInfo;
import com.jdc.onestop.hospital.service.PatientService;

@RestController
@RequestMapping("patients")
public class PatientApi {
	
	@Autowired
	private PatientService service;

	@GetMapping
	@PreAuthorize("hasAnyAuthority('Admin', 'Office') || (hasAuthority('Doctor') && #form.doctorEmail eq authentication.name)")
	PageInfo<PatientListItem> search(PatientSearch form) {
		return service.search(form);
	}
	
	@GetMapping("{id}")
	@PostAuthorize("hasAnyAuthority('Admin', 'Office', 'Doctor') || (hasAuthority('Patient') && #returnObject.patient.email eq authentication.name)")
	PatientDetails findById(@PathVariable int id) {
		return service.findById(id);
	}
}
