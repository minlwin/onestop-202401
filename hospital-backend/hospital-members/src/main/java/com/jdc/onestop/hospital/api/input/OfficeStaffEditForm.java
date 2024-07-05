package com.jdc.onestop.hospital.api.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OfficeStaffEditForm(
		String code,
		@NotBlank(message = "Please enter staff name.")
		String name,
		@NotBlank(message = "Please enter staff position.")
		String position,
		@NotNull(message = "Please select department.")
		Integer departmentId,
		String building,
		String street,
		String quarter,
		int townshipId) {

}
