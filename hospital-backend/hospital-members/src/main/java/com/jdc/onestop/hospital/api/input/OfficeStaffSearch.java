package com.jdc.onestop.hospital.api.input;

import java.util.ArrayList;

import com.jdc.onestop.hospital.domain.member.entity.OfficeStaff;
import com.jdc.onestop.hospital.domain.utils.consts.OfficeStaffStatus;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record OfficeStaffSearch(
		OfficeStaffStatus status,
		String keyword) {

	public Predicate[] where(CriteriaBuilder cb, Root<OfficeStaff> root) {
		var list = new ArrayList<Predicate>();
		
		return list.toArray(size -> new Predicate[size]);
	}
}
