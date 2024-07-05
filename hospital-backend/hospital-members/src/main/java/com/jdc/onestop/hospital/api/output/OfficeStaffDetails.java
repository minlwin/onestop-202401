package com.jdc.onestop.hospital.api.output;

import java.time.LocalDateTime;

import com.jdc.onestop.hospital.commons.dto.AddressInfo;
import com.jdc.onestop.hospital.commons.dto.DepartmentListItem;
import com.jdc.onestop.hospital.domain.utils.consts.EmployeeStatus;

public record OfficeStaffDetails(
		int id,
		String code,
		String name,
		String position,
		DepartmentListItem department,
		EmployeeStatus status,
		LocalDateTime assignAt,
		LocalDateTime statusChangeAt,
		String statusChangeReason,
		String phone,
		AddressInfo address) {

}
