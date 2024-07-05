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

import com.jdc.onestop.hospital.api.input.AppointmentEditForm;
import com.jdc.onestop.hospital.api.input.AppointmentSearch;
import com.jdc.onestop.hospital.api.input.StatusUpdateForm;
import com.jdc.onestop.hospital.api.output.AppointmentDetails;
import com.jdc.onestop.hospital.api.output.AppointmentListItem;
import com.jdc.onestop.hospital.domain.PageInfo;

@RestController
@RequestMapping("appointments")
public class AppointmentApi {

	@GetMapping
	PageInfo<AppointmentListItem> search(AppointmentSearch form) {
		return null;
	}
	
	@GetMapping("{id}")
	AppointmentDetails findById(@PathVariable String id) {
		return null;
	}
	
	@PostMapping
	AppointmentDetails create(
			@Validated @RequestBody AppointmentEditForm form, BindingResult result) {
		return null;
	}
	
	@PutMapping("{id}")
	AppointmentDetails updateStatus(
			@PathVariable String id,
			@Validated @RequestBody StatusUpdateForm form, BindingResult result) {
		return null;
	}
}
