package com.jdc.onestop.hospital.commons.dto;

import java.time.LocalDate;

import com.jdc.onestop.hospital.domain.member.entity.Patient;
import com.jdc.onestop.hospital.domain.utils.consts.Gender;

public record PatientInfo(
		int id,
		String name,
		Gender gender,
		LocalDate dob) {

	public static PatientInfo from(Patient entity) {
		return new PatientInfo(entity.getId(), 
				entity.getName(), 
				entity.getGender(), 
				entity.getDob());
	}
}
