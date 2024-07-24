package com.jdc.onestop.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.onestop.hospital.api.input.ProfileInfoForm;
import com.jdc.onestop.hospital.api.output.DoctorDetails;
import com.jdc.onestop.hospital.api.output.OfficeStaffDetails;
import com.jdc.onestop.hospital.api.output.PatientDetails;
import com.jdc.onestop.hospital.api.output.ProfileInfo;
import com.jdc.onestop.hospital.commons.dto.AddressChangeForm;
import com.jdc.onestop.hospital.commons.dto.AddressInfo;
import com.jdc.onestop.hospital.domain.location.repo.TownshipRepo;
import com.jdc.onestop.hospital.domain.member.repo.AccountRepo;
import com.jdc.onestop.hospital.domain.member.repo.DoctorRepo;
import com.jdc.onestop.hospital.domain.member.repo.EmployeeRepo;
import com.jdc.onestop.hospital.domain.member.repo.OfficeStaffRepo;
import com.jdc.onestop.hospital.domain.member.repo.PatientRepo;
import com.jdc.onestop.hospital.exceptions.ApiBusinessException;

@Service
@Transactional
public class MemberProfileService {
	
	@Autowired
	private AccountRepo accountRepo;

	@Autowired
	private ProfileImageStorageService storageService;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	@Autowired
	private DoctorRepo doctorRepo;
	@Autowired
	private OfficeStaffRepo officeStaffRepo;

	@Autowired
	private PatientRepo patientRepo;
	
	@Autowired
	private TownshipRepo townshipRepo;
	
	
	@Transactional(readOnly = true)
	public ProfileInfo search(String username) {
		return accountRepo.findOneByUsername(username).map(ProfileInfo::from)
				.orElseThrow(() -> new ApiBusinessException("Invalid login id."));
	}

	public ProfileInfo uploadPhoto(String username, MultipartFile photo) {
		
		var account = accountRepo.findOneByUsername(username)
				.orElseThrow(() -> new ApiBusinessException("Invalid login id."));
		
		var profile = storageService.saveProfile(account.getUsername(), photo);
		account.setProfile(profile);
		
		return ProfileInfo.from(account);
	}

	public ProfileInfo update(String username, ProfileInfoForm form) {
		var account = accountRepo.findOneByUsername(username)
				.orElseThrow(() -> new ApiBusinessException("Invalid login id."));
		
		account.setFullName(form.name());
		account.setPhone(form.phone());

		return ProfileInfo.from(account);
	}

	public AddressInfo update(String username, AddressChangeForm form) {
		
		var account = accountRepo.findOneByUsername(username)
				.orElseThrow(() -> new ApiBusinessException("Invalid login id."));
		
		var township = townshipRepo.findById(form.townshipId())
				.orElseThrow(() -> new ApiBusinessException("Invalid township id."));
		
		var address = form.address(township);
		
		switch(account.getRole()) {
		case Patient -> {
			patientRepo.findOneByAccountUsername(username).ifPresent(entity -> entity.setAddress(address));
		}
		case Doctor, Office -> {
			employeeRepo.findOneByAccountUsername(username).ifPresent(entity -> entity.setAddress(address));
		}
		default -> throw new ApiBusinessException("Admin user has no address info.");
		}
		
		return AddressInfo.from(address);
	}

	@Transactional(readOnly = true)
	public DoctorDetails loadFindDoctor(String username) {
		return doctorRepo.findOneByAccountUsername(username)
				.map(DoctorDetails::from)
				.orElseThrow(() -> new ApiBusinessException("Invalid login id."));
	}

	@Transactional(readOnly = true)
	public PatientDetails loadPatient(String username) {
		return patientRepo.findOneByAccountUsername(username)
				.map(PatientDetails::from)
				.orElseThrow(() -> new ApiBusinessException("Invalid login id."));
	}

	@Transactional(readOnly = true)
	public OfficeStaffDetails loadOffice(String username) {
		return officeStaffRepo.findOneByAccountUsername(username)
				.map(OfficeStaffDetails::from)
				.orElseThrow(() -> new ApiBusinessException("Invalid login id."));
	}

}
