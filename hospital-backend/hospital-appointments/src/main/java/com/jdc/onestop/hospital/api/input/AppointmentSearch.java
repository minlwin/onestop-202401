package com.jdc.onestop.hospital.api.input;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.onestop.hospital.domain.member.entity.Account_;
import com.jdc.onestop.hospital.domain.member.entity.Doctor_;
import com.jdc.onestop.hospital.domain.member.entity.Patient_;
import com.jdc.onestop.hospital.domain.transaction.entity.Appointment;
import com.jdc.onestop.hospital.domain.transaction.entity.Appointment_;
import com.jdc.onestop.hospital.domain.transaction.entity.DoctorSchedule_;
import com.jdc.onestop.hospital.domain.utils.consts.AppointmentStatus;
import com.jdc.onestop.hospital.domain.utils.embeddables.AppointmentPk_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record AppointmentSearch(
		String doctorEmail,
		String patientEmail,
		AppointmentStatus status,
		String keyword,
		LocalDate from,
		LocalDate to
		) {

	public Predicate[] where(CriteriaBuilder cb, Root<Appointment> root) {
		
		var list = new ArrayList<Predicate>();
		
		if(null != from) {
			list.add(cb.greaterThanOrEqualTo(root.get(Appointment_.id)
					.get(AppointmentPk_.issueDate), from));
		}
		
		if(null != to) {
			list.add(cb.lessThanOrEqualTo(root.get(Appointment_.id)
					.get(AppointmentPk_.issueDate), to));
		}
				
		if(null != status) {
			list.add(cb.equal(root.get(Appointment_.status), status));
		}
		
		if(StringUtils.hasLength(doctorEmail)) {
			list.add(cb.equal(root.get(Appointment_.schedule)
					.get(DoctorSchedule_.doctor).get(Doctor_.email), doctorEmail));
		}
		
		if(StringUtils.hasLength(patientEmail)) {
			list.add(cb.equal(root.get(Appointment_.patient)
					.get(Patient_.email), patientEmail));
		}
		
		if(StringUtils.hasLength(keyword)) {
			list.add(cb.or(
				cb.like(cb.lower(root.get(Appointment_.schedule)
					.get(DoctorSchedule_.doctor).get(Doctor_.account).get(Account_.fullName)), 
						keyword.toLowerCase().concat("%")),
				cb.like(cb.lower(root.get(Appointment_.patient)
						.get(Patient_.account).get(Account_.fullName)), 
							keyword.toLowerCase().concat("%"))
			));
		}

		return list.toArray(size -> new Predicate[size]);
	}
}
