package com.jdc.onestop.hospital.api.output;

import java.time.LocalDateTime;

import com.jdc.onestop.hospital.commons.dto.DoctorInfo;
import com.jdc.onestop.hospital.commons.dto.PatientInfo;

public record ReviewDetails(
		DoctorInfo doctor,
		PatientInfo patient,
		int stars,
		String reason,
		LocalDateTime reviewAt) {

}
