package com.jdc.onestop.hospital.api.input;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReviewEditForm(
		@NotNull(message = "Please select doctor.")
		Integer doctorId,
		@Min(value = 0, message = "Rate must be between 0 and 5.")
		@Max(value = 5, message = "Rate must be between 0 and 5.")
		int stars,
		@NotBlank(message = "Please enter reason for review.")
		String reason) {

}
