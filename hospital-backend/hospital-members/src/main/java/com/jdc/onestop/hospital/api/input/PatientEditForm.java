package com.jdc.onestop.hospital.api.input;

import java.time.LocalDate;

import com.jdc.onestop.hospital.domain.utils.consts.Gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientEditForm(
		String code,
		@NotBlank(message = "Please enter patient name.")
		String name,
		@NotNull(message = "Please select gender.")
		Gender gender,
		@NotNull(message = "Please enter date of birth.")
		LocalDate dob,
		String building,
		String street,
		String quarter,
		int townshipId) {

}
