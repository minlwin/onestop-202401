package com.jdc.onestop.hospital.api.input;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.onestop.hospital.domain.member.entity.Account_;
import com.jdc.onestop.hospital.domain.member.entity.Doctor_;
import com.jdc.onestop.hospital.domain.transaction.entity.DoctorSchedule;
import com.jdc.onestop.hospital.domain.transaction.entity.DoctorSchedule_;
import com.jdc.onestop.hospital.domain.utils.consts.Section;
import com.jdc.onestop.hospital.domain.utils.embeddables.DoctorSchedulePk_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record ScheduleSearch(
		String email,
		String doctor, 
		Section section,
		LocalDate from,
		LocalDate to
		) {

	public Predicate[] where(CriteriaBuilder cb, 
			Root<DoctorSchedule> root) {
		var list = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(doctor)) {
			list.add(
				cb.like(cb.lower(root.get(DoctorSchedule_.doctor).get(Doctor_.account).get(Account_.fullName)), 
						doctor.toLowerCase().concat("%"))	
			);
		}
		
		if(null != section) {
			list.add(cb.equal(root.get(DoctorSchedule_.id).get(DoctorSchedulePk_.section), section));
		}
		
		if(null != from) {
			list.add(cb.greaterThanOrEqualTo(root.get(DoctorSchedule_.id).get(DoctorSchedulePk_.issueDate), from));
		}
		
		if(null != to) {
			list.add(cb.lessThanOrEqualTo(root.get(DoctorSchedule_.id).get(DoctorSchedulePk_.issueDate), to));
		}
		return list.toArray(size -> new Predicate[size]);
	}
}
