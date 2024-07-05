package com.jdc.onestop.hospital.api.input;

public record DoctorEditForm(
		String code,
		String name,
		String degree,
		int departmentId,
		String phone,
		String email,
		String building,
		String street,
		String quarter,
		int townshipId		
		) {

}
