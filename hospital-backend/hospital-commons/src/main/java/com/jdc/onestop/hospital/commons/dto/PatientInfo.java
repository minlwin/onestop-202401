package com.jdc.onestop.hospital.commons.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.onestop.hospital.domain.member.entity.Patient;
import com.jdc.onestop.hospital.domain.utils.consts.Gender;

public record PatientInfo(
		int id,
		String name,
		Gender gender,
		LocalDate dob,
		LocalDateTime registerAt,
		String phone,
		String email,
		AddressInfo address) {

	public static PatientInfo from(Patient entity) {
		return new PatientInfo(entity.getId(), 
				entity.getName(), 
				entity.getGender(), 
				entity.getDob(), 
				entity.getRegisterAt(), 
				entity.getPhone(), 
				entity.getEmail(), 
				AddressInfo.from(entity.getAddress()));
	}
}
