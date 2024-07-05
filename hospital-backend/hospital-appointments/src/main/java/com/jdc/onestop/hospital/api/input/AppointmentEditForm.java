package com.jdc.onestop.hospital.api.input;

import java.time.LocalDate;

import com.jdc.onestop.hospital.domain.utils.consts.Section;

public record AppointmentEditForm(
		LocalDate date,
		Section section,
		int doctorId,
		String complain
) {

}
