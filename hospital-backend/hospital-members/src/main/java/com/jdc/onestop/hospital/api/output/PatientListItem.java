package com.jdc.onestop.hospital.api.output;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import com.jdc.onestop.hospital.domain.utils.consts.Gender;

public record PatientListItem(
		int id,
		String code,
		String name,
		Gender gender,
		LocalDate dob,
		LocalDateTime registerAt,
		String phone,
		String email,
		long visitCount) {

	public int getAge() {
		var period = Period.between(dob, LocalDate.now());
		return period.getYears();
	}
	
}
