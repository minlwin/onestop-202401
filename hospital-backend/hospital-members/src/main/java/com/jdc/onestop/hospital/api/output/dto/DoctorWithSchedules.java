package com.jdc.onestop.hospital.api.output.dto;

import java.util.List;

import com.jdc.onestop.hospital.commons.dto.DoctorInfo;

public record DoctorWithSchedules(
		DoctorInfo doctor,
		List<DoctorScheduleInfo> schedules) {

}
