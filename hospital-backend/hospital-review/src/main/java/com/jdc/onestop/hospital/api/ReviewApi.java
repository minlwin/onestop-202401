package com.jdc.onestop.hospital.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.hospital.api.input.ReviewEditForm;
import com.jdc.onestop.hospital.api.input.ReviewSearch;
import com.jdc.onestop.hospital.api.output.ReviewDetails;
import com.jdc.onestop.hospital.api.output.ReviewListItem;
import com.jdc.onestop.hospital.domain.PageInfo;
import com.jdc.onestop.hospital.service.ReviewService;

@RestController
@RequestMapping("review")
public class ReviewApi {
	
	@Autowired
	private ReviewService service;

	@GetMapping
	public PageInfo<ReviewListItem> search(ReviewSearch form, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		return service.search(form, page, size);
	}
	
	@PostMapping
	public ReviewDetails create(
			@Validated @RequestBody ReviewEditForm form, BindingResult result) {
		return service.create(form);
	}
}
