package com.jdc.onestop.hospital.api.output;

import java.time.LocalDateTime;

import com.jdc.onestop.hospital.commons.dto.DoctorInfo;
import com.jdc.onestop.hospital.commons.dto.PatientInfo;
import com.jdc.onestop.hospital.domain.transaction.entity.Review;

public record ReviewDetails(
		DoctorInfo doctor,
		PatientInfo patient,
		int stars,
		String reason,
		LocalDateTime reviewAt) {

	public static ReviewDetails from(Review review) {
		return new ReviewDetails(
				DoctorInfo.from(review.getDoctor()), 
				PatientInfo.from(review.getPatient()), 
				review.getStar(), 
				review.getReason(), 
				review.getCreateAt());
	}

}
