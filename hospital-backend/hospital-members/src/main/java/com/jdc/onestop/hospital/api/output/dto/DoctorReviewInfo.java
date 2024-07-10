package com.jdc.onestop.hospital.api.output.dto;

import java.time.LocalDateTime;

import com.jdc.onestop.hospital.domain.transaction.entity.Review;

public record DoctorReviewInfo (
		int stars,
		String reason,
		int patientId,
		String patientName,
		LocalDateTime reviewAt) implements Comparable<DoctorReviewInfo>{

	public static DoctorReviewInfo from(Review entity) {
		return new DoctorReviewInfo(
				entity.getStar(), 
				entity.getReason(), 
				entity.getPatient().getId(), 
				entity.getPatient().getName(), 
				entity.getUpdateAt());
	}

	@Override
	public int compareTo(DoctorReviewInfo o) {
		return reviewAt.compareTo(o.reviewAt());
	}
}
