package com.jdc.onestop.hospital.service;

import org.springframework.stereotype.Service;

import com.jdc.onestop.hospital.api.input.PatientEditForm;
import com.jdc.onestop.hospital.api.input.PatientSearch;
import com.jdc.onestop.hospital.api.output.PatientDetails;
import com.jdc.onestop.hospital.api.output.PatientListItem;
import com.jdc.onestop.hospital.domain.PageInfo;

@Service
public class PatientService {

	public PatientDetails update(int id, PatientEditForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	public PatientDetails findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public PageInfo<PatientListItem> search(PatientSearch form) {
		// TODO Auto-generated method stub
		return null;
	}

}
