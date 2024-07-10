package com.jdc.onestop.hospital.api.output;

import com.jdc.onestop.hospital.domain.location.entity.District_;
import com.jdc.onestop.hospital.domain.location.entity.Division_;
import com.jdc.onestop.hospital.domain.location.entity.Township;
import com.jdc.onestop.hospital.domain.location.entity.Township_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record TownshipDto(
		int id, 
		String name,
		int districtId, 
		String district,
		int divisionId, 
		String division) {

	public static void select(CriteriaQuery<TownshipDto> cq, Root<Township> root) {
		cq.multiselect(
			root.get(Township_.id),
			root.get(Township_.id),
			root.get(Township_.district).get(District_.id),
			root.get(Township_.district).get(District_.name),
			root.get(Township_.district).get(District_.division).get(Division_.id),
			root.get(Township_.district).get(District_.division).get(Division_.name)
		);
	}
}
