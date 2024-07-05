package com.jdc.onestop.hospital.api.output;

import java.time.LocalDateTime;

import com.jdc.onestop.hospital.domain.utils.consts.EmployeeStatus;

public record DoctorListItem(
		int id,
		String code,
		String name,
		EmployeeStatus status,
		int departmentId,
		String departmentName,
		String degree,
		String phone,
		LocalDateTime assignAt
		) {

}
