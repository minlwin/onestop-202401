package com.jdc.onestop.hospital.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.hospital.api.input.DivisionSearch;
import com.jdc.onestop.hospital.api.output.DivisionDto;
import com.jdc.onestop.hospital.service.DivisionService;

@RestController
@RequestMapping("division")
public class DivisionApi {
	
	@Autowired
	private DivisionService service;

	@GetMapping
	public List<DivisionDto> search(DivisionSearch form) {
		return service.search(form);
	}

}
