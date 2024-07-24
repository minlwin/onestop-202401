package com.jdc.onestop.hospital.domain.member.repo;

import java.util.List;
import java.util.Optional;

import com.jdc.onestop.hospital.domain.BaseRepository;
import com.jdc.onestop.hospital.domain.member.entity.Doctor;
import com.jdc.onestop.hospital.domain.utils.consts.DoctorStatus;

public interface DoctorRepo extends BaseRepository<Doctor, Integer> {

	List<Doctor> findByStatus(DoctorStatus onduty);

	Optional<Doctor> findOneByAccountUsername(String username);

}
