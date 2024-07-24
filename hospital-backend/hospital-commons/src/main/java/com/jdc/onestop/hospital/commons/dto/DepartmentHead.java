package com.jdc.onestop.hospital.commons.dto;

import com.jdc.onestop.hospital.domain.member.entity.Employee;
import com.jdc.onestop.hospital.utils.EmployeeCode;

public record DepartmentHead(
		int id,
		String name,
		String role,
		String phone) {
	
	public String getCode() {
		return EmployeeCode.format.formatted(id);
	}
	
	public String getShortName() {
		return "%s : %s".formatted(getCode(), name());
	}

	public static DepartmentHead from(Employee entity) {
		
		if(null != entity) {
			return new DepartmentHead(
					entity.getId(), 
					entity.getAccount().getFullName(), 
					entity.getAccount().getRole().name(), 
					entity.getAccount().getPhone());
		}
		
		return null;
	}

}
