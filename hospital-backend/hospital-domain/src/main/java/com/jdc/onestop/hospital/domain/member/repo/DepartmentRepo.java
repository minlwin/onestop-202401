package com.jdc.onestop.hospital.domain.member.repo;

import java.util.Optional;

import com.jdc.onestop.hospital.domain.BaseRepository;
import com.jdc.onestop.hospital.domain.member.entity.Department;

public interface DepartmentRepo extends BaseRepository<Department, Integer> {

	long countByCode(String code);

	Optional<Department> findOneByCode(String departmentCode);

}
