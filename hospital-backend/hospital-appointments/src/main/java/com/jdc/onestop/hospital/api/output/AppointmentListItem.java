package com.jdc.onestop.hospital.api.output;

import java.time.LocalDate;

import com.jdc.onestop.hospital.domain.utils.consts.AppointmentStatus;
import com.jdc.onestop.hospital.domain.utils.consts.Section;

public record AppointmentListItem(
		String id,
		LocalDate date,
		Section section,
		int tokenNumber,
		String complain,
		AppointmentStatus status,
		int doctorId,
		String doctorName,
		int patientId,
		String patientName,
		String patientPhone
		) {

}
