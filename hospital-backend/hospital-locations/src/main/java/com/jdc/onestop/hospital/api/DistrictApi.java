package com.jdc.onestop.hospital.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.hospital.api.input.DistrictSearch;
import com.jdc.onestop.hospital.api.output.DistrictDto;
import com.jdc.onestop.hospital.service.DistrictService;

@RestController
@RequestMapping("district")
public class DistrictApi {
	
	@Autowired
	private DistrictService service;
	
	@GetMapping
	public List<DistrictDto> search(DistrictSearch form) {
		return service.search(form);
	}

}
