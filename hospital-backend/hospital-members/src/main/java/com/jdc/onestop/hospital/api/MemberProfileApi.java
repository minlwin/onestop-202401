package com.jdc.onestop.hospital.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.onestop.hospital.api.input.ProfileInfoForm;
import com.jdc.onestop.hospital.api.output.DoctorDetails;
import com.jdc.onestop.hospital.api.output.OfficeStaffDetails;
import com.jdc.onestop.hospital.api.output.PatientDetails;
import com.jdc.onestop.hospital.api.output.ProfileInfo;
import com.jdc.onestop.hospital.commons.dto.AddressChangeForm;
import com.jdc.onestop.hospital.commons.dto.AddressInfo;
import com.jdc.onestop.hospital.service.MemberProfileService;

@RestController
@RequestMapping("profile")
public class MemberProfileApi {
	
	@Autowired
	private MemberProfileService service;

	@GetMapping
	@PreAuthorize("#username eq authentication.name")
	ProfileInfo getProfile(@RequestParam String username) {
		return service.search(username);
	}
	
	@PutMapping("photo")
	@PreAuthorize("#username eq authentication.name")
	ProfileInfo uploadProfilePhoto(
			@RequestParam String username,
			@RequestParam MultipartFile photo) {
		return service.uploadPhoto(username, photo);
	}	

	@PutMapping("personal")
	@PreAuthorize("#username eq authentication.name")
	ProfileInfo updatePersonalInfo(
			@RequestParam String username,
			@RequestParam ProfileInfoForm form) {
		return service.update(username, form);
	}	

	@PutMapping("address")
	@PreAuthorize("#username eq authentication.name")
	AddressInfo updateAddress(
			@RequestParam String username,
			@RequestParam AddressChangeForm form) {
		return service.update(username, form);
	}	
	
	@GetMapping("doctor")
	@PreAuthorize("#username eq authentication.name")
	DoctorDetails loadForDoctor(@RequestParam String username) {
		return service.loadFindDoctor(username);
	}
	
	@GetMapping("patient")
	@PreAuthorize("#username eq authentication.name")
	PatientDetails loadForPatient(@RequestParam String username) {
		return service.loadPatient(username);
	}

	@GetMapping("office")
	@PreAuthorize("#username eq authentication.name")
	OfficeStaffDetails loadForOffice(@RequestParam String username) {
		return service.loadOffice(username);
	}
}
