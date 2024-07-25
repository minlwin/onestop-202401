package com.jdc.onestop.hospital.api.output.dto;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import com.jdc.onestop.hospital.domain.transaction.entity.DoctorSchedule;
import com.jdc.onestop.hospital.domain.utils.consts.Section;

public record DoctorScheduleInfo(
		int doctorId,
		LocalDate issueAt,
		Section section,
		int currentToken,
		int maxToken) {

	public String getDay() {
		return issueAt.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault());
	}
	
	public String getStartTime() {
		return section.getAcceptableTime();
	}
	
	public boolean isAvailable() {
		return currentToken < maxToken;
	}
	
	public static DoctorScheduleInfo from(DoctorSchedule entity) {
		return new DoctorScheduleInfo(entity.getId().getDoctorId(), entity.getId().getIssueDate(), entity.getId().getSection(), entity.getCurrentToken(), entity.getMaxToken());
	}
}
