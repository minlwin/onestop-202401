package com.jdc.onestop.hospital.api.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.onestop.hospital.domain.member.entity.Account_;
import com.jdc.onestop.hospital.domain.member.entity.Department_;
import com.jdc.onestop.hospital.domain.member.entity.Doctor;
import com.jdc.onestop.hospital.domain.member.entity.Doctor_;
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
		
		if(null != status) {
			list.add(cb.equal(root.get(Doctor_.status), status));
		}
		
		if(StringUtils.hasLength(department)) {
			list.add(cb.like(cb.lower(root.get(Doctor_.department).get(Department_.name)), 
					department.toLowerCase().concat("%")));
		}
		
		if(StringUtils.hasLength(name)) {
			list.add(cb.like(cb.lower(root.get(Doctor_.account).get(Account_.fullName)), 
					name.toLowerCase().concat("%")));
		}		
 		
		return list.toArray(size -> new Predicate[size]);
	}
}
