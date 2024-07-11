package com.jdc.onestop.hospital.api.input;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DoctorSectionForms(
		@NotNull(message = "Please enter section start date.")
		LocalDate startDate,
		@NotEmpty(message = "Please enter doctor sections")
		List<@Valid DoctorSectionForm> sections) {

}
