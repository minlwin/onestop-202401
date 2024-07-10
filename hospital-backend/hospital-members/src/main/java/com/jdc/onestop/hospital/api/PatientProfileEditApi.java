package com.jdc.onestop.hospital.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.hospital.api.input.PatientEditForm;
import com.jdc.onestop.hospital.api.output.OfficeStaffDetails;
import com.jdc.onestop.hospital.api.output.PatientDetails;
import com.jdc.onestop.hospital.commons.dto.AddressChangeForm;
import com.jdc.onestop.hospital.service.PatientService;

@RestController
@RequestMapping("patient/profile")
public class PatientProfileEditApi {
	
	@Autowired
	private PatientService service;

	@PutMapping("{id}")
	@PostAuthorize("hasAnyAuthority('Admin', 'Office') || (hasAuthority('Patient') && #returnObject.patient.email eq authentication.name)")
	PatientDetails edit(@PathVariable int id, 
			@Validated @RequestBody PatientEditForm form, 
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
