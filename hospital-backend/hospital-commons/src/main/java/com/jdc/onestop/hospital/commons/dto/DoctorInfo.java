package com.jdc.onestop.hospital.commons.dto;

import java.time.LocalDateTime;

import com.jdc.onestop.hospital.domain.utils.consts.EmployeeStatus;

public record DoctorInfo(
		int id,
		String code,
		String name,
		String profile,
		String degree,
		DepartmentListItem department,
		EmployeeStatus status,
		LocalDateTime assignAt,
		LocalDateTime statusChangeAt,
		String statusChangeReason,
		String phone,
		String email,
		int stars,
		AddressInfo address) {

}
