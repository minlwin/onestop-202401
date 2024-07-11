package com.jdc.onestop.hospital.service;

import org.springframework.stereotype.Service;

import com.jdc.onestop.hospital.api.input.AppointmentEditForm;
import com.jdc.onestop.hospital.api.input.AppointmentSearch;
import com.jdc.onestop.hospital.api.output.AppointmentDetails;
import com.jdc.onestop.hospital.api.output.AppointmentListItem;
import com.jdc.onestop.hospital.commons.dto.StatusUpdateForm;
import com.jdc.onestop.hospital.domain.PageInfo;

@Service
public class AppointmentService {

	public PageInfo<AppointmentListItem> search(AppointmentSearch form, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public AppointmentDetails findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public AppointmentDetails create(AppointmentEditForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	public AppointmentDetails update(String id, StatusUpdateForm form) {
		// TODO Auto-generated method stub
		return null;
	}


}
