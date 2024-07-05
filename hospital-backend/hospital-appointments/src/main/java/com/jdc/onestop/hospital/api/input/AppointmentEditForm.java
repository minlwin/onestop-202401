package com.jdc.onestop.hospital.api.input;

import java.time.LocalDate;

import com.jdc.onestop.hospital.domain.utils.consts.Section;

import jakarta.validation.constraints.NotNull;

public record AppointmentEditForm(
		@NotNull(message = "Please enter appointment date.")
		LocalDate date,
		@NotNull(message = "Please select section.")
		Section section,
		@NotNull(message = "Please select doctor.")
		Integer doctorId,
		String complain
) {

}
