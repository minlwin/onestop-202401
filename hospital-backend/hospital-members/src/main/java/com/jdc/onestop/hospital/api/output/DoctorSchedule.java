package com.jdc.onestop.hospital.api.output;

import java.time.DayOfWeek;
import java.time.LocalDate;

import com.jdc.onestop.hospital.domain.utils.consts.Section;

public record DoctorSchedule(
		LocalDate date,
		DayOfWeek day,
		Section section,
		int tokenNumber) {

}
