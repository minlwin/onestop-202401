package com.jdc.onestop.hospital.commons.dto;

import jakarta.validation.constraints.NotNull;

public record DepartmentChangeForm(
		@NotNull(message = "Please select department")
		Integer departmentId) {

}
