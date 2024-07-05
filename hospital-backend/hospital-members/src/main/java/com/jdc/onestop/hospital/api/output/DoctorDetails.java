package com.jdc.onestop.hospital.api.output;

import java.util.List;

import com.jdc.onestop.hospital.commons.dto.DoctorInfo;

public record DoctorDetails(
		DoctorInfo doctor,
		List<DoctorSchedule> schedules) {

}
