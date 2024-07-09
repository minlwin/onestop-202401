package com.jdc.onestop.hospital.domain.member.repo;

import java.util.Optional;

import com.jdc.onestop.hospital.domain.BaseRepository;
import com.jdc.onestop.hospital.domain.member.entity.Employee;

public interface EmployeeRepo extends BaseRepository<Employee, Integer> {

	Optional<Employee> findOneByAccountUsername(String username);

}
