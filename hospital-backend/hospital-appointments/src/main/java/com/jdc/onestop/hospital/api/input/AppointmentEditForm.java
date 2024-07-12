package com.jdc.onestop.hospital.api.input;

import java.time.LocalDate;

import com.jdc.onestop.hospital.domain.utils.consts.Section;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AppointmentEditForm(
		@NotBlank(message = "Please enter patient email.")
		String email,
		@NotNull(message = "Please enter appointment date.")
		LocalDate date,
		@NotNull(message = "Please select section.")
		Section section,
		@NotNull(message = "Please select doctor.")
		Integer doctorId,
		String complain
) {

}
