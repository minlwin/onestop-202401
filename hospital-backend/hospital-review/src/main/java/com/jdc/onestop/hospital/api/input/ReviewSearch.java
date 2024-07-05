package com.jdc.onestop.hospital.api.input;

import java.time.LocalDate;

public record ReviewSearch(
		String doctor,
		String patient,
		int starFrom,
		int startTo,
		LocalDate dateFrom,
		LocalDate dateTo) {

}
