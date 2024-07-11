package com.jdc.onestop.hospital.api.input;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.onestop.hospital.domain.member.entity.OfficeStaff;
import com.jdc.onestop.hospital.domain.utils.consts.OfficeStaffStatus;

import jakarta.validation.constraints.NotBlank;

public record OfficeStaffCreateForm(
		@NotBlank(message = "Please enter staff name.")
		String name,
		@NotBlank(message = "Please enter staff position.")
		String position,
		@NotBlank(message = "Please enter assign date.")
		LocalDate assignAt,
		@NotBlank(message = "Please enter department code.")
		String departmentCode,
		@NotBlank(message = "Please enter email for login.")
		String email,
		@NotBlank(message = "Please enter phone number.")
		String phone) {

	public void updateFields(OfficeStaff entity) {
		entity.setPosition(position);
		entity.setAssignAt(assignAt);
		entity.setEmail(email);
		entity.setPhone(phone);
		entity.setStatus(OfficeStaffStatus.Assigned);
		entity.setChangeAt(LocalDateTime.now());
		entity.setChangeReason("New staff registration.");
	}
	
}
