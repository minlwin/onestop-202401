package com.jdc.onestop.hospital.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.onestop.hospital.api.input.SignUpForm;
import com.jdc.onestop.hospital.api.output.PatientDetails;
import com.jdc.onestop.hospital.domain.member.entity.Account;
import com.jdc.onestop.hospital.domain.member.entity.Patient;
import com.jdc.onestop.hospital.domain.member.repo.AccountRepo;
import com.jdc.onestop.hospital.domain.member.repo.PatientRepo;
import com.jdc.onestop.hospital.domain.utils.consts.MemberRole;
import com.jdc.onestop.hospital.exceptions.ApiBusinessException;

@Service
public class SignUpService {
	
	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private PatientRepo patientRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public PatientDetails signUp(SignUpForm form) {
		
		// Check Account Email
		if(accountRepo.findOneByUsername(form.email()).isPresent()) {
			throw new ApiBusinessException("Your email has already been used.");
		}
		
		var account = new Account();
		account.setUsername(form.email());
		account.setRole(MemberRole.Patient);
		account.setFullName(form.name());
		account.setPassword(passwordEncoder.encode(form.password()));
		
		account = accountRepo.save(account);
		
		var patient = new Patient();
		patient.setName(form.name());
		patient.setAccount(account);
		
		patient.setRegisterAt(LocalDateTime.now());
		patient.setPhone(form.phone());
		patient.setEmail(form.email());
		
		patient = patientRepo.save(patient);
		
		return PatientDetails.from(patient);
	}

}
