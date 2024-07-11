package com.jdc.onestop.hospital.commons.dto;

import java.time.LocalDateTime;

import com.jdc.onestop.hospital.domain.member.entity.Doctor;
import com.jdc.onestop.hospital.domain.member.entity.OfficeStaff;
import com.jdc.onestop.hospital.domain.utils.consts.DoctorStatus;
import com.jdc.onestop.hospital.domain.utils.consts.OfficeStaffStatus;

import jakarta.validation.constraints.NotBlank;

public record StatusUpdateForm(
		@NotBlank(message = "Please select status.")
		String statusCode,
		@NotBlank(message = "Please enter status change reason.")
		String changeReason) {
	
	public void update(Doctor entity) {
		entity.setStatus(DoctorStatus.valueOf(statusCode));
		entity.setChangeReason(changeReason);
		entity.setChangeAt(LocalDateTime.now());
	}
	
	public void update(OfficeStaff entity) {
		entity.setStatus(OfficeStaffStatus.valueOf(statusCode));
		entity.setChangeReason(changeReason);
		entity.setChangeAt(LocalDateTime.now());
	}
	
}
