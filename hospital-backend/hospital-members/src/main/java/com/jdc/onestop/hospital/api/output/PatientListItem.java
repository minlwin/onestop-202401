package com.jdc.onestop.hospital.api.output;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import com.jdc.onestop.hospital.domain.member.entity.Patient;
import com.jdc.onestop.hospital.domain.member.entity.Patient_;
import com.jdc.onestop.hospital.domain.utils.consts.Gender;
import com.jdc.onestop.hospital.utils.PatientCode;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public record PatientListItem(
		int id,
		String name,
		Gender gender,
		LocalDate dob,
		LocalDateTime registerAt,
		String phone,
		String email,
		long visitCount) {

	public String getAge() {
		if(null != dob) {
			var period = Period.between(dob, LocalDate.now());
			return String.valueOf(period.getYears());
		}
		return "";
	}
	
	public String getCode() {
		return PatientCode.format.formatted(id);
	}
	
	public static void select(CriteriaBuilder cb, 
			CriteriaQuery<PatientListItem> cq, Root<Patient> root) {
		
		var appointments = root.join(Patient_.appointment, JoinType.LEFT);
		
		cq.multiselect(
			root.get(Patient_.id),
			root.get(Patient_.name),
			root.get(Patient_.gender),
			root.get(Patient_.dob),
			root.get(Patient_.registerAt),
			root.get(Patient_.phone),
			root.get(Patient_.email),
			cb.count(appointments)
		);
		
		cq.groupBy(
			root.get(Patient_.id),
			root.get(Patient_.name),
			root.get(Patient_.gender),
			root.get(Patient_.dob),
			root.get(Patient_.registerAt),
			root.get(Patient_.phone),
			root.get(Patient_.email)
		);
	}
}
