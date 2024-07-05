package com.jdc.onestop.hospital.api.output;

import java.time.LocalDateTime;

public record ReviewListItem(
		int doctorId,
		String doctorName,
		String degree,
		String department,
		int patientId,
		String patientName,
		int stars,
		String reason,
		LocalDateTime reviewAt) {

}
