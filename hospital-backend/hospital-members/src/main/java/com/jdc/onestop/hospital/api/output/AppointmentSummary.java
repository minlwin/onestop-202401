package com.jdc.onestop.hospital.api.output;

public record AppointmentSummary(
		String doctorCode,
		String doctorName,
		String doctorDegree,
		int appliedCount,
		int finishedCount,
		int canceledCount) {

}
