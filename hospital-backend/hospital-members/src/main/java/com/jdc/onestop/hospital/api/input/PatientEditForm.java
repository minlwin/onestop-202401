package com.jdc.onestop.hospital.api.input;

import java.time.LocalDate;

import com.jdc.onestop.hospital.domain.member.entity.Patient;
import com.jdc.onestop.hospital.domain.utils.consts.Gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientEditForm(
		@NotBlank(message = "Please enter patient name.")
		String name,
		@NotBlank(message = "Please enter phone number.")
		String phone,
		@NotNull(message = "Please select gender.")
		Gender gender,
		@NotNull(message = "Please enter date of birth.")
		LocalDate dob) {

	public void updateFields(Patient patient) {
		patient.setName(name);
		patient.setGender(gender);
		patient.setDob(dob);
		patient.getAccount().setPhone(phone);
	}

}
