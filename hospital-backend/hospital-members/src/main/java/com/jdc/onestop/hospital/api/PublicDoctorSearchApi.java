package com.jdc.onestop.hospital.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.hospital.api.input.DoctorSearch;
import com.jdc.onestop.hospital.api.output.DoctorListItem;
import com.jdc.onestop.hospital.api.output.dto.DoctorWithSchedules;
import com.jdc.onestop.hospital.service.DoctorService;

@RestController
@RequestMapping("public/doctors")
public class PublicDoctorSearchApi {

	@Autowired
	private DoctorService service;

	@GetMapping
	List<DoctorListItem> search(DoctorSearch form) {
		return service.search(form);
	}
	
	@GetMapping("{id}")
	DoctorWithSchedules findForAppointment(@PathVariable int id) {
		return service.findForAppointment(id);
	}
}
