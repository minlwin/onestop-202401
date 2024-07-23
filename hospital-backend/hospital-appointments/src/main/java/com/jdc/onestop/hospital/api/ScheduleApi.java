package com.jdc.onestop.hospital.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.hospital.api.input.ScheduleSearch;
import com.jdc.onestop.hospital.api.output.ScheduleListItem;
import com.jdc.onestop.hospital.domain.PageInfo;
import com.jdc.onestop.hospital.service.DoctorScheduleService;

@RestController
@RequestMapping("public/schedules")
public class ScheduleApi {
	
	@Autowired
	private DoctorScheduleService service;

	@GetMapping
	@PreAuthorize("hasAnyAuthority('Admin', 'Office') || (hasAuthority('Doctor') && #form.email eq authentication.name)")
	public PageInfo<ScheduleListItem> search(
			ScheduleSearch form,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		return service.search(form, page, size);
	}
}
