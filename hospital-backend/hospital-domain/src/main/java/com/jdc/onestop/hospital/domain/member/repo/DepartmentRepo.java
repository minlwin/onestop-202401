package com.jdc.onestop.hospital.domain.member.repo;

import com.jdc.onestop.hospital.domain.BaseRepository;
import com.jdc.onestop.hospital.domain.member.entity.Department;

public interface DepartmentRepo extends BaseRepository<Department, Integer> {

	long countByCode(String code);

}
