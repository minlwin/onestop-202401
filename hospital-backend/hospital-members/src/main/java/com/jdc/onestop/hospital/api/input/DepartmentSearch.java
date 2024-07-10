package com.jdc.onestop.hospital.api.input;

import java.util.ArrayList;

import com.jdc.onestop.hospital.domain.member.entity.Department;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record DepartmentSearch(
		String code,
		String name) {

	public Predicate[] where(CriteriaBuilder cb, Root<Department> root) {
		var list = new ArrayList<Predicate>();
		
		return list.toArray(size -> new Predicate[size]);
	}
}
