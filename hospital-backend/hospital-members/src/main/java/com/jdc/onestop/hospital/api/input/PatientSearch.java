package com.jdc.onestop.hospital.api.input;

import java.util.ArrayList;

import com.jdc.onestop.hospital.domain.member.entity.Patient;
import com.jdc.onestop.hospital.domain.utils.consts.Gender;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record PatientSearch(
		String doctorEmail,
		Gender gender,
		String keyword) {

	public Predicate[] where(CriteriaBuilder cb, Root<Patient> root) {
		var list = new ArrayList<Predicate>();
		
		return list.toArray(size -> new Predicate[size]);
	}
}
