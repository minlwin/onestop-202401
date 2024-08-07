package com.jdc.onestop.hospital.api.output;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.onestop.hospital.commons.dto.DoctorInfo;
import com.jdc.onestop.hospital.commons.dto.PatientInfo;
import com.jdc.onestop.hospital.domain.transaction.entity.Appointment;
import com.jdc.onestop.hospital.domain.utils.consts.AppointmentStatus;
import com.jdc.onestop.hospital.domain.utils.consts.Section;

public record AppointmentDetails(
		String id,
		LocalDate date,
		Section section,
		int tokenNumber,
		DoctorInfo doctor,
		PatientInfo patient,
		String complain,
		AppointmentStatus status,
		LocalDateTime registerAt,
		LocalDateTime statusChageAt,
		String changeReason,
		String changeBy
		) {

	public static AppointmentDetails from(Appointment entity) {
		return new AppointmentDetails(
				entity.getId().getCode(), 
				entity.getId().getIssueDate(), 
				entity.getId().getSection(), 
				entity.getTokenNumber(), 
				DoctorInfo.from(entity.getSchedule().getDoctor()), 
				PatientInfo.from(entity.getPatient()), 
				entity.getComplain(), 
				entity.getStatus(), 
				entity.getCreateAt(), 
				entity.getUpdateAt(), 
				entity.getChangeReason(), 
				entity.getChangeBy());
	}
}
