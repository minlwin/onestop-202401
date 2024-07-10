package com.jdc.onestop.hospital.service;

import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.onestop.hospital.api.input.DoctorEditForm;
import com.jdc.onestop.hospital.api.input.DoctorSearch;
import com.jdc.onestop.hospital.api.output.DoctorDetails;
import com.jdc.onestop.hospital.api.output.DoctorListItem;
import com.jdc.onestop.hospital.api.output.OfficeStaffDetails;
import com.jdc.onestop.hospital.commons.dto.AddressChangeForm;
import com.jdc.onestop.hospital.commons.dto.DepartmentChangeForm;
import com.jdc.onestop.hospital.commons.dto.StatusUpdateForm;
import com.jdc.onestop.hospital.domain.PageInfo;
import com.jdc.onestop.hospital.domain.member.entity.Doctor;
import com.jdc.onestop.hospital.domain.member.entity.Doctor_;
import com.jdc.onestop.hospital.domain.member.repo.DoctorRepo;
import com.jdc.onestop.hospital.domain.utils.consts.DoctorStatus;
import com.jdc.onestop.hospital.exceptions.ApiBusinessException;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepo doctorRepo;

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public DoctorDetails create(DoctorEditForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public DoctorDetails update(int id, DoctorEditForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public DoctorDetails updateStatus(int id, StatusUpdateForm form) {
		
		if(!Stream.of(DoctorStatus.values()).map(DoctorStatus::name).toList().contains(form.statusCode())) {
			throw new ApiBusinessException("Invalid doctor status code.");
		}
		
		return doctorRepo.findById(id)
				.stream()
				.peek(entity -> {
					entity.setStatus(DoctorStatus.valueOf(form.statusCode()));
					entity.setChangeReason(form.changeReason());
					entity.setChangeAt(LocalDateTime.now());
				})
				.map(DoctorDetails::from)
				.findAny()
				.orElseThrow(() -> new ApiBusinessException("There is no doctor with given id."));
	}
	
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public OfficeStaffDetails update(int id, DepartmentChangeForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public OfficeStaffDetails update(int id, AddressChangeForm form) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public DoctorDetails uploadImage(int id, MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	public DoctorDetails findById(int id) {
		return doctorRepo.findById(id)
				.map(DoctorDetails::from)
				.orElseThrow(() -> new ApiBusinessException("There is no doctor with given id."));
	}

	@Transactional(readOnly = true)
	public PageInfo<DoctorListItem> search(DoctorSearch form, int page, int size) {
		return doctorRepo.search(queryFunc(form), countFunc(form), page, size);
	}

	private Function<CriteriaBuilder, CriteriaQuery<DoctorListItem>> queryFunc(DoctorSearch form) {
		return cb -> {
			var cq = cb.createQuery(DoctorListItem.class);
			var root = cq.from(Doctor.class);
			
			DoctorListItem.select(cq, root);
			cq.where(form.where(cb, root));
			
			return cq;
		};
	}

	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(DoctorSearch form) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Doctor.class);
			cq.select(cb.count(root.get(Doctor_.id)));
			cq.where(form.where(cb, root));
			return cq;
		};
	}

}
