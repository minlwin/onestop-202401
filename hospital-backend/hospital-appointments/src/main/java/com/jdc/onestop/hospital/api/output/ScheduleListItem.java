package com.jdc.onestop.hospital.api.output;

import java.time.DayOfWeek;
import java.time.LocalDate;

import com.jdc.onestop.hospital.domain.member.entity.Account_;
import com.jdc.onestop.hospital.domain.member.entity.Doctor_;
import com.jdc.onestop.hospital.domain.transaction.entity.DoctorSchedule;
import com.jdc.onestop.hospital.domain.transaction.entity.DoctorSchedule_;
import com.jdc.onestop.hospital.domain.utils.consts.Section;
import com.jdc.onestop.hospital.domain.utils.embeddables.DoctorSchedulePk_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record ScheduleListItem(
		LocalDate issueAt,
		Section section,
		int doctorId,
		String doctorName,
		String degree,
		int maxToken,
		int currentToken) {
	
	public DayOfWeek getDay() {
		return issueAt.getDayOfWeek();
	}

	public static void select(CriteriaQuery<ScheduleListItem> cq, 
			Root<DoctorSchedule> root) {
		cq.multiselect(
			root.get(DoctorSchedule_.id).get(DoctorSchedulePk_.issueDate),
			root.get(DoctorSchedule_.id).get(DoctorSchedulePk_.section),
			root.get(DoctorSchedule_.id).get(DoctorSchedulePk_.doctorId),
			root.get(DoctorSchedule_.doctor).get(Doctor_.account).get(Account_.fullName),
			root.get(DoctorSchedule_.doctor).get(Doctor_.degree),
			root.get(DoctorSchedule_.maxToken),
			root.get(DoctorSchedule_.currentToken)			
		);
	}
}
