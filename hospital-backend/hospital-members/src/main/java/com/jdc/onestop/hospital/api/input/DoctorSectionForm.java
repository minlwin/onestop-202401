package com.jdc.onestop.hospital.api.input;

import java.time.DayOfWeek;

import com.jdc.onestop.hospital.domain.utils.consts.Section;

import jakarta.validation.constraints.NotNull;

public record DoctorSectionForm(
		@NotNull(message = "Please select day")
		DayOfWeek day,
		@NotNull(message = "Please select section")
		Section section,
		Integer maxToken
		) {

}
