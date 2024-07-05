package com.jdc.onestop.hospital.api.input;

import com.jdc.onestop.hospital.domain.utils.consts.Gender;

public record PatientEditForm(
		String code,
		String name,
		Gender gender,
		String building,
		String street,
		String quarter,
		int townshipId) {

}
