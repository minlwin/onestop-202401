package com.jdc.onestop.hospital.api.input;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.onestop.hospital.domain.member.entity.Doctor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DoctorEditForm(
		@NotNull(message = "Please select department")
		Integer department,
		@NotBlank(message = "Please enter doctor name.")
		String name,
		@NotBlank(message = "Please enter degree of doctor.")
		String degree,
		@NotBlank(message = "Please enter phone number.")
		String phone,
		@NotBlank(message = "Please enter email address.")
		String email,
		@NotNull(message = "Please enter assign date.")
		LocalDate assignAt,
		String reason
) {

	public void update(Doctor entity) {
		entity.getAccount().setFullName(name);
		entity.setDegree(degree);
		entity.getAccount().setPhone(phone);
		entity.setEmail(email);
		entity.setAssignAt(assignAt);
		entity.setChangeAt(LocalDateTime.now());
		entity.setChangeReason(reason);
	}

}
