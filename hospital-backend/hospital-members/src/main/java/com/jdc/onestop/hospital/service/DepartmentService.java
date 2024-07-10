package com.jdc.onestop.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.onestop.hospital.api.input.DepartmentEditForm;
import com.jdc.onestop.hospital.api.input.DepartmentSearch;
import com.jdc.onestop.hospital.api.output.DepartmentDetails;
import com.jdc.onestop.hospital.api.output.DepartmentListItem;
import com.jdc.onestop.hospital.domain.PageInfo;
import com.jdc.onestop.hospital.domain.member.repo.DepartmentRepo;
import com.jdc.onestop.hospital.domain.member.repo.EmployeeRepo;
import com.jdc.onestop.hospital.exceptions.ApiBusinessException;
import com.jdc.onestop.hospital.utils.EmployeeCode;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepo departmentRepo;
	@Autowired
	private EmployeeRepo employeeRepo;

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public DepartmentDetails create(DepartmentEditForm form) {

		validate(form);

		var entity = departmentRepo.save(form.entity());		
		
		return DepartmentDetails.from(entity);
	}

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public DepartmentDetails update(int id, DepartmentEditForm form) {
		
		validate(form);
		
		var entity = departmentRepo.findById(id)
				.orElseThrow(() -> new ApiBusinessException("There is no department with given id."));
		
		entity.setCode(form.code());
		entity.setName(form.name());
		entity.setPhone(form.phone());
		entity.setEmail(form.email());
		
		if(StringUtils.hasLength(form.headCode())) {
			var employee = employeeRepo.findById(EmployeeCode.parse(form.headCode()))
					.orElseThrow(() -> new ApiBusinessException("There is no head with given head code."));
			entity.setHead(employee);
		}
		
		return DepartmentDetails.from(entity);
	}

	private void validate(DepartmentEditForm form) {

		if(departmentRepo.countByCode(form.code()) > 0) {
			throw new ApiBusinessException("Department code has been already used.");
		}
	}

	@Transactional(readOnly = true)
	public DepartmentDetails findById(int id) {
		var entity = departmentRepo.findById(id)
				.orElseThrow(() -> new ApiBusinessException("There is no department with given id."));
		return DepartmentDetails.from(entity);
	}

	@Transactional(readOnly = true)
	public PageInfo<DepartmentListItem> search(DepartmentSearch form) {
		// TODO Auto-generated method stub
		return null;
	}

}
