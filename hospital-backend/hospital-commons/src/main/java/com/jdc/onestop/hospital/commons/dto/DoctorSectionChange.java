package com.jdc.onestop.hospital.commons.dto;

import java.time.LocalDate;
import java.util.List;

public record DoctorSectionChange(
		int doctorId, 
		LocalDate changeFrom,
		List<DoctorSectionChangeItem> items) {

}
