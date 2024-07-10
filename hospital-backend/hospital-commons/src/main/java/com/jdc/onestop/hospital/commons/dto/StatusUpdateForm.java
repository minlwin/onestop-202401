package com.jdc.onestop.hospital.commons.dto;

import jakarta.validation.constraints.NotBlank;

public record StatusUpdateForm(
		@NotBlank(message = "Please select status.")
		String statusCode,
		@NotBlank(message = "Please enter status change reason.")
		String changeReason) {
}
