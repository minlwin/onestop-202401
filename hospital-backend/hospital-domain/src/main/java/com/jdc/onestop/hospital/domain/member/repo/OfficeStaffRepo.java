package com.jdc.onestop.hospital.domain.member.repo;

import java.util.Optional;

import com.jdc.onestop.hospital.domain.BaseRepository;
import com.jdc.onestop.hospital.domain.member.entity.OfficeStaff;

public interface OfficeStaffRepo extends BaseRepository<OfficeStaff, Integer> {

	Optional<OfficeStaff> findOneByAccountUsername(String username);

}
