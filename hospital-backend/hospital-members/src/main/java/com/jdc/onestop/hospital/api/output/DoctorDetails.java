package com.jdc.onestop.hospital.api.output;

import java.util.List;

import com.jdc.onestop.hospital.api.output.dto.DoctorReviewInfo;
import com.jdc.onestop.hospital.api.output.dto.DoctorSectionInfo;
import com.jdc.onestop.hospital.commons.dto.DoctorInfo;
import com.jdc.onestop.hospital.domain.member.entity.Doctor;

public record DoctorDetails(
		DoctorInfo doctor,
		List<DoctorSectionInfo> sections, 
		List<DoctorReviewInfo> reviews) {

	public static DoctorDetails from(Doctor entity) {
		return new DoctorDetails(
				DoctorInfo.from(entity), 
				entity.getSection().stream().map(DoctorSectionInfo::from)
					.sorted().toList(), 
				entity.getReview().stream().map(DoctorReviewInfo::from)
					.sorted().toList());
	}
}
