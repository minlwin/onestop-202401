package com.jdc.onestop.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jdc.onestop.hospital.domain.member.entity.Account;
import com.jdc.onestop.hospital.domain.member.repo.AccountRepo;
import com.jdc.onestop.hospital.domain.member.repo.EmployeeRepo;
import com.jdc.onestop.hospital.domain.utils.consts.MemberRole;

@Service
public class AppUserDetailsService implements UserDetailsService{
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return accountRepo.findOneByUsername(username)
				.map(a -> User.withUsername(username)
						.password(a.getPassword())
						.authorities(getAuthorities(a))
						.build())
				.orElseThrow(() -> new UsernameNotFoundException("Invalid login id."));
	}
	
	private String[] getAuthorities(Account account) {
		
		if(account.getRole() == MemberRole.Admin || account.getRole() == MemberRole.Patient) {
			return new String[] {account.getRole().name(), "Activated"};
		}
		
		var employee = employeeRepo.findOneByAccountUsername(account.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("Invalid login id."));
		
		return employee.isActivated() ? new String[] {account.getRole().name(), "Activated"} : 
			new String[] {account.getRole().name()};
	}

}
