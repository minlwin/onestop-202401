package com.jdc.onestop.hospital.api.input;

import com.jdc.onestop.hospital.domain.member.entity.Department;

import jakarta.validation.constraints.NotBlank;

public record DepartmentEditForm(
		@NotBlank(message = "Please enter department code.")
		String code,
		@NotBlank(message = "Please enter department name.")
		String name, 
		String headCode,
		@NotBlank(message = "Please enter department phone.")
		String phone, 
		@NotBlank(message = "Please enter department email.")
		String email) {

	public Department entity() {
		var entity = new Department();
		entity.setCode(code);
		entity.setName(name);
		entity.setPhone(phone);
		entity.setEmail(email);
		return entity;
	}

}
