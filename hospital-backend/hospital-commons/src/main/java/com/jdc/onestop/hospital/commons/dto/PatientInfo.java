package com.jdc.onestop.hospital.commons.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.onestop.hospital.domain.member.entity.Patient;
import com.jdc.onestop.hospital.domain.utils.consts.Gender;
import com.jdc.onestop.hospital.utils.PatientCode;

public record PatientInfo(
		int id,
		String name,
		Gender gender,
		LocalDate dob,
		String email,
		String phone,
		LocalDateTime registerAt) {

	public static PatientInfo from(Patient entity) {
		return new PatientInfo(entity.getId(), 
				entity.getName(),
				entity.getGender(), 
				entity.getDob(), 
				entity.getEmail(),
				entity.getPhone(),
				entity.getRegisterAt());
	}
	
	public String getCode() {
		return PatientCode.format.formatted(id);
	}
}
