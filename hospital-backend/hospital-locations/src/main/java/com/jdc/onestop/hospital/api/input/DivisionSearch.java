package com.jdc.onestop.hospital.api.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.onestop.hospital.domain.location.entity.Division;
import com.jdc.onestop.hospital.domain.location.entity.Division_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record DivisionSearch(String name) {

	public Predicate[] where(CriteriaBuilder cb, Root<Division> root) {
		var list = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(name)) {
			// lower(root.name) like ?
			list.add(cb.like(cb.lower(root.get(Division_.name)), 
					name.toLowerCase().concat("%")));
		}
		
		return list.toArray(size -> new Predicate[size]);
	}
}
