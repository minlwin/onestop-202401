package com.jdc.onestop.hospital.service;

import org.springframework.stereotype.Service;

import com.jdc.onestop.hospital.api.input.ReviewEditForm;
import com.jdc.onestop.hospital.api.input.ReviewSearch;
import com.jdc.onestop.hospital.api.output.ReviewDetails;
import com.jdc.onestop.hospital.api.output.ReviewListItem;
import com.jdc.onestop.hospital.domain.PageInfo;

@Service
public class ReviewService {

	public PageInfo<ReviewListItem> search(ReviewSearch form, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public ReviewDetails create(ReviewEditForm form) {
		// TODO Auto-generated method stub
		return null;
	}

}
