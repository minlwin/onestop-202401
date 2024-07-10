package com.jdc.onestop.hospital.api.output.dto;

public record AppointmentSummary(
		String doctorCode,
		String doctorName,
		String doctorDegree,
		int appliedCount,
		int finishedCount,
		int canceledCount) {

}
