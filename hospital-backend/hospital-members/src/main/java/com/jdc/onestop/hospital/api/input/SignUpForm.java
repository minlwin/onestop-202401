package com.jdc.onestop.hospital.api.input;

import jakarta.validation.constraints.NotBlank;

public record SignUpForm(
		@NotBlank(message = "Please enter patient name.")
		String name,
		@NotBlank(message = "Please enter phone number.")
		String phone,
		@NotBlank(message = "Please enter email for login.")
		String email,
		@NotBlank(message = "Please enter password.")
		String password) {

}
