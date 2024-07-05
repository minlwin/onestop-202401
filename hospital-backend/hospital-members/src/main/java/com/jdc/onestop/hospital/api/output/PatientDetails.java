package com.jdc.onestop.hospital.api.output;

import java.util.List;

import com.jdc.onestop.hospital.commons.dto.PatientInfo;

public record PatientDetails(		
		PatientInfo patient,
		List<AppointmentSummary> appointments) {

}
