package com.jdc.onestop.hospital.commons.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressChangeForm(
		@NotBlank(message = "Please enter building address.")
		String building,
		@NotBlank(message = "Please enter street address.")
		String street,
		@NotBlank(message = "Please enter quarter address.")
		String quarter,
		@NotBlank(message = "Please select township.")
		Integer townshipId) {

}
