package com.jdc.onestop.hospital.api.output;

import java.time.LocalDateTime;

import com.jdc.onestop.hospital.domain.utils.consts.Gender;

public record PatientListItem(
		int id,
		String code,
		String name,
		Gender gender,
		LocalDateTime registerAt,
		String phone,
		String email,
		long visitCount) {

}
