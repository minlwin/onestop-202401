package com.jdc.onestop.hospital.domain.member.repo;

import java.util.Optional;

import com.jdc.onestop.hospital.domain.BaseRepository;
import com.jdc.onestop.hospital.domain.member.entity.Patient;

public interface PatientRepo extends BaseRepository<Patient, Integer> {

	Optional<Patient> findOneByAccountUsername(String name);

}
