package com.jdc.onestop.hospital.api.output;

import java.time.LocalDate;

import com.jdc.onestop.hospital.domain.member.entity.Account_;
import com.jdc.onestop.hospital.domain.member.entity.Department_;
import com.jdc.onestop.hospital.domain.member.entity.Doctor;
import com.jdc.onestop.hospital.domain.member.entity.Doctor_;
import com.jdc.onestop.hospital.domain.utils.consts.EmployeeStatus;
import com.jdc.onestop.hospital.utils.EmployeeCode;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record DoctorListItem(
		int id,
		String name,
		String profile,
		EmployeeStatus status,
		int departmentId,
		String departmentName,
		String degree,
		String phone,
		String email,
		LocalDate assignAt,
		int stars
		) {
	
	public String getCode() {
		return EmployeeCode.format.formatted(id);
	}

	public static void select(CriteriaQuery<DoctorListItem> cq, Root<Doctor> root) { 
		
		cq.multiselect(
			root.get(Doctor_.id),
			root.get(Doctor_.account).get(Account_.fullName),
			root.get(Doctor_.account).get(Account_.profile),
			root.get(Doctor_.status),
			root.get(Doctor_.department).get(Department_.id),
			root.get(Doctor_.department).get(Department_.name),
			root.get(Doctor_.degree),
			root.get(Doctor_.account).get(Account_.phone),
			root.get(Doctor_.email),
			root.get(Doctor_.assignAt),
			root.get(Doctor_.star)			
		);
	}
	
	public static DoctorListItem from(Doctor d) {
		
		return new DoctorListItem(
				d.getId(), 
				d.getAccount().getFullName(),
				d.getAccount().getProfile(), 
				d.getStatus(), 
				d.getDepartment().getId(), 
				d.getDepartment().getName(), 
				d.getDegree(), 
				d.getAccount().getPhone(), 
				d.getEmail(),
				d.getAssignAt(),
				d.getStar());
	}
	
	

}
