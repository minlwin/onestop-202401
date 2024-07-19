package com.jdc.onestop.hospital.api.input;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.onestop.hospital.domain.member.entity.OfficeStaff;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OfficeStaffEditForm(
		@NotNull(message = "Please select department")
		Integer department,
		@NotBlank(message = "Please enter staff name.")
		String name,
		@NotBlank(message = "Please enter staff position.")
		String position,
		@NotNull(message = "Please enter assign date.")
		LocalDate assignAt,
		@NotBlank(message = "Please enter email for login.")
		String email,
		@NotBlank(message = "Please enter phone number.")
		String phone,
		String reason) {

	public void update(OfficeStaff entity) {
		entity.getAccount().setFullName(name);
		entity.setPosition(position);
		entity.setAssignAt(assignAt);
		entity.setPhone(phone);
		entity.setChangeAt(LocalDateTime.now());
		entity.setChangeReason(reason);
	}
}
