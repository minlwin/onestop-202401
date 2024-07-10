package com.jdc.onestop.hospital.api.output;

import com.jdc.onestop.hospital.domain.member.entity.Account_;
import com.jdc.onestop.hospital.domain.member.entity.Department;
import com.jdc.onestop.hospital.domain.member.entity.Department_;
import com.jdc.onestop.hospital.domain.member.entity.Doctor;
import com.jdc.onestop.hospital.domain.member.entity.Doctor_;
import com.jdc.onestop.hospital.domain.member.entity.Employee_;
import com.jdc.onestop.hospital.domain.member.entity.OfficeStaff;
import com.jdc.onestop.hospital.domain.member.entity.OfficeStaff_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public record DepartmentListItem(
		int id,
		String code,
		String name,
		String head,
		String phone,
		String email,
		long doctors,
		long staffs) {

	public static void select(
			CriteriaBuilder cb,
			CriteriaQuery<DepartmentListItem> cq, 
			Root<Department> root) { 
		
		var employee = root.join(Department_.employee, JoinType.LEFT);
		var doctor = cb.treat(employee, Doctor.class);
		var staff = cb.treat(employee, OfficeStaff.class);
		
		cq.multiselect(
			root.get(Department_.id),
			root.get(Department_.code),
			root.get(Department_.name),
			root.get(Department_.head).get(Employee_.account).get(Account_.fullName),
			root.get(Department_.phone),
			root.get(Department_.email),
			cb.count(doctor.get(Doctor_.id)),
			cb.count(staff.get(OfficeStaff_.id))
		);
		
		cq.groupBy(
			root.get(Department_.id),
			root.get(Department_.code),
			root.get(Department_.name),
			root.get(Department_.head).get(Employee_.account).get(Account_.fullName),
			root.get(Department_.phone),
			root.get(Department_.email)
		);
		
	}
}
