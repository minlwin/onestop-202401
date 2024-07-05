package com.jdc.onestop.hospital.api.input;

public record StatusUpdateForm(
		String statusCode,
		String changeReason) {

}
