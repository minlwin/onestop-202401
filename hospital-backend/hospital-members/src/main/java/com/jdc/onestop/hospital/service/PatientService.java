package com.jdc.onestop.hospital.service;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.onestop.hospital.api.input.PatientEditForm;
import com.jdc.onestop.hospital.api.input.PatientSearch;
import com.jdc.onestop.hospital.api.output.PatientDetails;
import com.jdc.onestop.hospital.api.output.PatientListItem;
import com.jdc.onestop.hospital.commons.dto.AddressChangeForm;
import com.jdc.onestop.hospital.domain.PageInfo;
import com.jdc.onestop.hospital.domain.location.repo.TownshipRepo;
import com.jdc.onestop.hospital.domain.member.entity.Patient;
import com.jdc.onestop.hospital.domain.member.entity.Patient_;
import com.jdc.onestop.hospital.domain.member.repo.PatientRepo;
import com.jdc.onestop.hospital.exceptions.ApiBusinessException;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
public class PatientService {
	
	@Autowired
	private PatientRepo patientRepo;
	@Autowired
	private TownshipRepo townshipRepo;

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public PatientDetails update(int id, PatientEditForm form) {
		
		return patientRepo.findById(id)
				.stream().peek(form::updateFields)
				.map(PatientDetails::from).findAny()
				.orElseThrow(() -> new ApiBusinessException("There is no patient with given id."));
	}
	
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public PatientDetails update(int id, AddressChangeForm form) {
		var township = townshipRepo.findById(form.townshipId())
				.orElseThrow(() -> new ApiBusinessException("There is no township with given id."));

		return patientRepo.findById(id)
				.stream()
				.peek(entity -> {
					entity.setAddress(form.address(township));
				})
				.map(PatientDetails::from)
				.findAny()
				.orElseThrow(() -> new ApiBusinessException("There is no patient with given id."));
	}	

	@Transactional(readOnly = true)
	public PatientDetails findById(int id) {
		return patientRepo.findById(id)
				.map(PatientDetails::from)
				.orElseThrow(() -> new ApiBusinessException("There is no patient with given id."));
	}

	@Transactional(readOnly = true)
	public PageInfo<PatientListItem> search(PatientSearch form, int page, int size) {
		return patientRepo.search(queryFunc(form), countFunc(form), page, size);
	}

	private Function<CriteriaBuilder, CriteriaQuery<PatientListItem>> queryFunc(PatientSearch form) {
		return cb -> {
			var cq = cb.createQuery(PatientListItem.class);
			var root = cq.from(Patient.class);
			
			PatientListItem.select(cb, cq, root);
			cq.where(form.where(cb, root));
			
			return cq;
		};
	}

	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(PatientSearch form) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Patient.class);
			cq.select(cb.count(root.get(Patient_.id)));
			cq.where(form.where(cb, root));
			return cq;
		};
	}

}
