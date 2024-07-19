package com.jdc.onestop.hospital.api.output;

import java.util.List;

import com.jdc.onestop.hospital.api.output.dto.DoctorSectionInfo;
import com.jdc.onestop.hospital.commons.dto.AddressInfo;
import com.jdc.onestop.hospital.commons.dto.DoctorInfo;
import com.jdc.onestop.hospital.domain.member.entity.Doctor;

public record DoctorDetails(
		DoctorInfo doctor,
		AddressInfo address,
		List<DoctorSectionInfo> sections) {

	public static DoctorDetails from(Doctor entity) {
		return new DoctorDetails(
				DoctorInfo.from(entity), 
				AddressInfo.from(entity.getAddress()),
				entity.getSection().stream().map(DoctorSectionInfo::from)
					.sorted().toList());
	}
}
