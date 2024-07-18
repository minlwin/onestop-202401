package com.jdc.onestop.hospital.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.hospital.api.input.DoctorSearch;
import com.jdc.onestop.hospital.api.output.DoctorDetails;
import com.jdc.onestop.hospital.api.output.DoctorListItem;
import com.jdc.onestop.hospital.domain.PageInfo;
import com.jdc.onestop.hospital.service.DoctorService;

@RestController
@RequestMapping("public/doctors")
public class PublicDoctorSearchApi {

	@Autowired
	private DoctorService service;

	@GetMapping
	PageInfo<DoctorListItem> search(DoctorSearch form,
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		return service.search(form, page, size);
	}
	
	@GetMapping("{id}")
	DoctorDetails findById(@PathVariable int id) {
		return service.findById(id);
	}
}
