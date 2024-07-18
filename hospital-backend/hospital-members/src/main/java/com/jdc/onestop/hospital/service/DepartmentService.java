package com.jdc.onestop.hospital.service;

import java.util.function.Function;

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
import com.jdc.onestop.hospital.domain.member.entity.Department;
import com.jdc.onestop.hospital.domain.member.entity.Department_;
import com.jdc.onestop.hospital.domain.member.repo.DepartmentRepo;
import com.jdc.onestop.hospital.domain.member.repo.EmployeeRepo;
import com.jdc.onestop.hospital.exceptions.ApiBusinessException;
import com.jdc.onestop.hospital.utils.EmployeeCode;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepo departmentRepo;
	@Autowired
	private EmployeeRepo employeeRepo;

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public DepartmentDetails create(DepartmentEditForm form) {

		if(departmentRepo.countByCode(form.code()) > 0) {
			throw new ApiBusinessException("Department code has been already used.");
		}

		var entity = departmentRepo.save(form.entity());		
		
		return DepartmentDetails.from(entity);
	}

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public DepartmentDetails update(int id, DepartmentEditForm form) {
		
		
		var entity = departmentRepo.findById(id)
				.orElseThrow(() -> new ApiBusinessException("There is no department with given id."));
		
		if(!entity.getCode().equals(form.code())) {
			if(departmentRepo.countByCode(form.code()) > 0) {
				throw new ApiBusinessException("Department code has been already used.");
			}
			entity.setCode(form.code());
		}
		
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

	@Transactional(readOnly = true)
	public DepartmentDetails findById(int id) {
		var entity = departmentRepo.findById(id)
				.orElseThrow(() -> new ApiBusinessException("There is no department with given id."));
		return DepartmentDetails.from(entity);
	}

	@Transactional(readOnly = true)
	public PageInfo<DepartmentListItem> search(DepartmentSearch form, int page, int size) {
		return departmentRepo.search(queryFunc(form), countFunc(form), page, size);
	}
	
	private Function<CriteriaBuilder, CriteriaQuery<DepartmentListItem>> queryFunc(DepartmentSearch form) {
		return cb -> {
			var cq = cb.createQuery(DepartmentListItem.class);
			var root = cq.from(Department.class);
			
			DepartmentListItem.select(cb, cq, root);
			cq.where(form.where(cb, root));
			
			return cq;
		};
	}

	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(DepartmentSearch form) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Department.class);
			cq.select(cb.count(root.get(Department_.id)));
			cq.where(form.where(cb, root));
			return cq;
		};
	}

}
