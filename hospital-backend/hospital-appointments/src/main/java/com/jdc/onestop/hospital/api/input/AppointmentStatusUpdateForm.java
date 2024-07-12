package com.jdc.onestop.hospital.api.input;

import org.springframework.util.StringUtils;

import com.jdc.onestop.hospital.domain.transaction.entity.Appointment;
import com.jdc.onestop.hospital.domain.utils.consts.AppointmentStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AppointmentStatusUpdateForm(
		@NotBlank(message = "Please enter doctor email.")
		String doctorEmail,
		@NotBlank(message = "Please enter doctor email.")
		String patientEmail,
		@NotNull(message = "Please select status.")
		AppointmentStatus status,
		String reason
		) {

	public void update(Appointment entity) {
		entity.setStatus(status);
		entity.setChangeReason(reason);
		
		if(StringUtils.hasLength(doctorEmail)) {
			entity.setChangeBy("Doctor");
		}

		if(StringUtils.hasLength(patientEmail)) {
			entity.setChangeBy("Patient");
		}
	}
}
