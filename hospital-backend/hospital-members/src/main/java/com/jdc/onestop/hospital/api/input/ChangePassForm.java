package com.jdc.onestop.hospital.api.input;

import jakarta.validation.constraints.NotBlank;

public record ChangePassForm(
		@NotBlank(message = "Please enter login id.")
		String username,
		@NotBlank(message = "Please enter old password.")
		String oldPass,
		@NotBlank(message = "Please enter new password.")
		String newPass) {

}
