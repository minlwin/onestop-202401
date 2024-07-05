package com.jdc.onestop.hospital.api.input;

import java.time.LocalDate;

import com.jdc.onestop.hospital.domain.utils.consts.AppointmentStatus;

public record AppointmentSearch(
		AppointmentStatus status,
		String keyword,
		LocalDate from,
		LocalDate to
		) {

}
