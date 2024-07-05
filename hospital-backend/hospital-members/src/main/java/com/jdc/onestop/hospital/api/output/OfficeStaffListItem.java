package com.jdc.onestop.hospital.api.output;

import java.time.LocalDateTime;

import com.jdc.onestop.hospital.domain.utils.consts.EmployeeStatus;

public record OfficeStaffListItem(
		int id,
		String code,
		String name,
		EmployeeStatus status,
		int departmentId,
		String departmentName,
		String position,
		String phone,
		LocalDateTime assignAt) {

}
