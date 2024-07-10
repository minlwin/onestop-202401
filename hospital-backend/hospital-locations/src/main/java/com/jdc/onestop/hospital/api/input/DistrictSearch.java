package com.jdc.onestop.hospital.api.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.onestop.hospital.domain.location.entity.District;
import com.jdc.onestop.hospital.domain.location.entity.District_;
import com.jdc.onestop.hospital.domain.location.entity.Division_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record DistrictSearch(String name, Integer divisionId, String division) {

	public Predicate[] where(CriteriaBuilder cb, Root<District> root) {
		var list = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(name)) {
			// lower(root.name) like ?
			list.add(cb.like(cb.lower(root.get(District_.name)), 
					name.toLowerCase().concat("%")));
		}
		
		if(null != divisionId) {
			list.add(cb.equal(root.get(District_.division).get(Division_.id), divisionId));
		}
		
		if(StringUtils.hasLength(division)) {
			list.add(cb.like(cb.lower(root.get(District_.division).get(Division_.name)), 
					division.toLowerCase().concat("%")));
		}		

		return list.toArray(size -> new Predicate[size]);
	}
}
