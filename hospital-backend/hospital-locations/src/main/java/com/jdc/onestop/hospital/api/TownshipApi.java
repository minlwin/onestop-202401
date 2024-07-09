package com.jdc.onestop.hospital.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.hospital.api.input.TownshipSearch;
import com.jdc.onestop.hospital.api.output.TownshipDto;
import com.jdc.onestop.hospital.service.TownshipService;

@RestController
@RequestMapping("township")
public class TownshipApi {
	
	@Autowired
	private TownshipService service;

	@GetMapping
	public List<TownshipDto> search(TownshipSearch form) {
		return service.search(form);
	}
}
