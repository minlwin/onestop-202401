package com.jdc.onestop.hospital.commons.dto;

import com.jdc.onestop.hospital.domain.member.entity.Department;

public record DepartmentInfo(
		int id,
		String code,
		String name,
		String phone) {

	public static DepartmentInfo from(Department entity) {
		return new DepartmentInfo(
				entity.getId(), 
				entity.getCode(), 
				entity.getName(), 
				entity.getPhone());
	}
}
