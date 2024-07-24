package com.jdc.onestop.hospital.api.output;

import java.time.LocalDate;

import com.jdc.onestop.hospital.domain.member.entity.Account_;
import com.jdc.onestop.hospital.domain.member.entity.Department_;
import com.jdc.onestop.hospital.domain.member.entity.OfficeStaff;
import com.jdc.onestop.hospital.domain.member.entity.OfficeStaff_;
import com.jdc.onestop.hospital.domain.utils.consts.EmployeeStatus;
import com.jdc.onestop.hospital.utils.EmployeeCode;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record OfficeStaffListItem(
		int id,
		String name,
		EmployeeStatus status,
		int departmentId,
		String departmentName,
		String position,
		String phone,
		String email,
		LocalDate assignAt) {

	public String getCode() {
		return EmployeeCode.format.formatted(id);
	}

	public static OfficeStaffListItem form(OfficeStaff s) {
		return new OfficeStaffListItem(
				s.getId(), 
				s.getAccount().getFullName(), 
				s.getStatus(), 
				s.getDepartment().getId(), 
				s.getDepartment().getName(), 
				s.getPosition(), 
				s.getAccount().getPhone(), 
				s.getEmail(),
				s.getAssignAt());
	}

	public static void select(CriteriaQuery<OfficeStaffListItem> cq, Root<OfficeStaff> root) {
		cq.multiselect(
			root.get(OfficeStaff_.id),
			root.get(OfficeStaff_.account).get(Account_.fullName),
			root.get(OfficeStaff_.status),
			root.get(OfficeStaff_.department).get(Department_.id),
			root.get(OfficeStaff_.department).get(Department_.name),
			root.get(OfficeStaff_.position),
			root.get(OfficeStaff_.account).get(Account_.phone),
			root.get(OfficeStaff_.email),
			root.get(OfficeStaff_.assignAt)
		);
	}

}
