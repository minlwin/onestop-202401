package com.jdc.onestop.hospital.api.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.onestop.hospital.domain.member.entity.Account_;
import com.jdc.onestop.hospital.domain.member.entity.Department_;
import com.jdc.onestop.hospital.domain.member.entity.OfficeStaff;
import com.jdc.onestop.hospital.domain.member.entity.OfficeStaff_;
import com.jdc.onestop.hospital.domain.utils.consts.OfficeStaffStatus;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record OfficeStaffSearch(
		OfficeStaffStatus status,
		String keyword) {

	public Predicate[] where(CriteriaBuilder cb, Root<OfficeStaff> root) {
		var list = new ArrayList<Predicate>();
		
		if(null != status) {
			list.add(cb.equal(root.get(OfficeStaff_.status), status));
		}
		
		if(StringUtils.hasLength(keyword)) {
			list.add(cb.or(
				cb.equal(root.get(OfficeStaff_.department).get(Department_.code), keyword),
				cb.like(cb.lower(root.get(OfficeStaff_.position)), keyword.toLowerCase().concat("%")),
				cb.like(cb.lower(root.get(OfficeStaff_.department).get(Department_.name)), keyword.toLowerCase().concat("%")),
				cb.like(cb.lower(root.get(OfficeStaff_.account).get(Account_.fullName)), keyword.toLowerCase().concat("%"))
			));
		}
		
		return list.toArray(size -> new Predicate[size]);
	}
}
