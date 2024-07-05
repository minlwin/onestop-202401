package com.jdc.onestop.hospital.api.output;

public record DepartmentListItem(
		int id,
		String code,
		String name,
		String head,
		String phone,
		String email) {

}
