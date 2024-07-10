package com.jdc.onestop.hospital.api.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DoctorEditForm(
		String code,
		@NotBlank(message = "Please enter doctor name.")
		String name,
		@NotBlank(message = "Please enter degree of doctor.")
		String degree,
		@NotNull(message = "Please select department")
		Integer departmentId,
		@NotBlank(message = "Please enter phone number.")
		String phone,
		@NotBlank(message = "Please enter email for login.")
		String email,
		String building,
		String street,
		String quarter,
		int townshipId		
		) {

}
