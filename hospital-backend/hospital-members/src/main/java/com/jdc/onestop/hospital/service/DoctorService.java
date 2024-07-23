package com.jdc.onestop.hospital.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.onestop.hospital.MessagingConfig.DoctorSectionChangeAction;
import com.jdc.onestop.hospital.api.input.DoctorEditForm;
import com.jdc.onestop.hospital.api.input.DoctorSearch;
import com.jdc.onestop.hospital.api.input.DoctorSectionForms;
import com.jdc.onestop.hospital.api.output.DoctorDetails;
import com.jdc.onestop.hospital.api.output.DoctorListItem;
import com.jdc.onestop.hospital.api.output.dto.DoctorScheduleInfo;
import com.jdc.onestop.hospital.api.output.dto.DoctorWithSchedules;
import com.jdc.onestop.hospital.commons.dto.AddressChangeForm;
import com.jdc.onestop.hospital.commons.dto.DepartmentChangeForm;
import com.jdc.onestop.hospital.commons.dto.DoctorInfo;
import com.jdc.onestop.hospital.commons.dto.DoctorSectionChange;
import com.jdc.onestop.hospital.commons.dto.DoctorSectionChangeItem;
import com.jdc.onestop.hospital.commons.dto.StatusUpdateForm;
import com.jdc.onestop.hospital.domain.PageInfo;
import com.jdc.onestop.hospital.domain.location.repo.TownshipRepo;
import com.jdc.onestop.hospital.domain.member.entity.Account;
import com.jdc.onestop.hospital.domain.member.entity.Doctor;
import com.jdc.onestop.hospital.domain.member.entity.DoctorSection;
import com.jdc.onestop.hospital.domain.member.entity.Doctor_;
import com.jdc.onestop.hospital.domain.member.repo.AccountRepo;
import com.jdc.onestop.hospital.domain.member.repo.DepartmentRepo;
import com.jdc.onestop.hospital.domain.member.repo.DoctorRepo;
import com.jdc.onestop.hospital.domain.member.repo.DoctorSectionRepo;
import com.jdc.onestop.hospital.domain.transaction.repo.DoctorScheduleRepo;
import com.jdc.onestop.hospital.domain.utils.consts.DoctorStatus;
import com.jdc.onestop.hospital.domain.utils.consts.MemberRole;
import com.jdc.onestop.hospital.exceptions.ApiBusinessException;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepo doctorRepo;
	
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
	
	@Autowired
	private ProfileImageStorageService storageService;
	
	@Autowired
	private DoctorSectionRepo sectionRepo;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private DirectExchange sectionChange;
	
	@Autowired
	private DoctorScheduleRepo scheduleRepo;

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public DoctorDetails create(DoctorEditForm form) {
		
		var depertment = departmentRepo.findById(form.department())
				.orElseThrow(() -> new ApiBusinessException("There is no department with given code."));
		
		var account = new Account();
		account.setFullName(form.name());
		account.setUsername(form.email());
		account.setRole(MemberRole.Doctor);
		account.setPassword(passwordEncoder.encode(passwordForNew));
		
		account = accountRepo.save(account);
		
		var doctor = new Doctor();
		doctor.setAccount(account);
		doctor.setEmail(form.email());
		doctor.setDepartment(depertment);
		doctor.setStatus(DoctorStatus.OnDuty);
		
		form.update(doctor);
		
		doctor = doctorRepo.save(doctor);
		
		return DoctorDetails.from(doctor);
	}

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public DoctorDetails update(int id, DoctorEditForm form) {
		return doctorRepo.findById(id)
				.stream()
				.peek(form::update)
				.map(DoctorDetails::from)
				.findAny()
				.orElseThrow(() -> new ApiBusinessException("There is no doctor with given id."));
	}

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public DoctorDetails updateStatus(int id, StatusUpdateForm form) {
		
		if(!Stream.of(DoctorStatus.values()).map(DoctorStatus::name).toList().contains(form.statusCode())) {
			throw new ApiBusinessException("Invalid doctor status code.");
		}
		
		return doctorRepo.findById(id)
				.stream()
				.peek(form::update)
				.map(DoctorDetails::from)
				.findAny()
				.orElseThrow(() -> new ApiBusinessException("There is no doctor with given id."));
	}
	
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public DoctorDetails update(int id, DepartmentChangeForm form) {
		var depertment = departmentRepo.findOneByCode(form.departmentCode())
				.orElseThrow(() -> new ApiBusinessException("There is no department with given code."));
		
		return doctorRepo.findById(id)
				.stream()
				.peek(entity -> {
					entity.setDepartment(depertment);
					entity.setChangeAt(LocalDateTime.now());
					entity.setChangeReason("Change Department");
				})
				.map(DoctorDetails::from)
				.findAny()
				.orElseThrow(() -> new ApiBusinessException("There is no doctor with given id."));
	}

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public DoctorDetails update(int id, AddressChangeForm form) {
		
		var township = townshipRepo.findById(form.townshipId())
				.orElseThrow(() -> new ApiBusinessException("There is no township with given id."));
		
		return doctorRepo.findById(id)
				.stream()
				.peek(entity -> {
					entity.setAddress(form.address(township));
					entity.setChangeAt(LocalDateTime.now());
					entity.setChangeReason("Change Address");
				})
				.map(DoctorDetails::from)
				.findAny()
				.orElseThrow(() -> new ApiBusinessException("There is no doctor with given id."));
	}
	

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public DoctorDetails uploadImage(int id, MultipartFile file) {
		
		if(null == file) {
			throw new ApiBusinessException("Please select profile photo.");
		}
		
		var profile = storageService.saveProfile(id, file);
		
		return doctorRepo.findById(id)
				.stream()
				.peek(a -> a.setProfile(profile))
				.map(DoctorDetails::from)
				.findAny()
				.orElseThrow(() -> new ApiBusinessException("There is no doctor with given id."));
	}
	
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public DoctorDetails updateSection(int id, DoctorSectionForms form) {
		
		var doctor = doctorRepo.findById(id)
				.orElseThrow(() -> new ApiBusinessException("There is no doctor with given id."));
		
		var deletedSections = new ArrayList<DoctorSectionChangeItem>();
		var createdSections = new ArrayList<DoctorSectionChangeItem>();
		
		var sections = doctor.getSection();
		
		for(var section : sections) {
			sectionRepo.delete(section);
			deletedSections.add(new DoctorSectionChangeItem(section.getDay(), section.getSection(), section.getMaxToken()));
		}
		
		doctor.getSection().clear();
		
		for(var section : form.sections()) {
			var doctorSection = new DoctorSection();
			doctorSection.setDoctor(doctor);
			doctorSection.setDay(section.day());
			doctorSection.setSection(section.section());
			doctorSection.setMaxToken(section.maxToken());
			sectionRepo.saveAndFlush(doctorSection);
			doctor.getSection().add(doctorSection);
			createdSections.add(new DoctorSectionChangeItem(section.day(), section.section(), section.maxToken()));
		}
		
		if(!deletedSections.isEmpty()) {
			var message = new DoctorSectionChange(id, form.startDate(), deletedSections);
			rabbitTemplate.convertAndSend(sectionChange.getName(), DoctorSectionChangeAction.Delete.name(), message);
		}
		
		if(!createdSections.isEmpty()) {
			var message = new DoctorSectionChange(id, form.startDate(), createdSections);
			rabbitTemplate.convertAndSend(sectionChange.getName(), DoctorSectionChangeAction.Create.name(), message);
		}
		
		return DoctorDetails.from(doctor);
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
	
	@Transactional(readOnly = true)
	public List<DoctorListItem> search(DoctorSearch form) {
		return doctorRepo.search(queryFunc(form));
	}


	@Transactional(readOnly = true)
	public DoctorWithSchedules findForAppointment(int id) {
		
		var doctorInfo = doctorRepo.findById(id).map(DoctorInfo::from)
				.orElseThrow(() -> new ApiBusinessException("There is no doctor with given id."));
				
		var schedules = scheduleRepo.searchForUpdate(id, LocalDate.now())
				.stream().map(DoctorScheduleInfo::from).toList();
		
		return new DoctorWithSchedules(doctorInfo, schedules);
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
