package com.jdc.onestop.hospital.service;

import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.onestop.hospital.api.input.OfficeStaffEditForm;
import com.jdc.onestop.hospital.api.input.OfficeStaffSearch;
import com.jdc.onestop.hospital.api.output.OfficeStaffDetails;
import com.jdc.onestop.hospital.api.output.OfficeStaffListItem;
import com.jdc.onestop.hospital.commons.dto.AddressChangeForm;
import com.jdc.onestop.hospital.commons.dto.DepartmentChangeForm;
import com.jdc.onestop.hospital.commons.dto.StatusUpdateForm;
import com.jdc.onestop.hospital.domain.PageInfo;
import com.jdc.onestop.hospital.domain.member.entity.OfficeStaff;
import com.jdc.onestop.hospital.domain.member.entity.OfficeStaff_;
import com.jdc.onestop.hospital.domain.member.repo.OfficeStaffRepo;
import com.jdc.onestop.hospital.domain.utils.consts.OfficeStaffStatus;
import com.jdc.onestop.hospital.exceptions.ApiBusinessException;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
public class OfficeStaffService {
	
	@Autowired
	private OfficeStaffRepo staffRepo;

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public OfficeStaffDetails create(OfficeStaffEditForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public OfficeStaffDetails update(int id, OfficeStaffEditForm form) {
		var entity = staffRepo.findById(id)
				.orElseThrow(() -> new ApiBusinessException("There is no office staff with given id."));
		
		form.update(entity);

		return OfficeStaffDetails.from(entity);
	}

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public OfficeStaffDetails update(int id, StatusUpdateForm form) {
		
		if(!Stream.of(OfficeStaffStatus.values()).map(OfficeStaffStatus::name).toList().contains(form.statusCode())) {
			throw new ApiBusinessException("Invalid office staff status.");
		}
		
		return staffRepo.findById(id)
				.stream()
				.peek(entity -> {
					entity.setStatus(OfficeStaffStatus.valueOf(form.statusCode()));
					entity.setChangeReason(form.changeReason());
					entity.setChangeAt(LocalDateTime.now());
				})
				.map(OfficeStaffDetails::from)
				.findAny()
				.orElseThrow(() -> new ApiBusinessException("There is no office staff with given id."));
	}

	@Transactional(readOnly = true)
	public OfficeStaffDetails findById(int id) {
		return staffRepo.findById(id)
				.map(OfficeStaffDetails::from)
				.orElseThrow(() -> new ApiBusinessException("There is no office staff with given id."));
	}

	@Transactional(readOnly = true)
	public PageInfo<OfficeStaffListItem> search(OfficeStaffSearch form, int page, int size) {
		return staffRepo.search(queryFunc(form), countFunc(form), page, size);
	}

	private Function<CriteriaBuilder, CriteriaQuery<OfficeStaffListItem>> queryFunc(OfficeStaffSearch form) {
		return cb -> {
			var cq = cb.createQuery(OfficeStaffListItem.class);
			var root = cq.from(OfficeStaff.class);
			
			OfficeStaffListItem.select(cq, root);
			cq.where(form.where(cb, root));
			
			return cq;
		};
	}

	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(OfficeStaffSearch form) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(OfficeStaff.class);
			cq.select(cb.count(root.get(OfficeStaff_.id)));
			cq.where(form.where(cb, root));
			return cq;
		};
	}

	public OfficeStaffDetails update(int id, DepartmentChangeForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	public OfficeStaffDetails update(int id, AddressChangeForm form) {
		// TODO Auto-generated method stub
		return null;
	}
}
