package com.jdc.onestop.hospital.api.output;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.onestop.hospital.commons.dto.AddressInfo;
import com.jdc.onestop.hospital.commons.dto.DepartmentInfo;
import com.jdc.onestop.hospital.domain.member.entity.OfficeStaff;
import com.jdc.onestop.hospital.domain.utils.consts.EmployeeStatus;
import com.jdc.onestop.hospital.utils.EmployeeCode;

public record OfficeStaffDetails(
		int id,
		String name,
		String position,
		DepartmentInfo department,
		EmployeeStatus status,
		LocalDate assignAt,
		LocalDateTime statusChangeAt,
		String statusChangeReason,
		String phone,
		String email,
		AddressInfo address) {

	public String getCode() {
		return EmployeeCode.format.formatted(id);
	}

	public static OfficeStaffDetails from(OfficeStaff entity) {
		return new OfficeStaffDetails(
				entity.getId(), 
				entity.getAccount().getFullName(), 
				entity.getPosition(), 
				DepartmentInfo.from(entity.getDepartment()), 
				entity.getStatus(), 
				entity.getAssignAt(), 
				entity.getChangeAt(), 
				entity.getChangeReason(), 
				entity.getPhone(), 
				entity.getEmail(),
				AddressInfo.from(entity.getAddress()));
	}

}
