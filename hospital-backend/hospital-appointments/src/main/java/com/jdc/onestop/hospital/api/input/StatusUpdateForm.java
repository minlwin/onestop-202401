package com.jdc.onestop.hospital.api.input;

import com.jdc.onestop.hospital.domain.utils.consts.AppointmentStatus;

public record StatusUpdateForm(
		AppointmentStatus status,
		String reason) {

}
