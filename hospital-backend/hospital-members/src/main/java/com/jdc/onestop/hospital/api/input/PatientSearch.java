package com.jdc.onestop.hospital.api.input;

import com.jdc.onestop.hospital.domain.utils.consts.Gender;

public record PatientSearch(
		Gender gender,
		String keyword) {

}
