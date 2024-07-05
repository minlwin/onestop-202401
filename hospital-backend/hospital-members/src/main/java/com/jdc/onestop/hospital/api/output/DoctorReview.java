package com.jdc.onestop.hospital.api.output;

import java.time.LocalDateTime;

public record DoctorReview(
		int stars,
		String reason,
		int patientId,
		String patientName,
		LocalDateTime reviewAt) {

}
