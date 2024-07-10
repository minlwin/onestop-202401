package com.jdc.onestop.hospital.api.input;

import com.jdc.onestop.hospital.domain.member.entity.OfficeStaff;

import jakarta.validation.constraints.NotBlank;

public record OfficeStaffEditForm(
		@NotBlank(message = "Please enter staff name.")
		String name,
		@NotBlank(message = "Please enter staff position.")
		String position,
		@NotBlank(message = "Please enter phone number.")
		String phone) {

	public void update(OfficeStaff entity) {
		// TODO Auto-generated method stub
		
	}

	
}
