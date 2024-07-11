package com.jdc.onestop.hospital.api.input;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.onestop.hospital.domain.member.entity.Doctor;

import jakarta.validation.constraints.NotBlank;

public record DoctorEditForm(
		@NotBlank(message = "Please enter doctor name.")
		String name,
		@NotBlank(message = "Please enter degree of doctor.")
		String degree,
		@NotBlank(message = "Please enter phone number.")
		String phone,
		@NotBlank(message = "Please enter assign date.")
		LocalDate assignAt,
		@NotBlank(message = "Please enter change reason.")
		String reason
) {

	public void update(Doctor entity) {
		entity.getAccount().setFullName(name);
		entity.setDegree(degree);
		entity.setPhone(phone);
		entity.setAssignAt(assignAt);
		entity.setChangeAt(LocalDateTime.now());
		entity.setChangeReason(reason);
	}

}
