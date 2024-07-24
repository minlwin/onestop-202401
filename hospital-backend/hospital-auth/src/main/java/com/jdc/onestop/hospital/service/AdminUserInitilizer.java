package com.jdc.onestop.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.onestop.hospital.domain.member.entity.Account;
import com.jdc.onestop.hospital.domain.member.repo.AccountRepo;
import com.jdc.onestop.hospital.domain.utils.consts.MemberRole;

@Configuration
public class AdminUserInitilizer {
	
	@Autowired
	private AccountRepo repo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	ApplicationRunner applicationRunner() {
		return args -> {
			
			if(repo.count() == 0) {
				createAdmin();
			}
		};
	}
	
	@Transactional
	private void createAdmin() {
		var admin = new Account();
		admin.setUsername("admin");
		admin.setPassword(passwordEncoder.encode("admin"));
		admin.setRole(MemberRole.Admin);
		admin.setFullName("Admin User");
		admin.setPhone("09782003098");
		repo.save(admin);
	}
}
