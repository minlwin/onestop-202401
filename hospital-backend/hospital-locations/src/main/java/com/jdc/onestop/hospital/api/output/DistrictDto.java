package com.jdc.onestop.hospital.api.output;

import com.jdc.onestop.hospital.domain.location.entity.District;
import com.jdc.onestop.hospital.domain.location.entity.District_;
import com.jdc.onestop.hospital.domain.location.entity.Division_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record DistrictDto(
		int id, 
		String name, 
		int divisionId, 
		String division) {

	public static void select(CriteriaQuery<DistrictDto> cq, Root<District> root) {
		cq.multiselect(
			root.get(District_.id),
			root.get(District_.name),
			root.get(District_.division).get(Division_.id),
			root.get(District_.division).get(Division_.name)
		);
	}
}
