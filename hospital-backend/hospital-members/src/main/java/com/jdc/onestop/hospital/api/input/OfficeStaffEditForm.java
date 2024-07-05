package com.jdc.onestop.hospital.api.input;

public record OfficeStaffEditForm(
		String code,
		String name,
		String position,
		int departmentId,
		String building,
		String street,
		String quarter,
		int townshipId) {

}
