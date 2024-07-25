package com.jdc.onestop.hospital.service;

import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.jdc.onestop.hospital.domain.location.repo.TownshipRepo;
import com.jdc.onestop.hospital.domain.member.entity.Account;
import com.jdc.onestop.hospital.domain.member.entity.OfficeStaff;
import com.jdc.onestop.hospital.domain.member.entity.OfficeStaff_;
import com.jdc.onestop.hospital.domain.member.repo.AccountRepo;
import com.jdc.onestop.hospital.domain.member.repo.DepartmentRepo;
import com.jdc.onestop.hospital.domain.member.repo.OfficeStaffRepo;
import com.jdc.onestop.hospital.domain.utils.consts.MemberRole;
import com.jdc.onestop.hospital.domain.utils.consts.OfficeStaffStatus;
import com.jdc.onestop.hospital.exceptions.ApiBusinessException;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
public class OfficeStaffService {
	
	@Autowired
	private OfficeStaffRepo staffRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Value("${app.account.password.default}")
	private String passwordForNew;

	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private DepartmentRepo departmentRepo;

	@Autowired
	private TownshipRepo townshipRepo;

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public OfficeStaffDetails create(OfficeStaffEditForm form) {
		var depertment = departmentRepo.findById(form.department())
				.orElseThrow(() -> new ApiBusinessException("There is no department with given code."));
		
		var account = new Account();
		account.setFullName(form.name());
		account.setUsername(form.email());
		account.setPhone(form.phone());
		account.setRole(MemberRole.Doctor);
		account.setPassword(passwordEncoder.encode(passwordForNew));
		
		account = accountRepo.save(account);
		
		var entity = new OfficeStaff();
		entity.setAccount(account);
		entity.setDepartment(depertment);
		entity.setEmail(form.email());
		entity.setStatus(OfficeStaffStatus.Assigned);
		form.update(entity);
		
		entity = staffRepo.saveAndFlush(entity);
		
		return OfficeStaffDetails.from(entity);
	}

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public OfficeStaffDetails update(int id, OfficeStaffEditForm form) {
		return staffRepo.findById(id)
				.stream()
				.peek(form::update)
				.map(OfficeStaffDetails::from)
				.findAny()
				.orElseThrow(() -> new ApiBusinessException("There is no office staff with given id."));
	}

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public OfficeStaffDetails update(int id, StatusUpdateForm form) {
		
		if(!Stream.of(OfficeStaffStatus.values()).map(OfficeStaffStatus::name).toList().contains(form.statusCode())) {
			throw new ApiBusinessException("Invalid office staff status.");
		}
		
		return staffRepo.findById(id)
				.stream()
				.peek(form::update)
				.map(OfficeStaffDetails::from)
				.findAny()
				.orElseThrow(() -> new ApiBusinessException("There is no office staff with given id."));
	}
	
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public OfficeStaffDetails update(int id, DepartmentChangeForm form) {
		var depertment = departmentRepo.findOneByCode(form.departmentCode())
				.orElseThrow(() -> new ApiBusinessException("There is no department with given code."));

		return staffRepo.findById(id)
				.stream()
				.peek(entity -> {
					entity.setDepartment(depertment);
					entity.setChangeAt(LocalDateTime.now());
					entity.setChangeReason("Change Department");
				})
				.map(OfficeStaffDetails::from)
				.findAny()
				.orElseThrow(() -> new ApiBusinessException("There is no office staff with given id."));
	}

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public OfficeStaffDetails update(int id, AddressChangeForm form) {
		var township = townshipRepo.findById(form.townshipId())
				.orElseThrow(() -> new ApiBusinessException("There is no township with given id."));

		return staffRepo.findById(id)
				.stream()
				.peek(entity -> {
					entity.setAddress(form.address(township));
					entity.setChangeAt(LocalDateTime.now());
					entity.setChangeReason("Change Address");
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

}
