package com.jdc.onestop.hospital.api.output;

import java.time.LocalDate;

import com.jdc.onestop.hospital.domain.member.entity.Account_;
import com.jdc.onestop.hospital.domain.member.entity.Doctor_;
import com.jdc.onestop.hospital.domain.member.entity.Patient_;
import com.jdc.onestop.hospital.domain.transaction.entity.Appointment;
import com.jdc.onestop.hospital.domain.transaction.entity.Appointment_;
import com.jdc.onestop.hospital.domain.transaction.entity.DoctorSchedule_;
import com.jdc.onestop.hospital.domain.utils.consts.AppointmentStatus;
import com.jdc.onestop.hospital.domain.utils.consts.Section;
import com.jdc.onestop.hospital.domain.utils.embeddables.AppointmentPk;
import com.jdc.onestop.hospital.domain.utils.embeddables.AppointmentPk_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record AppointmentListItem(
		String id,
		LocalDate date,
		Section section,
		int tokenNumber,
		String complain,
		AppointmentStatus status,
		int doctorId,
		String doctorName,
		int patientId,
		String patientName,
		String patientPhone
		) {

	public AppointmentListItem(
		AppointmentPk id,
		LocalDate date,
		Section section,
		int tokenNumber,
		String complain,
		AppointmentStatus status,
		int doctorId,
		String doctorName,
		int patientId,
		String patientName,
		String patientPhone) {
		this(id.getCode(), date, section, tokenNumber, complain, status, doctorId, doctorName, patientId, patientName, patientPhone);
	}
	
	public static void select(CriteriaQuery<AppointmentListItem> cq, Root<Appointment> root) {
		cq.multiselect(
			root.get(Appointment_.id),
			root.get(Appointment_.id).get(AppointmentPk_.issueDate),
			root.get(Appointment_.id).get(AppointmentPk_.section),
			root.get(Appointment_.tokenNumber),
			root.get(Appointment_.complain),
			root.get(Appointment_.status),
			root.get(Appointment_.id).get(AppointmentPk_.doctorId),
			root.get(Appointment_.schedule).get(DoctorSchedule_.doctor).get(Doctor_.account).get(Account_.fullName),
			root.get(Appointment_.patient).get(Patient_.id),
			root.get(Appointment_.patient).get(Patient_.name),
			root.get(Appointment_.patient).get(Patient_.account).get(Account_.phone)
		);
	}
}
