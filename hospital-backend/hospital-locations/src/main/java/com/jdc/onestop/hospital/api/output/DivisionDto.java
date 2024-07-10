package com.jdc.onestop.hospital.api.output;

import com.jdc.onestop.hospital.domain.location.entity.Division;
import com.jdc.onestop.hospital.domain.location.entity.Division_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record DivisionDto(int id, String name) {

	public static void select(CriteriaQuery<DivisionDto> cq, Root<Division> root) {
		cq.multiselect(
			root.get(Division_.id),
			root.get(Division_.name)
		);
	}
}
