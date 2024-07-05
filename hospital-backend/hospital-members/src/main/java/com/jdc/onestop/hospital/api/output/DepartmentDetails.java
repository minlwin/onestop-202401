package com.jdc.onestop.hospital.api.output;

public record DepartmentDetails(
		int id,
		String code,
		String name,
		DepartmentHead head,
		String phone,
		String email,
		String address) {

}
