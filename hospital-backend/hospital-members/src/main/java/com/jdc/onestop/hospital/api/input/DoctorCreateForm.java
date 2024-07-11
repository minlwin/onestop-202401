package com.jdc.onestop.hospital.api.input;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.onestop.hospital.domain.member.entity.Doctor;
import com.jdc.onestop.hospital.domain.utils.consts.DoctorStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DoctorCreateForm(
		@NotBlank(message = "Please enter doctor name.")
		String name,
		@NotBlank(message = "Please enter degree of doctor.")
		String degree,
		@NotBlank(message = "Please enter phone number.")
		String phone,
		@NotBlank(message = "Please enter email for login.")
		String email,
		@NotNull(message = "Please enter assign date.")
		LocalDate assignAt,
		@NotBlank(message = "Please enter department code.")
		String departmentCode
		) {

	public void updateFields(Doctor doctor) {
		doctor.setAssignAt(assignAt);
		doctor.setDegree(degree);
		doctor.setPhone(phone);
		doctor.setEmail(email);
		doctor.setStatus(DoctorStatus.OnDuty);
		doctor.setChangeAt(LocalDateTime.now());
		doctor.setChangeReason("New doctor registration.");
	}

}
