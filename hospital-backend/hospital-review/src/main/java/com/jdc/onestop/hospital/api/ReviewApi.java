package com.jdc.onestop.hospital.api;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.hospital.api.input.ReviewEditForm;
import com.jdc.onestop.hospital.api.input.ReviewSearch;
import com.jdc.onestop.hospital.api.output.ReviewDetails;
import com.jdc.onestop.hospital.api.output.ReviewListItem;
import com.jdc.onestop.hospital.domain.PageInfo;

@RestController
@RequestMapping("review")
public class ReviewApi {

	@GetMapping
	public PageInfo<ReviewListItem> search(ReviewSearch search) {
		return null;
	}
	
	@GetMapping
	public ReviewDetails create(
			@Validated @RequestBody ReviewEditForm form, BindingResult result) {
		return null;
	}
}
