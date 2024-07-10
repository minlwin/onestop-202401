package com.jdc.onestop.hospital.commons.dto;

import com.jdc.onestop.hospital.domain.member.entity.Doctor;
import com.jdc.onestop.hospital.utils.EmployeeCode;

public record DoctorInfo(
		int id,
		String name,
		String profile,
		String degree,
		DepartmentInfo department) {

	public String getCode() {
		return EmployeeCode.format.formatted(id);
	}
	
	public static DoctorInfo from(Doctor entity) {
		return new DoctorInfo(
				entity.getId(), 
				entity.getAccount().getFullName(), 
				entity.getProfile(),
				entity.getDegree(), 
				DepartmentInfo.from(entity.getDepartment()));
	}

}
