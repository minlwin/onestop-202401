package com.jdc.onestop.hospital.api.output;

import java.util.ArrayList;
import java.util.List;

import com.jdc.onestop.hospital.commons.dto.DepartmentHead;
import com.jdc.onestop.hospital.domain.member.entity.Department;
import com.jdc.onestop.hospital.domain.member.entity.Doctor;
import com.jdc.onestop.hospital.domain.member.entity.OfficeStaff;

public record DepartmentDetails(
		int id,
		String code,
		String name,
		DepartmentHead head,
		String phone,
		String email,
		List<DoctorListItem> doctors,
		List<OfficeStaffListItem> staffs) {

	public static DepartmentDetails from(Department entity) {
		
		List<DoctorListItem> doctors = new ArrayList<>();
		List<OfficeStaffListItem> staffs = new ArrayList<>();
		
		for(var item : entity.getEmployee()) {
			switch(item) {
			case Doctor d -> doctors.add(DoctorListItem.from(d));
			case OfficeStaff o -> staffs.add(OfficeStaffListItem.form(o));
			default -> throw new IllegalArgumentException();
			}
		}
		
		return new DepartmentDetails(
				entity.getId(), 
				entity.getCode(), 
				entity.getName(), 
				DepartmentHead.from(entity.getHead()), 
				entity.getPhone(), 
				entity.getEmail(), 
				doctors, 
				staffs);
	}

}
