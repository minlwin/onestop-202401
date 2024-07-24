package com.jdc.onestop.hospital.api.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.onestop.hospital.domain.member.entity.Account_;
import com.jdc.onestop.hospital.domain.member.entity.Doctor_;
import com.jdc.onestop.hospital.domain.member.entity.Patient;
import com.jdc.onestop.hospital.domain.member.entity.Patient_;
import com.jdc.onestop.hospital.domain.transaction.entity.Appointment_;
import com.jdc.onestop.hospital.domain.transaction.entity.DoctorSchedule_;
import com.jdc.onestop.hospital.domain.utils.consts.Gender;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record PatientSearch(
		String doctorEmail,
		Gender gender,
		String keyword) {

	public Predicate[] where(CriteriaBuilder cb, Root<Patient> root) {
		var list = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(doctorEmail)) {

			var appointment = root.join(Patient_.appointment, JoinType.LEFT);
			var schedule = appointment.join(Appointment_.schedule, JoinType.LEFT);
			var doctor = schedule.join(DoctorSchedule_.doctor, JoinType.LEFT);

			list.add(cb.equal(doctor.get(Doctor_.email), doctorEmail));
		}

		if(null != gender) {
			list.add(cb.equal(root.get(Patient_.gender), gender));
		}
		
		if(StringUtils.hasLength(keyword)) {
			list.add(cb.or(
				cb.like(root.get(Patient_.account).get(Account_.phone), keyword.concat("%")),
				cb.like(cb.lower(root.get(Patient_.name)), keyword.toLowerCase().concat("%"))
			));
		}
		
		return list.toArray(size -> new Predicate[size]);
	}
}
