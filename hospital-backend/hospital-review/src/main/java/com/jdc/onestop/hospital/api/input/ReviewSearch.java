package com.jdc.onestop.hospital.api.input;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.onestop.hospital.domain.member.entity.Account_;
import com.jdc.onestop.hospital.domain.member.entity.Doctor_;
import com.jdc.onestop.hospital.domain.member.entity.Patient_;
import com.jdc.onestop.hospital.domain.transaction.entity.Review;
import com.jdc.onestop.hospital.domain.transaction.entity.Review_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record ReviewSearch(
		String doctor,
		String patient,
		Integer starFrom,
		Integer starTo,
		LocalDate dateFrom,
		LocalDate dateTo) {

	public Predicate[] where(CriteriaBuilder cb, Root<Review> root) {
		var list = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(doctor)) {
			list.add(cb.like(cb.lower(root.get(Review_.doctor).get(Doctor_.account).get(Account_.fullName)), doctor.toLowerCase().concat("%")));
		}
		
		if(StringUtils.hasLength(patient)) {
			list.add(cb.like(cb.lower(root.get(Review_.patient).get(Patient_.account).get(Account_.fullName)), patient.toLowerCase().concat("%")));
		}
		
		if(null != starFrom) {
			list.add(cb.ge(root.get(Review_.star), starFrom));
		}

		if(null != starTo) {
			list.add(cb.le(root.get(Review_.star), starTo));
		}

		if(null != dateFrom) {
			list.add(cb.greaterThanOrEqualTo(root.get(Review_.createAt), dateFrom.atStartOfDay()));
		}

		if(null != dateTo) {
			list.add(cb.lessThanOrEqualTo(root.get(Review_.createAt), dateTo.plusDays(1).atStartOfDay()));
		}
		
		return list.toArray(size -> new Predicate[size]);
	}
	
}
