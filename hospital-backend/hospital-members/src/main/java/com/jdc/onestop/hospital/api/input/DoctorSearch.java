package com.jdc.onestop.hospital.api.input;

import com.jdc.onestop.hospital.domain.utils.consts.DoctorStatus;

public record DoctorSearch(
		DoctorStatus status,
		String department,
		String name) {

}
