package com.jdc.onestop.hospital.api.input;

import jakarta.validation.constraints.NotBlank;

public record DepartmentEditForm(
		@NotBlank(message = "Please enter department code.")
		String code,
		@NotBlank(message = "Please enter department name.")
		String name, 
		@NotBlank(message = "Please enter department head code.")
		String headCode,
		@NotBlank(message = "Please enter department phone.")
		String phone, 
		@NotBlank(message = "Please enter department email.")
		String email,
		String address) {

}
