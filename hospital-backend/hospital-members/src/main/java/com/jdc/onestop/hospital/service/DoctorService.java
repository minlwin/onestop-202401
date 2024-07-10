package com.jdc.onestop.hospital.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.onestop.hospital.api.input.DoctorEditForm;
import com.jdc.onestop.hospital.api.input.DoctorSearch;
import com.jdc.onestop.hospital.api.output.DoctorDetails;
import com.jdc.onestop.hospital.api.output.DoctorListItem;
import com.jdc.onestop.hospital.commons.dto.DoctorInfo;
import com.jdc.onestop.hospital.commons.dto.StatusUpdateForm;
import com.jdc.onestop.hospital.domain.PageInfo;

@Service
public class DoctorService {

	public DoctorInfo create(DoctorEditForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	public DoctorInfo update(int id, DoctorEditForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	public DoctorInfo updateStatus(int id, StatusUpdateForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	public DoctorInfo uploadImage(int id, MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	public DoctorDetails findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public PageInfo<DoctorListItem> search(DoctorSearch form) {
		// TODO Auto-generated method stub
		return null;
	}

}
