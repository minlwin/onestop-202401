package com.jdc.onestop.hospital.api.input;

import jakarta.validation.constraints.NotBlank;

public record DoctorEditForm(
		@NotBlank(message = "Please enter doctor name.")
		String name,
		@NotBlank(message = "Please enter degree of doctor.")
		String degree,
		@NotBlank(message = "Please enter phone number.")
		String phone
		) {

}
