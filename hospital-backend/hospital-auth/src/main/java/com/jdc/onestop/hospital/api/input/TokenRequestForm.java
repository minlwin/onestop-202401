package com.jdc.onestop.hospital.api.input;

import jakarta.validation.constraints.NotBlank;

public record TokenRequestForm(
		@NotBlank(message = "Please enter login id.")
		String username,
		@NotBlank(message = "Please enter password.")
		String password) {

}
