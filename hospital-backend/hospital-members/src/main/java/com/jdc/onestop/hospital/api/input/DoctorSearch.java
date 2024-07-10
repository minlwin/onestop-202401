package com.jdc.onestop.hospital.api.input;

import java.util.ArrayList;

import com.jdc.onestop.hospital.domain.member.entity.Doctor;
import com.jdc.onestop.hospital.domain.utils.consts.DoctorStatus;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record DoctorSearch(
		DoctorStatus status,
		String department,
		String name) {

	public Predicate[] where(CriteriaBuilder cb, Root<Doctor> root) {
		var list = new ArrayList<Predicate>();
		
		return list.toArray(size -> new Predicate[size]);
	}
}
